package umu.tds.apps.persistencia;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.modelo.Cancion;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;
	
	public static AdaptadorCancionTDS getUnicaInstancia() {
		if (unicaInstancia == null) return new AdaptadorCancionTDS();
		else return unicaInstancia;
	}
	
	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void registrarCancion(Cancion cancion) {
		Entidad eCancion;
		boolean existe = true;
		
		
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		
		if (existe) return;
		
		eCancion = new Entidad();
		eCancion.setNombre("cancion");
		
		ArrayList<Propiedad> propiedades = new ArrayList<Propiedad>();
		propiedades.add(new Propiedad("titulo", cancion.getTitulo()));
		propiedades.add(new Propiedad("rutaFichero", cancion.getRutaFichero()));
		propiedades.add(new Propiedad("interprete", cancion.getInterprete()));
		propiedades.add(new Propiedad("estilo", cancion.getEstilo()));
		propiedades.add(new Propiedad("numReproducciones", String.valueOf(cancion.getNumReproducciones())));
		
		eCancion.setPropiedades(propiedades);
		
		eCancion = servPersistencia.registrarEntidad(eCancion);
		cancion.setCodigo(eCancion.getId());
		
		
	}

	public void borrarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		
		servPersistencia.borrarEntidad(eCancion);
		
	}

	public void modificarCancion(Cancion cancion) {
		
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		
		servPersistencia.eliminarPropiedadEntidad(eCancion, "titulo");
		servPersistencia.anadirPropiedadEntidad(eCancion, "titulo", cancion.getTitulo());
		
		servPersistencia.eliminarPropiedadEntidad(eCancion, "rutaFichero");
		servPersistencia.anadirPropiedadEntidad(eCancion, "rutaFichero", cancion.getRutaFichero());
		
		servPersistencia.eliminarPropiedadEntidad(eCancion, "interprete");
		servPersistencia.anadirPropiedadEntidad(eCancion, "interprete", cancion.getInterprete());
		
		servPersistencia.eliminarPropiedadEntidad(eCancion, "estilo");
		servPersistencia.anadirPropiedadEntidad(eCancion, "estilo", cancion.getEstilo());
		
		servPersistencia.eliminarPropiedadEntidad(eCancion, "numReproducciones");
		servPersistencia.anadirPropiedadEntidad(eCancion, "numReproducciones", String.valueOf(cancion.getNumReproducciones()));
		
	
	}

	public Cancion recuperarCancion(int codigo) {
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Cancion) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eCancion;
		String titulo;
		String rutaFichero;
		String interprete;
		String estilo;
		int numReproducciones;
		
		eCancion = servPersistencia.recuperarEntidad(codigo);
		
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutaFichero");
		interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");
		estilo = servPersistencia.recuperarPropiedadEntidad(eCancion, "estilo");
		numReproducciones = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "numReproducciones"));
		
		Cancion cancion = new Cancion(titulo, rutaFichero, interprete, estilo, numReproducciones);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, cancion);
		
		return cancion;

	}

	public List<Cancion> recuperarTodasCanciones() {
		
		List<Entidad> eCanciones = servPersistencia.recuperarEntidades("cancion");
		List<Cancion> canciones = new LinkedList<Cancion>();
		
		for (Entidad eCancion:eCanciones) {
			canciones.add(recuperarCancion(eCancion.getId()));
		}
		return canciones;
	}
	
	
}
