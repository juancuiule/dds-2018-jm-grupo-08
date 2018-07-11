package dominioTest;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dominio.transformadores.Punto;
import dominio.transformadores.RepositorioTransformadores;
import dominio.transformadores.Transformador;
import dominio.transformadores.Zona;

public class TransformadorTest {
	Zona unaZonaDePrueba;
	@Before
	public void fixture() {
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(10, 10), true));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(10, 15), true));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(3, 15), false));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(10, 5), false));
		RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(5, 5), true));
		
		unaZonaDePrueba = new Zona(new Punto(10, 12), 330);
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
}
