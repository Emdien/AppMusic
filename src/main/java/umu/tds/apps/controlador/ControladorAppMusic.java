package umu.tds.apps.controlador;

import umu.tds.apps.persistencia.DAOException;
import umu.tds.apps.persistencia.FactoriaDAO;
import umu.tds.apps.persistencia.IAdaptadorCancionDAO;
import umu.tds.apps.persistencia.IAdaptadorListaReproduccionDAO;
import umu.tds.apps.persistencia.IAdaptadorUsuarioDAO;

import java.util.Date;

import umu.tds.apps.modelo.*;

public class ControladorAppMusic {

	private static ControladorAppMusic unicaInstancia;
	
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorListaReproduccionDAO adaptadorListaReproduccion;
	
	private CatalogoCanciones catalogoCanciones;
	private CatalogoUsuarios catalogoUsuarios;
	
	private Usuario usuarioActual;			// Para mantener la sesion del usuario
	
	// Patron singleton
	public static ControladorAppMusic getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new ControladorAppMusic();
		return unicaInstancia;
	}
	
	private ControladorAppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
	}
	
	
	// Método de Login (Usuario / Contraseña)
	
	public boolean login(String usuario, String password) {
		Usuario user = catalogoUsuarios.getUsuario(usuario);
		if (user != null && user.getPassword().equals(password)){
			this.usuarioActual = user;
			return true;
		}
		return false;
	}
	
	// Método de Registro
	
	public boolean registrarUsuario(String nombre, String apellidos, Date fechaNacim, String email, 
								String username, String password) {
		
		// Si ya está registrado, no procedemos
		
		if (catalogoUsuarios.getUsuario(username)!= null) return false;
		
		
		Usuario user = new Usuario(nombre, apellidos, fechaNacim, email, username, password, false);
		adaptadorUsuario.registrarUsuario(user);
		catalogoUsuarios.addUsuario(user);
		
		
		return true;
	}
	
	// Método para Eliminar un Usuario
	
	public boolean borrarUsuario(Usuario user) {
		
		// Si no existe el usuario, no se procede
		
		if (catalogoUsuarios.getUsuario(user.getUsername()) == null) return false;
		
		adaptadorUsuario.borrarUsuario(user);
		catalogoUsuarios.removeUsuario(user);
		return true;
		
	}
	
	// Método para Obtener usuario actual (sesion)
	
	public Usuario getUsuarioActual() {
		return this.usuarioActual;
	}
	
	// Método para crear una Lista de reproduccion
	
	public boolean crearNuevaLista(String nombre) {
		
		// Primero delego en el usuario para que cree la lista
		
		ListaReproduccion lr = usuarioActual.crearLista(nombre);
		
		// Una vez creada, la persisto en la base de datos
		
		adaptadorListaReproduccion.registrarListaReproduccion(lr);
		if (lr != null) return true;
		return false;
	}
	
	// Método para añadir una cancion a una Lista de reproduccion - Version (String, Cancion)
	
	public boolean addCancionToLista(String nombreLista, Cancion cancion) {
		ListaReproduccion lr = usuarioActual.addCancioToLista(nombreLista, cancion);
		
		if (lr != null) {
			adaptadorListaReproduccion.modificarListaReproduccion(lr);
			return true;
		}
		return false;
	}
	
	// Método para añadir una cancion a una Lista de reproduccion - Version (String, String)
	
	public boolean addCancionToLista(String nombreLista, String nombreCancion) {
		
		Cancion cancion = catalogoCanciones.getCancion(nombreCancion);
		return addCancionToLista(nombreLista, cancion);
		
	}
	
	
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
		
	}
	
	
	
}
