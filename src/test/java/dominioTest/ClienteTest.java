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

	@Test
	public void elClienteATieneUnDispositivoEncendido() {
		assertTrue(clienteA.hayAlgunDispositivoEncendido());
	}

	@Test
	public void elClienteATieneDosDispositivosEncendidos() {
		assertEquals(new Integer(2), clienteA.cantidadDeDispositivosEncendidos());
	}

	@Test
	public void elClienteATieneUnDispositivoApagado() {
		assertEquals(new Integer(1), clienteA.cantidadDeDispositivosApagados());
	}

	@Test
	public void elClienteATieneTresDispositivos() {
		assertEquals(new Integer(3), clienteA.cantidadDeDispositivos());
	}

	@Test
	public void consumoDelClienteA() {
		assertEquals(new Double(1.75), clienteA.consumo());
	}

	@Test
	public void elClienteBNoTieneUnDispositivoEncendido() {
		assertFalse(clienteB.hayAlgunDispositivoEncendido());
	}

	@Test
	public void elConsumoDelClienteB() {
		assertEquals(new Double(0), clienteB.consumo());
	}

	@Test
	public void elClienteACambiaDeCategoria() {
		Dispositivo lavaplatos = new Dispositivo("Heladera", 320.023, true);
		Dispositivo plancha = new Dispositivo("Tostadora", 120.123, true);
		clienteA.agregarDispositivo(lavaplatos);
		clienteA.agregarDispositivo(plancha);
		clienteA.recategorizar();
		assertEquals("R4", clienteA.categoria().getNombre());
	}

	@Test
	public void elClienteBCambiaDeCategoria() {
		Dispositivo heladera = new Dispositivo("Heladera con Freezer", 0.4, true);
		Dispositivo tostadora = new Dispositivo("Tostadora", 1.0, true);
		clienteB.agregarDispositivo(heladera);
		clienteB.agregarDispositivo(tostadora);
		clienteB.recategorizar();
		assertEquals("R1", clienteB.categoria().getNombre());
	}
}
