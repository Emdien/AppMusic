package umu.tds.apps.modelo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Usuario {
	
	private static final Double precioBase = 5.0;		// 9-11-2020 Precio base de premium
	
	
	private int codigo;

	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String email;
	private String username;
	private String password;
	private Boolean premium = false;
	
	private LinkedList<ListaReproduccion> listasReproduccion;
	private LinkedList<Cancion> cancionesRecientes;
	
	private Descuento descuento;
	
	public Usuario(String nombre, String apellidos, Date fechaNacimiento, String email, String username,
			String password, Boolean premium) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.username = username;
		this.password = password;
		this.codigo = 0;
		this.premium = premium;
		this.listasReproduccion = new LinkedList<ListaReproduccion>();
		this.cancionesRecientes = new LinkedList<Cancion>();
		
		this.descuento = generarDescuento();
	}
	
	
	private Descuento generarDescuento() {
		
		Descuento aux = null;
		
		// Caso DescuentoEdad
		aux = new DescuentoEdad().generarDescuento(this);	// Puedo utilizar this aqui? Si no, paso solo la fecha de nacimiento
		if (aux != null) return aux;
		
		// Caso DescuentoTemporal
		aux = new DescuentoTemporal().generarDescuento(this);
		if (aux != null) return aux;

		return aux;
		
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


	public void addListaReproduccion(ListaReproduccion lr, ArrayList<Cancion> cancionesPlaylist) {
		lr.setCanciones(cancionesPlaylist);
		this.listasReproduccion.add(lr);
		
	}


	public void addCancionReciente(Cancion c) {
		this.cancionesRecientes.add(c);
		
	}
	
	public ListaReproduccion crearLista(String nombre) {
		ListaReproduccion lr = new ListaReproduccion(nombre);
		
		// Comprueba si ya existe una lista con es nombre
		// En caso de que exista, se modificara esa lista
		
		for(ListaReproduccion l : listasReproduccion) {
			if (l.getNombre().equals(lr.getNombre()))
				return l;
		}
		
		
		//addListaReproduccion(lr);
		
		
		
		return lr;
		
		
		
	}
	
	public ListaReproduccion addCancionToLista(ListaReproduccion lr, Cancion cancion) {

		lr.addCancion(cancion);

		return lr;
	}
	
	public boolean removeListaReproduccion(ListaReproduccion lr) {
		
		if (listasReproduccion.contains(lr)) {
			listasReproduccion.remove(lr);
			return true;
		}
		
		return false;
		
	}


	public static Double getPreciobase() {
		return precioBase;
	}


	public Descuento getDescuento() {
		return descuento;
	}
	
	
	public Double realizarPago() {
		this.setPremium(true);
		return this.descuento.calcDescuento();
	}
	
	public Double getPrecio() {
		return this.descuento.calcDescuento();
	}


	public void addListaReproduccion(ListaReproduccion lr) {
		this.listasReproduccion.add(lr);
		
	}
	
}
