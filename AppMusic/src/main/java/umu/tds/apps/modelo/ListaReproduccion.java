package umu.tds.apps.modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListaReproduccion {
	private String nombre;
	private int codigo;
	private ArrayList<Cancion> canciones;
	
	
	public ListaReproduccion(String nombre) {
		super();
		this.nombre = nombre;
		this.codigo = 0;
		this.canciones = new ArrayList<Cancion>();
	}


	public String getNombre() {
		return nombre;
	}


	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}


	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void addCancion(Cancion c) {
		canciones.add(c);
		
	}
	
	
	
}
