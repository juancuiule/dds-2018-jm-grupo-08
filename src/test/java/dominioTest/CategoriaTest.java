package dominioTest;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import dominio.Categoria;
import dominio.RepositorioCategorias;

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
	public void repoDarCategoria_R1_dadoConsumoQueCorresponde() {
		Optional<Categoria> categoria = RepositorioCategorias.getInstance().categoriaCorrespondiente((double) 120);
		assertEquals("R1", categoria.get().getCategoria());
	}

	@Test
	public void repoDarOtraCategoria_R1_dadoConsumoQueNoCorresponde() {
		Optional<Categoria> categoria = RepositorioCategorias.getInstance().categoriaCorrespondiente((double) 500);
		assertNotEquals("El repo da una categoria que no corresponde", "R1", categoria.get().getCategoria());
	}

}
