package umu.tds.apps.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.modelo.Cancion;
import umu.tds.apps.modelo.ListaReproduccion;

public class AdaptadorListaReproduccionTDS implements IAdaptadorListaReproduccionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaReproduccionTDS unicaInstancia = null;
	
	public static AdaptadorListaReproduccionTDS getUnicaInstancia() {
		if (unicaInstancia == null) return new AdaptadorListaReproduccionTDS();
		else return unicaInstancia;
	}
	
	private AdaptadorListaReproduccionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void registrarListaReproduccion(ListaReproduccion listaRep) {
		Entidad eListaRep;
		boolean existe = true;
		
		
		try {
			eListaRep = servPersistencia.recuperarEntidad(listaRep.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		
		if (existe) return;
		
		
		// Registrar primero los atributos que son objetos
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion c : listaRep.getCanciones()) {
			adaptadorCancion.registrarCancion(c);
		}		
		
		eListaRep = new Entidad();
		eListaRep.setNombre("listarep");
		
		ArrayList<Propiedad> propiedades = new ArrayList<Propiedad>();
		propiedades.add(new Propiedad("nombre", listaRep.getNombre()));
		propiedades.add(new Propiedad("canciones", obtenerCodigosCanciones(listaRep.getCanciones())));
		
		
		eListaRep.setPropiedades(propiedades);
		
		eListaRep = servPersistencia.registrarEntidad(eListaRep);
		
		listaRep.setCodigo(eListaRep.getId());
		
	}


	public void borrarListaReproduccion(ListaReproduccion listaRep) {
		
		Entidad eListaRep = servPersistencia.recuperarEntidad(listaRep.getCodigo());
		
		servPersistencia.borrarEntidad(eListaRep);
		
		// (Gonzalo): Creo que no hace falta borrar las canciones?
		
		
	}

	public void modificarListaReproduccion(ListaReproduccion listaRep) {
		
		Entidad eListaRep = servPersistencia.recuperarEntidad(listaRep.getCodigo());
		
		servPersistencia.eliminarPropiedadEntidad(eListaRep, "nombre");
		servPersistencia.anadirPropiedadEntidad(eListaRep, "nombre", listaRep.getNombre());
		
		String canciones = obtenerCodigosCanciones(listaRep.getCanciones());
		servPersistencia.eliminarPropiedadEntidad(eListaRep, "canciones");
		servPersistencia.anadirPropiedadEntidad(eListaRep, "canciones", canciones);		
		
	}

	public ListaReproduccion recuperarListaReproduccion(int codigo) {
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo)) {
			return (ListaReproduccion) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		}
		
		Entidad eListaRep;
		List<Cancion> canciones = new LinkedList<Cancion>();
		String nombre;
		
		eListaRep = servPersistencia.recuperarEntidad(codigo);
		
		// Recuperar propiedades no objeto
		nombre = servPersistencia.recuperarPropiedadEntidad(eListaRep, "nombre");
		
		ListaReproduccion listaRep = new ListaReproduccion(nombre);
		listaRep.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, listaRep);
		
		// Recuperar propiedades objeto
		canciones = obtenerCancionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eListaRep, "canciones"));
		
		for (Cancion c : canciones) 
			listaRep.addCancion(c);
		
		
		return listaRep;
	}

	public List<ListaReproduccion> recuperarTodasListasReproduccion() {
		List<Entidad> eListasRep = servPersistencia.recuperarEntidades("listarep");
		List<ListaReproduccion> listasRep = new LinkedList<ListaReproduccion>();
		
		for (Entidad eListaRep : eListasRep) {
			listasRep.add(recuperarListaReproduccion(eListaRep.getId()));
		}
		
		return listasRep;
	}

	
	// Metodos auxiliares   ---  Hacer con lambdas ?
	
	
	private String obtenerCodigosCanciones(LinkedList<Cancion> canciones) {
		String aux = "";
		for (Cancion c : canciones) {
			aux += c.getCodigo() + " ";
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
	
	
}
