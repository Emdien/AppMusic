package umu.tds.apps.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.apps.persistencia.DAOException;
import umu.tds.apps.persistencia.FactoriaDAO;
import umu.tds.apps.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	private Map<String, Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = null;
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorU;
	
	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
	
	
	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorU = dao.getUsuarioDAO();
			usuarios = new HashMap<String, Usuario>();
			cargarCatalogo();
		} catch (DAOException ed) {
			ed.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		for (Usuario u : usuarios.values()) {
			lista.add(u);
		}
		return lista;
	}
	
	public Usuario getUsuario(int codigo) {
		for (Usuario u: usuarios.values())
			if (u.getCodigo() == codigo) return u;
		return null;
	}
	
	public Usuario getUsuario(String username) {
		return usuarios.get(username);
	}
	
	public void addUsuario(Usuario u) {
		usuarios.put(u.getUsername(), u);
	}
	
	public void removeUsuario(Usuario u) {
		usuarios.remove(u.getUsername());
	}
	
	
	// Metodos auxiliares
	
	private void cargarCatalogo() {
		List<Usuario> usuariosBD = adaptadorU.recuperarTodosUsuarios();
		for  (Usuario u : usuariosBD) {
			this.usuarios.put(u.getUsername(), u);
		}
	}
	
	
	
}
