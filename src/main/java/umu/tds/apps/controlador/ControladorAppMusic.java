package umu.tds.apps.controlador;

import umu.tds.apps.persistencia.DAOException;
import umu.tds.apps.persistencia.FactoriaDAO;
import umu.tds.apps.persistencia.IAdaptadorCancionDAO;
import umu.tds.apps.persistencia.IAdaptadorListaReproduccionDAO;
import umu.tds.apps.persistencia.IAdaptadorUsuarioDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import umu.tds.apps.modelo.*;

public class ControladorAppMusic {

	private static ControladorAppMusic unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorListaReproduccionDAO adaptadorListaReproduccion;

	private CatalogoCanciones catalogoCanciones;
	private CatalogoUsuarios catalogoUsuarios;

	private Usuario usuarioActual; // Para mantener la sesion del usuario
	private int index = 0; // Posicion en la lista
	private MediaPlayer mediaPlayer;
	private static final int MAX_RECIENTES = 10;

	// Patron singleton
	public static ControladorAppMusic getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorAppMusic();
		return unicaInstancia;
	}

	private ControladorAppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
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

	// Método para Eliminar un Usuario

	public boolean borrarUsuario(Usuario user) {

		// Si no existe el usuario, no se procede

		if (catalogoUsuarios.getUsuario(user.getUsername()) == null)
			return false;

		adaptadorUsuario.borrarUsuario(user);
		catalogoUsuarios.removeUsuario(user);
		return true;

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

		adaptadorListaReproduccion.registrarListaReproduccion(lr);
		return lr;
	}

	// Método para añadir una cancion a una Lista de reproduccion - Version (String,
	// Cancion)

	/*
	 * public boolean addCancionToLista(String nombreLista, Cancion cancion) {
	 * ListaReproduccion lr = usuarioActual.addCancionToLista(nombreLista, cancion);
	 * 
	 * if (lr != null) { adaptadorListaReproduccion.modificarListaReproduccion(lr);
	 * return true; } return false; }
	 * 
	 * // Método para añadir una cancion a una Lista de reproduccion - Version
	 * (String, String)
	 * 
	 * public boolean addCancionToLista(String nombreLista, String nombreCancion) {
	 * 
	 * Cancion cancion = catalogoCanciones.getCancion(nombreCancion); return
	 * addCancionToLista(nombreLista, cancion);
	 * 
	 * }
	 */

	// Metodo para añadir una cancion a una lista de reproduccion - Version (Lista,
	// Cancion)

	public ListaReproduccion addCancionToLista(ListaReproduccion lr, Cancion cancion) {

		usuarioActual.addCancionToLista(lr, cancion);
		adaptadorListaReproduccion.modificarListaReproduccion(lr);

		return lr;
	}

	// Método para borrar una Lista de reproduccion - Version (String)

	public boolean removeListaReproduccion(String nombre) {

		return usuarioActual.removeListaReproduccion(nombre);
	}

	// Método para borrar una Lista de reproduccion - Version (ListaReproduccion)

	public boolean removeListaReproduccion(ListaReproduccion lr) {
		return removeListaReproduccion(lr.getNombre());
	}

	// Metodo para añadir/cargar canciones

	public void loadCanciones() {

		// Metodo lee las canciones en la carpeta correspondiente
		// Pasos a seguir:
		// 1. Sacar los estilos (carpetas)
		// 1.1 Guardo los estilos en un array de strings
		// 2. Accedo a cada carpeta con el string guardado anteriormente
		// 2.1 Leo el nombre del fichero
		// 2.2 Separo los campos del nombre del fichero, y construyo el objeto Cancion
		// 3. Añado la cancion a la persistencia

		ArrayList<String> estilos = new ArrayList<String>();

		String resourcePath = "./src/main/resources"; // Funciona cuando empaquetado?

		// Saco las carpetas (estilos)

		File folder = new File(resourcePath);
		for (File fileEntry : folder.listFiles()) {
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

	// Metodo para escuchar una cancion - Revisar

	public void reproducirCancion(Cancion c) {

		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {});
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

	// Metodo para pausar una cancion

	public void pausarCancion(Cancion c) {
		// TODO
	}

	// Metodo para buscar canciones
	
	public void buscarCanciones(String titulo, String interprete, String estilo) {
		
	}
	
	// Metodo para hacerse premium
	// Descuentos
	// Metodo para mostrar canciones recientes
	
	public List<Cancion> getCancionesRecientes(){
		return usuarioActual.getCancionesRecientes();
	}
	
	// Metodo para añadir una cancion reciente
	
	public List<Cancion> addCancionReciente(Cancion c) {
		LinkedList<Cancion> recientes = usuarioActual.getCancionesRecientes();
		
		if (recientes.size() == MAX_RECIENTES) {		// Si hay ya 10 recientes (maximo)
			recientes.removeFirst();		// Me elimina la menos reciente (la primera de la lista)
		}
		
		recientes.add(c);
		
		return recientes;
		
	}
	
	// Metodo para mostrar las playlists del usuario
	// Metodo para mostrar todas las canciones
	// Metodo para imprimir en pdf

	// Para los estilos, podría en el catalogo de canciones
	// tener una lista de estilos presentes en las canciones.

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

	private void loadCancionesInFolder(File archivo, String estilo) {
		String[] split = archivo.getName().split("-");
		String interpretes = split[0];
		String titulo = split[1];

		Cancion cancion = catalogoCanciones.getCancion(titulo);

		// Si no se encuentra en el catalogo (y por tanto en la BD) lo creo y añado a
		// ambos

		if (cancion == null) {
			cancion = new Cancion(titulo, archivo.getPath(), interpretes, estilo, 0);
			catalogoCanciones.addCancion(cancion);
			adaptadorCancion.registrarCancion(cancion);
		}

	}

}
