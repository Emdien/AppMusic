package umu.tds.apps.persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import com.sun.tools.javac.parser.ReferenceParser.ParseException;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.modelo.Cancion;
import umu.tds.apps.modelo.ListaReproduccion;
import umu.tds.apps.modelo.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new AdaptadorUsuarioTDS();
		return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public void registrarUsuario(Usuario user) {
		Entidad eUsuario;
		boolean existe = true;
		
		// Si el usuario esta registrado --> No se registra
		try {
			eUsuario = servPersistencia.recuperarEntidad(user.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		
		if (existe) return;
		
		// Registrar atributos que son objetos
		
		AdaptadorListaReproduccionTDS adaptadorLR = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		for (ListaReproduccion lr : user.getListasReproduccion()) {
			adaptadorLR.registrarListaReproduccion(lr);
		}
		
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion c : user.getCancionesRecientes()) {
			adaptadorC.registrarCancion(c);
		}
		
		
		// Crear entidad Usuario
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		ArrayList<Propiedad> propiedades = new ArrayList<Propiedad>();
		propiedades.add(new Propiedad("nombre", user.getNombre()));
		propiedades.add(new Propiedad("apellidos", user.getApellidos()));
		propiedades.add(new Propiedad("fechaNacimiento", dateFormat.format(user.getFechaNacimiento())));
		propiedades.add(new Propiedad("email", user.getEmail()));
		propiedades.add(new Propiedad("username", user.getUsername()));
		propiedades.add(new Propiedad("password", user.getPassword()));
		propiedades.add(new Propiedad("premium", user.isPremium().toString()));
		
		propiedades.add(new Propiedad("listasrep", obtenerCodigosListasRep(user.getListasReproduccion())));
		propiedades.add(new Propiedad("canciones", obtenerCodigosCanciones(user.getCancionesRecientes())));
		
		
		eUsuario.setPropiedades(propiedades);
		
		// Registrar la entidad
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		
		// Asignar UUID al a entidad utilizando la persistencia
		user.setCodigo(eUsuario.getId());
	
	}

	public void borrarUsuario(Usuario user) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(user.getCodigo());

		AdaptadorListaReproduccionTDS adaptadorLR = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		
		for (ListaReproduccion lr : user.getListasReproduccion()) {
			adaptadorLR.borrarListaReproduccion(lr);
		}
		
		servPersistencia.borrarEntidad(eUsuario);
		
	}

	public void modificarUsuario(Usuario user) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(user.getCodigo());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", user.getNombre());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apellidos", user.getApellidos());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechaNacimiento");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechaNacimiento", dateFormat.format(user.getFechaNacimiento()));
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", user.getEmail());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "username");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "username", user.getUsername());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password", user.getPassword());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "premium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "premium", user.isPremium().toString());
		
		
		// Modificar las propiedades objeto - Canciones // Playlist
		String listasReproduccion = obtenerCodigosListasRep(user.getListasReproduccion());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "listasrep");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "listasrep", listasReproduccion);
		
		String cancionesRecientes = obtenerCodigosCanciones(user.getCancionesRecientes());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "canciones");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "canciones", cancionesRecientes);
		
		
	}

	public Usuario recuperarUsuario(int codigo) {
		
		// Si la entidad esta en el pool la devuelve 
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// Si no lo esta, va a la base de datos
		Entidad eUsuario;
		String nombre;
		String apellidos;
		Date fechaNacimiento = null;
		String email;
		String username;
		String password;
		Boolean premium;
		
		List<ListaReproduccion> listasRep = new LinkedList<ListaReproduccion>();
		List<Cancion> canciones = new LinkedList<Cancion>();
		
		// Propiedades que no son objetos de la aplicacion
		
		eUsuario = servPersistencia.recuperarEntidad(codigo);
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		username = servPersistencia.recuperarPropiedadEntidad(eUsuario, "username");
		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		premium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		
		try {
			fechaNacimiento = dateFormat.parse( servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		}  catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
		Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, email, username, password, premium);
		usuario.setCodigo(codigo);
		
		// Antes de llamar a otros adaptadores, hay que añadir el Usuario al PoolDAO
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, usuario);
		
		
		// Recuperar propiedades que son objetos llamando a sus adaptadores --- Cancion(es) // Playlist
		
		listasRep = obtenerListasRepDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "listasrep"));
		for (ListaReproduccion lr : listasRep) {
			usuario.addListaReproduccion(lr);
		}
		
		canciones = obtenerCancionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "canciones"));
		for (Cancion c : canciones) {
			usuario.addCancionReciente(c);
		}
		
		
		return usuario;
	}
	

	public List<Usuario> recuperarTodosUsuarios() {
		
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();
		
		for (Entidad eUsuario:eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		
		return usuarios;
	}

	// Metodos auxiliares
	
	private String obtenerCodigosCanciones(LinkedList<Cancion> cancionesRecientes) {
		String aux = "";
		for (Cancion c : cancionesRecientes) {
			aux += c.getCodigo() + " ";
		}
		
		return aux.trim();
	}

	private String obtenerCodigosListasRep(LinkedList<ListaReproduccion> listasReproduccion) {
		String aux = "";
		for (ListaReproduccion lr : listasReproduccion) {
			aux += lr.getCodigo() + " ";
		}
		
		return aux.trim();
	}

	
	private List<Cancion> obtenerCancionesDesdeCodigos(String canciones) {
		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(canciones, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		
		while(strTok.hasMoreElements()) {
			listaCanciones.add(adaptadorC.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		
		
		return listaCanciones;
	}

	private List<ListaReproduccion> obtenerListasRepDesdeCodigos(String listasRep) {
		List<ListaReproduccion> listasReproduccion = new LinkedList<ListaReproduccion>();
		StringTokenizer strTok = new StringTokenizer(listasRep, " ");
		AdaptadorListaReproduccionTDS adaptadorLR = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		
		while(strTok.hasMoreElements()) {
			listasReproduccion.add(adaptadorLR.recuperarListaReproduccion(Integer.valueOf((String) strTok.nextElement())));
		}
		
		
		return listasReproduccion;
	}
	
	
	
	
	
	
	
}
