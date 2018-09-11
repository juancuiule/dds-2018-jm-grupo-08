package dominioTest;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import dominio.Cliente;
import dominio.TipoDeDocumento;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.Dispositivo;
import dominio.transformadores.Punto;
import dominio.transformadores.RepositorioTransformadores;
import dominio.transformadores.Transformador;
import dominio.transformadores.Zona;

public class TransformadorTest {
	Zona unaZonaDePrueba;
	Cliente clienteDePrueba;
	
	@Before
	public void fixture() {
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(10, 10), true));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(10, 15), true));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(3, 15), false));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(10, 5), false));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(5, 5), true));
		
		unaZonaDePrueba = new Zona(new Punto(10, 12), 330);
		clienteDePrueba = new Cliente("Marjorie", "Shaw", TipoDeDocumento.DNI, 32516843, 42000000, LocalDate.now(),
				"7807 Samaritan Dr", "majshaw", "hudson",
				new ArrayList<Dispositivo>(Arrays.asList(
						new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0)))),
				false, new Punto(9, 10));
	}

	@Test
	public void elPrimeroPertenece() {
		Transformador primero = new Transformador(new Punto(10, 10), true);
		assertTrue(unaZonaDePrueba.estaDentroDelRadio(primero));
	}
	
	@Test
	public void laZonaDePruebaTiene2Transformadores() {
		assertEquals(2, unaZonaDePrueba.transformadoresDeLaZona().count());
	}
	
	
	@Test
	public void elPrimeroEsElTransformadorMasCercano() {
		Transformador adecuado = RepositorioTransformadores.getInstance().transformadorMasCercano(clienteDePrueba);
		assertEquals(new Double(10), adecuado.getPunto().getPosLong());
	}
	
	
	@Test
	public void transformadorCalculaBienConsumo() {
		clienteDePrueba.asignarTransformador();
		Transformador transformadorCorrespondiente = new Transformador(new Punto(10, 10), true);
		assertEquals(clienteDePrueba.consumo(periodoUltimoMes()), transformadorCorrespondiente.consumo(periodoUltimoMes()));
	}
	
	private Double periodoUltimoMes() {
		 double cantidad = ChronoUnit.DAYS.between( LocalDate.now().plusMonths(-1),LocalDate.now());
		 return cantidad;
	
}
	
}
