package umu.tds.apps.modelo;

import java.util.Date;
import java.util.LinkedList;

public class Usuario {
	
	private int codigo;

	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String email;
	private String username;
	private String password;
	private Boolean premium;
	
	private LinkedList<ListaReproduccion> listasReproduccion;
	private LinkedList<Cancion> cancionesRecientes;
	
	public Usuario(String nombre, String apellidos, Date fechaNacimiento, String email, String username,
			String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.username = username;
		this.password = password;
		this.codigo = 0;
		
		this.listasReproduccion = new LinkedList<ListaReproduccion>();
		this.cancionesRecientes = new LinkedList<Cancion>();
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	
	public LinkedList<ListaReproduccion> getListasReproduccion() {
		return listasReproduccion;
	}


	public void setListasReproduccion(LinkedList<ListaReproduccion> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}


	public LinkedList<Cancion> getCancionesRecientes() {
		return cancionesRecientes;
	}


	public void setCancionesRecientes(LinkedList<Cancion> cancionesRecientes) {
		this.cancionesRecientes = cancionesRecientes;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean isPremium() {
		return premium;
	}
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}


	public void addListaReproduccion(ListaReproduccion lr) {
		this.listasReproduccion.add(lr);
		
	}


	public void addCancionReciente(Cancion c) {
		this.cancionesRecientes.add(c);
		
	}
	
	
}
