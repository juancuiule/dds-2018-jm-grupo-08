package dominioTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import dominio.Categoria;
import dominio.Cliente;
import dominio.RepositorioCategorias;
import dominio.dispositivo.Dispositivo;
import dominio.transformadores.Punto;
import dominioTest.mocks.InterfazDeFabricaMock;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.TipoDeDocumento;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ClienteTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	Cliente clienteA;
	Cliente clienteB;
	InterfazDeFabricaMock interfazDeFabrica;

	RepositorioCategorias repo = RepositorioCategorias.getInstance();
	Categoria R1 = new Categoria("R1", 0, 150, 18.76, 0.644);
	Categoria R2 = new Categoria("R2", 150, 325, 35.32, 0.644);
	Categoria R3 = new Categoria("R3", 325, 400, 60.71, 0.681);
	Categoria R4 = new Categoria("R4", 400, 450, 71.74, 0.738);
	Categoria R5 = new Categoria("R5", 450, 500, 110.38, 0.794);
	Categoria R6 = new Categoria("R6", 500, 600, 220.75, 0.832);
	Categoria R7 = new Categoria("R7", 600, 700, 443.59, 0.851);
	Categoria R8 = new Categoria("R8", 700, 1400, 545.96, 0.851);
	Categoria R9 = new Categoria("R9", 1400, Integer.MAX_VALUE, 887.19, 0.851);

	@Before
	public void fixture() {
		repo.agregar(R1);
		repo.agregar(R2);
		repo.agregar(R3);
		repo.agregar(R4);
		repo.agregar(R5);
		repo.agregar(R6);
		repo.agregar(R7);
		repo.agregar(R8);
		repo.agregar(R9);
	}
	
	@Before
	public void generarCliente() {
		interfazDeFabrica = new InterfazDeFabricaMock();
		
		clienteA = new Cliente("Marjorie", "Shaw", TipoDeDocumento.DNI, 32516843, 42000000, LocalDate.now(),
				"7807 Samaritan Dr", "majshaw", "hudson",
				new ArrayList<Dispositivo>(Arrays.asList(
						new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0)),
						new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0)),
						new Dispositivo("Tostadora", new ComportamientoInteligente(interfazDeFabrica)))),
				false, new Punto(20, 15));

		
		clienteB = new Cliente("Arthur", "Howell", TipoDeDocumento.LC, 27662834, 42000001, LocalDate.now(),
		"8944 Red Saturn Dr", "arthhow", "hotshot", new ArrayList<Dispositivo>(), false, new Punto(40, 75));
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
		assertEquals(new Double(751), clienteA.consumo(periodoUltimoMes()));
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

		assertEquals("R9", clienteA.categoria().getNombre());
	}


	@Test
	public void elClienteBCambiaDeCategoria() {
		Dispositivo heladera = new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0));
		Dispositivo tostadora = new Dispositivo("Tostadora", new ComportamientoEstandar(1.0, 12.0));

		
		
		clienteB.agregarDispositivo(heladera);
		clienteB.agregarDispositivo(tostadora);

		
		clienteB.recategorizar();

		assertEquals("R6", clienteB.categoria().getNombre());
	}
	
	
	
	
	private Double periodoUltimoMes() {
		 double cantidad = ChronoUnit.DAYS.between( LocalDate.now().plusMonths(-1),LocalDate.now());
		 return cantidad;

	}
}
