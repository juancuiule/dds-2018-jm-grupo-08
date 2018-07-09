package dominioTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import dominio.Cliente;
import dominio.dispositivo.Dispositivo;
import dominioTest.mocks.InterfazDeFabricaMock;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.TipoDeDocumento;
import java.time.Period;

public class ClienteTest {

	Cliente clienteA;
	Cliente clienteB;
	InterfazDeFabricaMock interfazDeFabrica;

	@Before
	public void generarCliente() {
		interfazDeFabrica = new InterfazDeFabricaMock();

		clienteA = new Cliente("Marjorie", "Shaw", TipoDeDocumento.DNI, 32516843, 42000000, LocalDate.now(),
				"7807 Samaritan Dr", "majshaw", "hudson",
				new ArrayList<Dispositivo>(Arrays.asList(
						new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0)),
						new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0)),
						new Dispositivo("Tostadora", new ComportamientoInteligente(interfazDeFabrica)))),
				false);

		clienteB = new Cliente("Arthur", "Howell", TipoDeDocumento.LC, 27662834, 42000001, LocalDate.now(),
				"8944 Red Saturn Dr", "arthhow", "hotshot", new ArrayList<Dispositivo>(), false);
	}

	@Test
	public void elClienteATieneUnDispositivoEncendido() {
		clienteA.dispositivos().forEach(dispositivo -> dispositivo.encender());
		assertTrue(clienteA.hayAlgunDispositivoEncendido());
	}

	@Test
	public void elClienteATieneUnSoloDispositivoEncendido() {
		clienteA.dispositivos().forEach(dispositivo -> dispositivo.encender());
		// Se enciende solo la tostadora, el unico dispositivo inteligente
		assertEquals(1, clienteA.cantidadDeDispositivosEncendidos());
	}

	@Test
	public void elClienteATieneTresDispositivos() {
		assertEquals(3, clienteA.cantidadDeDispositivos());
	}

	@Test
	public void consumoDelClienteA() {
		assertEquals(new Double(1.75), clienteA.consumo(periodoUltimoMes()));
	}

	@Test
	public void elClienteBNoTieneUnDispositivoEncendido() {
		assertFalse(clienteB.hayAlgunDispositivoEncendido());
	}

	@Test
	public void elConsumoDelClienteB() {
		assertEquals(new Double(0), clienteB.consumo(periodoUltimoMes()));
	}

	@Test
	public void elClienteACambiaDeCategoria() {
		Dispositivo lavaplatos = new Dispositivo("Heladera", new ComportamientoEstandar(320.023, 12.0));
		Dispositivo plancha = new Dispositivo("Tostadora", new ComportamientoEstandar(120.123, 12.0));

		clienteA.agregarDispositivo(lavaplatos);
		clienteA.agregarDispositivo(plancha);
		clienteA.recategorizar();

		assertEquals("R4", clienteA.categoria().getNombre());
	}

	@Test
	public void elClienteBCambiaDeCategoria() {
		Dispositivo heladera = new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0));
		Dispositivo tostadora = new Dispositivo("Tostadora", new ComportamientoEstandar(1.0, 12.0));
		clienteB.agregarDispositivo(heladera);

		clienteB.agregarDispositivo(tostadora);
		clienteB.recategorizar();

		assertEquals("R1", clienteB.categoria().getNombre());
	}

	private Period periodoUltimoMes() {
		return Period.between(LocalDate.now().plusMonths(-1), LocalDate.now());
	}
}
