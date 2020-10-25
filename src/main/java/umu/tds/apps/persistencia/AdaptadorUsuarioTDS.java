package umu.tds.apps.persistencia;

import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.modelo.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null) return new AdaptadorUsuarioTDS();
		else return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void registrarUsuario(Usuario user) {

	}

	public void borrarUsuario(Usuario user) {
		// TODO Auto-generated method stub
		
	}

	public void modificarUsuario(Usuario user) {
		// TODO Auto-generated method stub
		
	}

	public Usuario recuperarUsuario(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
}
