package dominioTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dominio.Categoria;

public class CategoriaTest {

	Categoria categoriaDePrueba;

	@Before
	public void generarCategoria() {
		categoriaDePrueba = new Categoria("R8", 700, 1400, 545.96, 0.851);
	}

	@Test
	public void correspondeCategoria_DadoUnConsumoQueCorrespondeAlaCategoria_true() {
		assertEquals("Falla - el consumo deberia corresponder a esta categoria",
				categoriaDePrueba.correspondeCategoria(800d), true);
	}

	@Test
	public void correspondeCategoria_DadoUnConsumoQueNoCorrespondeAlaCategoria_false() {
		assertEquals("Falla - el consumo deberia corresponder a esta categoria",
				categoriaDePrueba.correspondeCategoria(1800d), false);
	}

	@Test
	public void cargoFijoDeCategoria() {
		assertEquals(2183.84, categoriaDePrueba.cargoFijo(4), 0);
	}

	@Test
	public void cargoVariableDeCategoria() {
		assertEquals(1659.45, categoriaDePrueba.cargoVariable(1950.0), 0);
	}
}
