package dominioTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import dominio.Cliente;
import dominio.DispositivoEstandar;
import dominio.TipoDeDocumento;
import dominio.Dispositivo;
import dominio.DispositivoEstandar;

public class ClienteTest {

	Cliente clienteA;
	Cliente clienteB;

	@Before
	public void generarCliente() {
		clienteA = new Cliente("Marjorie", "Shaw", TipoDeDocumento.DNI, 32516843, 42000000, LocalDate.now(),
				"7807 Samaritan Dr", "majshaw", "hudson",
				new ArrayList<Dispositivo>(Arrays.asList(
						new DispositivoEstandar("Aire Acondicionado 2200 Frigorias", 1.350, 24),
						new DispositivoEstandar("Heladera con Freezer", 0.4, 24), new DispositivoEstandar("Tostadora", 1.0, 24))));

		clienteB = new Cliente("Arthur", "Howell", TipoDeDocumento.LC, 27662834, 42000001, LocalDate.now(),
				"8944 Red Saturn Dr", "arthhow", "hotshot", new ArrayList<Dispositivo>());
	}

	@Test
	public void elClienteATieneUnDispositivoEncendido() {
		assertTrue(clienteA.hayAlgunDispositivoEncendido());
	}

	@Test
	public void elClienteATieneDosDispositivosEncendidos() {
		assertEquals(2, clienteA.cantidadDeDispositivosEncendidos());
	}

	@Test
	public void elClienteATieneUnDispositivoApagado() {
		assertEquals(1, clienteA.cantidadDeDispositivosApagados());
	}

	@Test
	public void elClienteATieneTresDispositivos() {
		assertEquals(3, clienteA.cantidadDeDispositivos());
	}

/*	@Test
	public void consumoDelClienteA() {
		assertEquals(new Double(1.75), clienteA.consumo());
	}*/

	@Test
	public void elClienteBNoTieneUnDispositivoEncendido() {
		assertFalse(clienteB.hayAlgunDispositivoEncendido());
	}

/*	@Test
	public void elConsumoDelClienteB() {
		assertEquals(new Double(0), clienteB.consumo());
	}*/

	@Test
	public void elClienteACambiaDeCategoria() {
		DispositivoEstandar lavaplatos = new DispositivoEstandar("Heladera", 320.023, 24);
		DispositivoEstandar plancha = new DispositivoEstandar("Tostadora", 120.123, 24);
		clienteA.agregarDispositivo(lavaplatos);
		clienteA.agregarDispositivo(plancha);
		clienteA.recategorizar();
		assertEquals("R4", clienteA.categoria().getNombre());
	}

	@Test
	public void elClienteBCambiaDeCategoria() {
		DispositivoEstandar heladera = new DispositivoEstandar("Heladera con Freezer", 0.4, 24);
		DispositivoEstandar tostadora = new DispositivoEstandar("Tostadora", 1.0, 24);
		clienteB.agregarDispositivo(heladera);
		clienteB.agregarDispositivo(tostadora);
		clienteB.recategorizar();
		assertEquals("R1", clienteB.categoria().getNombre());
	}
}
