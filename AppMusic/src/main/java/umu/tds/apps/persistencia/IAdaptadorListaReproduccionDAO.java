package umu.tds.apps.persistencia;

import java.util.List;

import umu.tds.apps.modelo.ListaReproduccion;



public interface IAdaptadorListaReproduccionDAO {
	public void registrarListaReproduccion(ListaReproduccion listaRep);
	public void borrarListaReproduccion(ListaReproduccion listaRep);
	public void modificarListaReproduccion(ListaReproduccion listaRep);
	public ListaReproduccion recuperarListaReproduccion(int codigo);
	public List<ListaReproduccion> recuperarTodasListasReproduccion();
}
