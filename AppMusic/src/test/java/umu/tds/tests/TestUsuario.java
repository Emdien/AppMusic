package umu.tds.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;
import umu.tds.apps.modelo.Descuento;
import umu.tds.apps.modelo.ListaReproduccion;
import umu.tds.apps.modelo.Usuario;

public class TestUsuario {

	private Usuario usuario;
	
	/*
	 * getCodigo
	 * getListasReproduccion
	 * setListasReproduccion
	 * getCancionesRecientes
	 * setCancionesRecientes
	 * addListaReproduccion
	 * addCancionReciente
	 * crearLista
	 * addCancionToLista
	 * removeListaReproduccion
	 * getPrecioBase
	 * getDescuento
	 * realizarPago
	 */
	
	@SuppressWarnings("deprecation")
	@Before
	public void inicializar() {
		// Uso constructor Date deprecado por simplicidad
		usuario = new Usuario("Gonzalo", "Nicolas Martinez", new Date(1998, 3, 18), "gonzalo.nicolasm@um.es", "Gon", "1234", false);
		
		usuario.setCodigo(1234); // Esto normalmente seria un código generado aleatoriamente. Por simplicidad, utilizo un código estático.
	}
	
	@Test
	public void testGetCodigo() {
		assertTrue(usuario.getCodigo() == 1234);
	}
	
	@Test
	public void testGetListasReproduccion() {
		LinkedList<ListaReproduccion> listas = usuario.getListasReproduccion();
		
		
		// El usuario no deberia de tener listas. Tiene que estar vacia
		assertTrue(listas.isEmpty());
		
	}
	
	@Test
	public void testSetListasReproduccion() {
		LinkedList<ListaReproduccion> listas = new LinkedList<>();
		
		listas.add(new ListaReproduccion("test"));
		
		usuario.setListasReproduccion(listas);
		
		// Tiene una lista de reproduccion. No debe de estar vacia
		assertTrue(!usuario.getListasReproduccion().isEmpty());
	}
	
	@Test
	public void testGetCancionesRecientes() {
		
		// No tiene canciones recientes. Debe de ser vacia
		assertTrue(usuario.getCancionesRecientes().isEmpty());
	}
	
	@Test
	public void testSetCancionesRecientes() {
		LinkedList<Cancion> canciones = new LinkedList<>();
		
		canciones.add(new Cancion("test", "testpath", "testInterprete", "TEST", 0));
		
		usuario.setCancionesRecientes(canciones);
		// El usuario tiene una cancion reciente. No debe estar vacia
		assertTrue(!usuario.getCancionesRecientes().isEmpty());
	}
	
	@Test
	public void testAddListaReproduccion() {
		ListaReproduccion lr = new ListaReproduccion("test");
		ArrayList<Cancion> canciones = new ArrayList<>();
		
		canciones.add(new Cancion("test", "testpath", "testInterprete", "TEST", 0));
		
		usuario.addListaReproduccion(lr, canciones);
		
		// Se ha añadido una lista de reproduccion con una cancion. No debería de ser vacia
		assertTrue(!usuario.getListasReproduccion().isEmpty());
	}
	
	@Test
	public void testAddCancionReciente() {
		
		Cancion c = new Cancion("test", "testpath", "testInterprete", "TEST", 0);
		
		usuario.addCancionReciente(c);
		// El usuario tiene una cancion reciente. No debe estar vacia
		assertTrue(!usuario.getCancionesRecientes().isEmpty());
		
		
	}
	
	@Test
	public void testCrearLista() {
		
		ListaReproduccion lr = usuario.crearLista("test");
		
		// crearLista devuelve una lista de reproduccion. Esta no debería de ser nula y debe tener el nombre indicado.
		assertTrue(lr != null && lr.getNombre().equals("test"));
	}
	
	@Test
	public void testAddCancionToLista() {
		Cancion c = new Cancion("test", "testpath", "testInterprete", "TEST", 0);
		ListaReproduccion lr = new ListaReproduccion("test");
		
		lr = usuario.addCancionToLista(lr, c);
		
		// Se ha creado una lista vacia, y se le ha añadido una cancion. No deberia de ser null y no deberia de estar vacia.
		assertTrue(lr != null && !lr.getCanciones().isEmpty());
	}
	
	@Test
	public void removeListaReproduccion() {
		
		ListaReproduccion lr = usuario.crearLista("test");
		usuario.addListaReproduccion(lr);
		
		// Se crea una lista y se elimina
		assertTrue(usuario.removeListaReproduccion(lr));
	}
	
	@Test
	public void testGetPrecioBase() {
		
		assertTrue(Usuario.getPreciobase() == 5.0);
	}
	
	@Test
	public void testGetDescuento() {
		Descuento d = usuario.getDescuento();
		
		assertTrue(d != null && d.getValorDescuento() == 0.20);
	}
	
	@Test
	public void testRealizarPago() {
		Double valor = usuario.realizarPago();
		
		// Valor se refiere al valor que se descuenta al precio base.
		assertTrue(usuario.isPremium() && valor == 1.0);
	}
	

	
	
	

}
