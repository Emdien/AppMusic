package umu.tds.apps.persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.sun.tools.javac.parser.ReferenceParser.ParseException;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.modelo.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null) return new AdaptadorUsuarioTDS();
		else return unicaInstancia;
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
		// TODO
		
		
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
		
		eUsuario.setPropiedades(propiedades);
		
		// Registrar la entidad
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		
		// Asignar UUID al a entidad utilizando la persistencia
		user.setCodigo(eUsuario.getId());
	
	}

	public void borrarUsuario(Usuario user) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(user.getCodigo());
		
		servPersistencia.borrarEntidad(eUsuario);
		
		
		// (Gonzalo): Borrar las playlist tambien? Quizas llamar a su adaptador para ello
		
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
		
		
		//TODO: Modificar las propiedades objeto - Cancion // Playlist
		
		
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
		
		// Propiedades que no son objetos de la aplicacion
		
		eUsuario = servPersistencia.recuperarEntidad(codigo);
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		username = servPersistencia.recuperarPropiedadEntidad(eUsuario, "username");
		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium");
		premium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		
		try {
			fechaNacimiento = dateFormat.parse( servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		}  catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, email, username, password);
		usuario.setCodigo(codigo);
		
		// Antes de llamar a otros adaptadores, hay que añadir el Usuario al PoolDAO
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, usuario);
		
		
		// Recuperar propiedades que son objetos llamando a sus adaptadores --- Cancion(es) // Playlist
		
		// TODO
		
		
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

	
	
	
	
	
	
	
}
