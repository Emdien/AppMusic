import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import umu.tds.apps.controlador.ControladorAppMusic;

public class TestDriver {
	
	ControladorAppMusic driver;

	@Before
	public void inicializar() {
		driver = ControladorAppMusic.getUnicaInstancia();
	}
	
	
	@Test
	public void testLogin() {
		assertTrue(driver.login("Emdien", "1234"));
	}

	@Test
	public void testRegistrarUsuario() {
		assertTrue(driver.registrarUsuario("Gonzalo", "Nicolas", new Date(), "gonzalo.nicolasm@um.es", "Emdien", "1234"));
	}

	@Test
	public void testCrearNuevaLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCancionToLista() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveListaReproduccion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllCanciones() {
		fail("Not yet implemented");
	}

	@Test
	public void testReproducirCancion() {
		fail("Not yet implemented");
	}

	@Test
	public void testPausarCancion() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarCanciones() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCancionesRecientes() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCancionReciente() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllListasReproduccion() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintPDF() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCancionesMasReproducidas() {
		fail("Not yet implemented");
	}

}
