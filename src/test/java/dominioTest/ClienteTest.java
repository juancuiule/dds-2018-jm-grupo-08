package dominioTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import dominio.Cliente;
import dominio.Dispositivo;
import dominio.TipoDeDocumento;

public class ClienteTest {

	Cliente clienteA;
	Cliente clienteB;

	@Before
	public void generarCliente() {
		clienteA = new Cliente("Marjorie", "Shaw", TipoDeDocumento.DNI, 32516843, 42000000, LocalDate.now(),
				"7807 Samaritan Dr", "majshaw", "hudson",
				new ArrayList<Dispositivo>(Arrays.asList(
						new Dispositivo("Aire Acondicionado 2200 Frigorias", 1.350, true),
						new Dispositivo("Heladera con Freezer", 0.4, true), new Dispositivo("Tostadora", 1.0, false))));

		clienteB = new Cliente("Arthur", "Howell", TipoDeDocumento.LC, 27662834, 42000001, LocalDate.now(),
				"8944 Red Saturn Dr", "arthhow", "hotshot", new ArrayList<Dispositivo>());
	}

	// Tests con A
	@Test
	public void elClienteATieneUnDispositivoEncendido() {
		assertTrue(clienteA.hayAlgunDispositivoEncendido());
	}

	@Test
	public void elClienteATieneDosDispositivosEncendidos() {
		assertEquals(new Long(2), clienteA.cantidadDeDispositivosEncendidos());
	}

	@Test
	public void elClienteATieneUnDispositivoApagado() {
		assertEquals(new Long(1), clienteA.cantidadDeDispositivosApagados());
	}

	@Test
	public void elClienteATieneTresDispositivos() {
		assertEquals(new Integer(3), clienteA.cantidadDeDispositivos());
	}
	
	@Test
	public void consumoDelClienteA() {
		assertEquals(new Double(1.75), clienteA.consumo());
	}

	// Tests con B
	@Test
	public void elClienteBNoTieneUnDispositivoEncendido() {
		assertFalse(clienteB.hayAlgunDispositivoEncendido());
	}

	@Test
	public void elConsumoDelClienteB() {
		assertEquals(new Double(0), clienteB.consumo());
		
		/*AGREGAR TEST DE RECATEGORIZAR*/
	}
}
