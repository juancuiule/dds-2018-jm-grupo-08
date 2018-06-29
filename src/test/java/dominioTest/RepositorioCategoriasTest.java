package dominioTest;

import static org.junit.Assert.*;


import org.junit.Test;

import dominio.Categoria;
import dominio.RepositorioCategorias;

public class RepositorioCategoriasTest {
	@Test
	public void repoDarCategoria_R1_dadoConsumoQueCorresponde() {
		Categoria categoria = RepositorioCategorias.getInstance().categoriaCorrespondiente((double) 120);
		assertEquals("R1", categoria.getNombre());
	}

	@Test
	public void repoDarOtraCategoria_R1_dadoConsumoQueNoCorresponde() {
		Categoria categoria = RepositorioCategorias.getInstance().categoriaCorrespondiente((double) 500);
		assertNotEquals("R1", categoria.getNombre());
	}
}
