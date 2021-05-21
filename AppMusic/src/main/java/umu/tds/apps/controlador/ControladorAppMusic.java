package umu.tds.apps.controlador;

import umu.tds.apps.persistencia.DAOException;
import umu.tds.apps.persistencia.FactoriaDAO;
import umu.tds.apps.persistencia.IAdaptadorCancionDAO;
import umu.tds.apps.persistencia.IAdaptadorListaReproduccionDAO;
import umu.tds.apps.persistencia.IAdaptadorUsuarioDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import umu.tds.apps.modelo.*;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import componente.Canciones;
import componente.CancionesEvent;
import componente.CancionesListener;
import componente.CargadorCanciones;

public class ControladorAppMusic implements CancionesListener {

	private static ControladorAppMusic unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorListaReproduccionDAO adaptadorListaReproduccion;

	private CatalogoCanciones catalogoCanciones;
	private CatalogoUsuarios catalogoUsuarios;

	private Usuario usuarioActual; 
	private MediaPlayer mediaPlayer;
	private static final int MAX_RECIENTES = 10;
	private ArrayList<String> estilos;
	
	private Canciones nuevasCanciones;
	private CargadorCanciones cargadorCanciones;
	private String binPath;
	private String tempPath;

	// Patron singleton
	public static ControladorAppMusic getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorAppMusic();
		return unicaInstancia;
	}

	private ControladorAppMusic() {
		estilos = new ArrayList<String>();
		inicializarAdaptadores();
		inicializarCatalogos();
		binPath = ControladorAppMusic.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		// quitar "/" añadida al inicio del path en plataforma Windows
		tempPath = binPath.replace("/bin", "/temp");

		cargadorCanciones = new CargadorCanciones();
		cargadorCanciones.addCancionesListener(this);
		
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	// Método de Login (Usuario / Contraseña)

	public boolean login(String usuario, String password) {
		Usuario user = catalogoUsuarios.getUsuario(usuario);
		
		if (user != null && user.getPassword().equals(password)) {
			this.usuarioActual = user;
			return true;
		}
		return false;
	}

	// Método de Registro

	public boolean registrarUsuario(String nombre, String apellidos, Date fechaNacim, String email, String username,
			String password) {

		// Si ya está registrado, no procedemos

		if (catalogoUsuarios.getUsuario(username) != null)
			return false;

		Usuario user = new Usuario(nombre, apellidos, fechaNacim, email, username, password, false);
		adaptadorUsuario.registrarUsuario(user);
		catalogoUsuarios.addUsuario(user);

		return true;
	}

	// Método para hacer logout

	public void logout() {
		
		// Para guardar la informacion del usuario antes de cerrar sesion (por si acaso)
		adaptadorUsuario.modificarUsuario(usuarioActual); 
		
		this.usuarioActual = null;	// Reinicio usuario
		
		if (mediaPlayer != null) {	// Paro el reproductor y lo elimino
			mediaPlayer.stop();
			mediaPlayer.dispose();
		}
		this.mediaPlayer = null;
	}

	// Método para Obtener usuario actual

	public Usuario getUsuarioActual() {
		return this.usuarioActual;
	}

	// Método para crear una Lista de reproduccion
	// Devuelvo una Lista a la Vista para que se utilice como parametro para otras
	// operaciones
	// La Vista no modificará directamente la Lista, si no que lo hará por medio del
	// controlador

	public ListaReproduccion crearNuevaLista(String nombre) {

		// Primero delego en el usuario para que cree la lista (o devuelva la lista con
		// dicho nombre)

		ListaReproduccion lr = usuarioActual.crearLista(nombre);

		// Una vez creada, la persisto en la base de datos
		// Si ya existe, no hace nada
		// MODIFICADO  - - No hago la persistencia en el momento de la creación. Tengo que esperar a la confirmacion

		return lr;
	}
	
	
	// Metodo para guardar una lista que haya sido creada o modificada
	
	public void guardarLista(ListaReproduccion lr, ArrayList<Cancion> cancionesPlaylist) {
		usuarioActual.addListaReproduccion(lr, cancionesPlaylist);
		adaptadorListaReproduccion.registrarListaReproduccion(lr);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}



	// Metodo para añadir una cancion a una lista de reproduccion - Version (Lista,
	// Cancion)

	public ListaReproduccion addCancionToLista(ListaReproduccion lr, Cancion cancion) {

		usuarioActual.addCancionToLista(lr, cancion);
		adaptadorListaReproduccion.modificarListaReproduccion(lr);

		return lr;
	}

	// Método para borrar una Lista de reproduccion - Version (ListaReproduccion)

	public boolean removeListaReproduccion(ListaReproduccion lr) {
		if (usuarioActual.removeListaReproduccion(lr)) {
			adaptadorListaReproduccion.borrarListaReproduccion(lr);
			adaptadorUsuario.modificarUsuario(usuarioActual);
			return true;
		}
		return false;
	}

	// Metodo para añadir/cargar canciones		MODIFICAR
	// Falta hacer trimming de .mp3 en titulo

	public void loadCanciones() {

		String resourcePath = "E:\\AppMusic\\canciones"; // TEMPORAL - CAMBIAR EN EL FUTURO
		// Cambiar el path a otra carpeta para que no se empaqueten las canciones con la aplicacion.

		// Saco las carpetas (estilos)

		File folder = new File(resourcePath);
		for (File fileEntry : folder.listFiles()) {
			if (!estilos.contains(fileEntry.getName()))
			estilos.add(fileEntry.getName());
		}

		File songFolder;

		// Recorro cada carpeta y añado las canciones (si no existen ya)

		for (String estilo : estilos) {
			songFolder = new File(resourcePath + "/" + estilo);
			for (File fileEntry : songFolder.listFiles())
				loadCancionesInFolder(fileEntry, estilo);
		}

	}

	// Metodo para obtener todas las canciones

	public List<Cancion> getAllCanciones() {
		return catalogoCanciones.getCanciones();
	}

	// Metodo para escuchar una cancion - OUTDATED - NO UTILIZAR

	private void reproducirCancionBasic(Cancion c) {

		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}

		// Reproducir una canción

		String fileName = c.getRutaFichero();
		File f = new File(fileName);
		Media hit = new Media(f.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
		
	}
	
	
	// Metodo para escuchar una cancion - Version 2
	
	public void reproducirCancion(Cancion c) {
		
		Media hit = null; 
		if (c.getRutaFichero().startsWith("http")) {
			URL uri = null;
			try {
				uri = new URL(c.getRutaFichero());
				Path mp3 = Files.createTempFile("now-playing", ".mp3");

				try (InputStream stream = uri.openStream()) {
					Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
				} 
				
				hit = new Media(mp3.toFile().toURI().toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		} else {
			File f = new File(c.getRutaFichero());
			hit = new Media(f.toURI().toString());
		}
				
		if (mediaPlayer == null) {			// Primera vez que se reproduce una cancion
			
			mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					mediaPlayer.stop();
				}
			});
			mediaPlayer.play();
			c.setNumReproducciones(c.getNumReproducciones()+1);
			addCancionReciente(c);
			adaptadorCancion.modificarCancion(c);
			
		}
		else {		// Si ya se ha creado un MediaPlayer anteriormente (ya se ha escuchado alguna cancion)
			if (mediaPlayer.getStatus() == Status.STOPPED) {
				mediaPlayer.play();
				c.setNumReproducciones(c.getNumReproducciones()+1);
				adaptadorCancion.modificarCancion(c);
				//addCancionReciente(c);
			}
			else if (mediaPlayer.getStatus() == Status.PAUSED) {
				if (mediaPlayer.getMedia().getSource().equals(hit.getSource())) {	// Compruebo si la cancion era la misma que se estaba reproduciendo
					mediaPlayer.play();												// Si lo es, reproduzco
					
				} 
				else {																// Si no es la misma cancion, cambio el media player con esa cancion
					mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.setOnEndOfMedia(new Runnable() {
						public void run() {
							mediaPlayer.stop();
						}
					});
					mediaPlayer.play();
					c.setNumReproducciones(c.getNumReproducciones()+1);				// Cuando es una cancion distinta, incremento numero de reproducciones
					addCancionReciente(c);
					adaptadorCancion.modificarCancion(c);
				}
			} else if (mediaPlayer.getStatus() == Status.PLAYING) {					// Si se cambia la cancion mientras que se esta escuchando otra
				if (!mediaPlayer.getMedia().getSource().equals(hit.getSource())) {	// Compruebo que la cancion no sea la misma
					mediaPlayer.pause();
					mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.setOnEndOfMedia(new Runnable() {
						public void run() {
							mediaPlayer.stop();
						}
					});
					mediaPlayer.play();
					c.setNumReproducciones(c.getNumReproducciones()+1);
					addCancionReciente(c);
					adaptadorCancion.modificarCancion(c);
				} 
			}
		}

	
		
	}
	
	
	
	

	// Metodo para pausar una cancion

	public void pausarCancion() {
		
		if (mediaPlayer != null) {
			if (mediaPlayer.getStatus() == Status.PLAYING) {
				mediaPlayer.pause();
				File directorio = new File(tempPath);

				String[] files = directorio.list();
				for (String archivo : files) {
				 File fichero = new File(tempPath + File.separator + archivo);
				 fichero.delete();
				 }
			}
		}
		
	}

	// Metodo para buscar canciones con un filtro

	public List<Cancion> buscarCanciones(String titulo, String interprete, String estilo) {
		
		loadCanciones();		// Busco canciones en la carpeta, en caso de que haya nuevas canciones.
		
		List<Cancion> lc = getAllCanciones();

		// Interprete lo he filtrado con contains en vez de contentEquals
		// Porque como puede existir varios interpretes en una cancion
		// Pues hago matching con la cadena de interprete
		
		
		// Arreglar algo aqui? idk no funciona bien
		
		lc = lc.stream()
				.filter(c -> titulo.equals("") || c.getTitulo().contains(titulo))
				.filter(c -> interprete.equals("") || c.getInterprete().contains(interprete)) 
				.filter(c -> estilo.equals("") || c.getEstilo().contentEquals(estilo))
				.collect(toList());
		return lc;
	}

	// Metodo para hacerse premium

	public void becomePremium() {
		usuarioActual.realizarPago();
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	

	// Metodo para obtener el descuento aplicado del usuario actual
	public Descuento getDescuentoUsuario() {
		return usuarioActual.getDescuento();
	}
	
	
	// NO SE SI ES NECESARIO ESTE METODO
	
	// Metodo para obtener el valor a pagar con el descuento aplicado
	public Double getPrecio() {
		return usuarioActual.getPrecio();
	}
	
	
	// Metodo para mostrar canciones recientes

	public List<Cancion> getCancionesRecientes() {
		return usuarioActual.getCancionesRecientes();
	}

	// Metodo para añadir una cancion reciente
	// Modificado a void, ya que la lista de recientes se obtiene con el metodo anterior.

	public void addCancionReciente(Cancion c) {
		LinkedList<Cancion> recientes = usuarioActual.getCancionesRecientes();

		if (!recientes.contains(c)) {				// Si la cancion no esta en recientes
			if (recientes.size() == MAX_RECIENTES) { // Si hay ya 10 recientes (maximo)
				recientes.removeFirst(); 			// Me elimina la menos reciente (la primera de la lista)
			}

			usuarioActual.addCancionReciente(c);
			adaptadorUsuario.modificarUsuario(usuarioActual);
		}
		
		/*if (recientes.size() == MAX_RECIENTES) { // Si hay ya 10 recientes (maximo)
			recientes.removeFirst(); // Me elimina la menos reciente (la primera de la lista)
		}

		usuarioActual.addCancionReciente(c);
		adaptadorUsuario.modificarUsuario(usuarioActual);*/
		//recientes = usuarioActual.getCancionesRecientes();		// Redundante?
		

		//return recientes;

	}

	// Metodo para mostrar las playlists del usuario

	public List<ListaReproduccion> getAllListasReproduccion() {
		if (!usuarioActual.getListasReproduccion().isEmpty())
			return usuarioActual.getListasReproduccion();
		return null;
	}

	// Metodo para imprimir en pdf las listas de reproduccion del usuario (Premium)

	public boolean printPDF(String folderpath) throws DocumentException {
		FileOutputStream archivo = null;
		String path = folderpath + "\\" + usuarioActual.getNombre() + "_playlists.pdf";
		
		try {
			archivo = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		List<ListaReproduccion> listasRep = usuarioActual.getListasReproduccion();
		Document documento = new Document();
		PdfWriter.getInstance(documento, archivo);
		documento.open();
		documento.add(new Paragraph("Listas de reproduccion del usuario " + usuarioActual.getNombre() +" "+ usuarioActual.getApellidos()));
		documento.add(new Paragraph("======================================================"));
		
		for (ListaReproduccion lr : listasRep) {
			documento.add(new Paragraph("Nombre de la lista: " + lr.getNombre()));
			
			for (Cancion c : lr.getCanciones()) {
				documento.add(new Paragraph("Titulo: " + c.getTitulo() + "     Interpretes: " + c.getInterprete() + "     Estilo: " + c.getEstilo()));
				
			}
			documento.add(new Paragraph("======================================================"));
		}
		documento.close();
		return true;
		

	}
	
	// Metodo para obtener las 10 canciones mas reproducidas en la aplicacion (Premium)
	
	public List<Cancion> getCancionesMasReproducidas() {
		
		
		// Hacer la comprobacion aqui de si es premium?
		// Igual mejor hacer la comprobacion en la GUI
		// De forma que si no es premium, no te deja pulsar el boton o la opcion(?)
		
		
		List<Cancion> lc = catalogoCanciones.getCanciones();
		
		List<Cancion> masReproducidas = lc.stream()
				.sorted(comparing(Cancion::getNumReproducciones).reversed())
				.limit(10)
				.collect(toList());
		
		return masReproducidas;
		
	}
	
	public ArrayList<String> getEstilos() {
		return this.estilos;
	}
	
	
	
	//======================================================================================
	

	
	
	// Método para inicializar los adaptadores

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
		adaptadorListaReproduccion = factoria.getListaReproduccionDAO();

	}

	// Método para inicializar los catalogos

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();

		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
		loadCanciones();

	}

	// Metodo para cargar una cancion a partir de un fichero
	// Falta hacer trimming de .mp3 en titulo

	private void loadCancionesInFolder(File archivo, String estilo) {
		String[] split = archivo.getName().split("-");
		String interpretes = split[0];
		String titulo = split[1];
		titulo = titulo.split(".mp3")[0];
		titulo = titulo.trim();

		Cancion cancion = catalogoCanciones.getCancion(titulo);

		// Si no se encuentra en el catalogo (y por tanto en la BD) lo creo y añado a
		// ambos

		if (cancion == null) {
			cancion = new Cancion(titulo, archivo.getPath(), interpretes, estilo, 0);
			catalogoCanciones.addCancion(cancion);
			adaptadorCancion.registrarCancion(cancion);
		}

	}

	@Override
	public void nuevasCanciones(EventObject arg0) {
		if (arg0 instanceof CancionesEvent) {
			nuevasCanciones = ((CancionesEvent) arg0).getCancionesNuevas();
		}
			
	}

	public void setFicheroCanciones(String pathFichero) {
		cargadorCanciones.setArchivoCanciones(pathFichero);
		
	}

	public void cargarNuevasCanciones() {
		ArrayList<Cancion> canciones = (ArrayList<Cancion>) catalogoCanciones.cargarNuevasCanciones(nuevasCanciones);
		for(Cancion c : canciones) {
			catalogoCanciones.addCancion(c);
			adaptadorCancion.registrarCancion(c);
		}
		
	}

}



// Método para borrar una Lista de reproduccion - Version (String)

/*
  public boolean removeListaReproduccion(String nombre) {
  
  if(usuarioActual.removeListaReproduccion(nombre)) {
  adaptadorListaReproduccion.borrarListaReproduccion(listaRep); } }
 */


// Método para añadir una cancion a una Lista de reproduccion - Version (String,
// Cancion)

/*
  public boolean addCancionToLista(String nombreLista, Cancion cancion) {
  ListaReproduccion lr = usuarioActual.addCancionToLista(nombreLista, cancion);
  
  if (lr != null) { adaptadorListaReproduccion.modificarListaReproduccion(lr);
  return true; } return false; }
  
  // Método para añadir una cancion a una Lista de reproduccion - Version
  (String, String)
  
  public boolean addCancionToLista(String nombreLista, String nombreCancion) {
  
  Cancion cancion = catalogoCanciones.getCancion(nombreCancion); return
  addCancionToLista(nombreLista, cancion);
  
  }
 */
