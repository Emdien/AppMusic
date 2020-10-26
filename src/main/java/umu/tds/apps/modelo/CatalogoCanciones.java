package umu.tds.apps.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import umu.tds.apps.persistencia.DAOException;
import umu.tds.apps.persistencia.FactoriaDAO;
import umu.tds.apps.persistencia.IAdaptadorCancionDAO;

public class CatalogoCanciones {

	private Map<String, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = null;
	private FactoriaDAO dao;
	private IAdaptadorCancionDAO adaptadorC;
	
	
	public static CatalogoCanciones getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoCanciones();
		return unicaInstancia;
	}
	
	private CatalogoCanciones() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorC = dao.getCancionDAO();
			canciones = new HashMap<String, Cancion>();
			cargarCatalogo();
		} catch (DAOException ed) {
			ed.printStackTrace();
		}
	}

	public List<Cancion> getCanciones() {
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		
		for (Cancion c : canciones.values()) {
			lista.add(c);
		}
		
		return lista;
	}
	
	
	public Cancion getCancion(int codigo) {
		for (Cancion c : canciones.values()) {
			if (c.getCodigo() == codigo) return c;
		}
		
		return null;
	}
	
	public Cancion getCancion(String titulo) {
		return canciones.get(titulo);
	}
	
	public void addCancion(Cancion c) {
		canciones.put(c.getTitulo(), c);
	}
	
	public void removeCancion(Cancion c) {
		canciones.remove(c.getTitulo());
	}
	
	
	
	// Metodos auxiliares
	
	private void cargarCatalogo() {
		List<Cancion> cancionesBD = adaptadorC.recuperarTodasCanciones();
		for (Cancion c : cancionesBD) {
			canciones.put(c.getTitulo(), c);
		}
	}
	
	
}
