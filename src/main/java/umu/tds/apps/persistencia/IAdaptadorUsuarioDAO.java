package umu.tds.apps.persistencia;

import java.util.List;

import umu.tds.apps.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario user);
	public void borrarUsuario(Usuario user);
	public void modificarUsuario(Usuario user);
	public Usuario recuperarUsuario(int codigo);
	public List<Usuario> recuperarTodosUsuarios();
	
}
