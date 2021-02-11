import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;
import umu.tds.apps.modelo.ListaReproduccion;
import umu.tds.apps.modelo.Usuario;
import umu.tds.apps.persistencia.AdaptadorUsuarioTDS;

public class TestDriver {
	
	ControladorAppMusic driver;

	@Before
	public void inicializar() {
		driver = ControladorAppMusic.getUnicaInstancia();
	}
	
	@Test
	public void testRegistrarUsuario() {
		assertFalse(driver.registrarUsuario("Gonzalo", "Nicolas", new Date(), "gonzalo.nicolasm@um.es", "Emdien", "1234"));
	}
	
	@Test
	public void testLogin() {
		assertTrue(driver.login("Emdien", "1234"));
	}

	

	@Test
	public void testCrearNuevaLista() {
		ListaReproduccion lr = driver.crearNuevaLista("Lista de prueba");
		List<ListaReproduccion> listas = driver.getAllListasReproduccion();
		
		assertTrue(lr != null && listas.size()>0);
	}

	@Test
	public void testAddCancionToLista() {
		
		List<Cancion> canciones = driver.getAllCanciones();
		Cancion c = canciones.get(0);
		ListaReproduccion testLista = driver.getAllListasReproduccion().get(0);
		
		ListaReproduccion lr = driver.addCancionToLista(testLista, c);

		
		assertTrue(lr.getCanciones().size()>0);
	}

	/*@Test
	public void testRemoveListaReproduccion() {
		
		ListaReproduccion lr = driver.getAllListasReproduccion().get(0);
		assertTrue(driver.removeListaReproduccion(lr));
	}*/

	@Test
	public void testGetAllCanciones() {
		List<Cancion> canciones = driver.getAllCanciones();
		assertTrue(canciones != null);
	}

	@Test
	public void testReproducirCancion() {
		Cancion c = driver.getAllCanciones().get(0);
		driver.reproducirCancion(c);
	}


	@Test
	public void testBuscarCanciones() {
		List<Cancion> canciones = driver.buscarCanciones("", "", "JAZZ");
		if (!canciones.isEmpty()) {
			for (Cancion c : canciones) 
				System.out.println(c.getTitulo());
		}
		
		assertTrue(!canciones.isEmpty());
	}

	@Test
	public void testGetCancionesRecientes() {
		List<Cancion> recientes = driver.getCancionesRecientes();
		System.out.println(recientes.size());
		
		for (Cancion c : recientes) {
			System.out.println(c.getTitulo());
		}
		
		assertTrue(!recientes.isEmpty());
	}


	@Test
	public void testGetAllListasReproduccion() {
		assertTrue(driver.getAllListasReproduccion() != null);
	}

	/*@Test
	public void testPrintPDF() throws DocumentException {
		assertTrue(driver.printPDF());
	}*/

	@Test
	public void testGetCancionesMasReproducidas() {
		List<Cancion> masReproducidas = driver.getCancionesMasReproducidas();
		System.out.println(masReproducidas.size());
		
		for(Cancion c : masReproducidas) {
			System.out.println(c.getTitulo());
			
		}
		
		assertTrue(!masReproducidas.isEmpty());
		
	}

}
