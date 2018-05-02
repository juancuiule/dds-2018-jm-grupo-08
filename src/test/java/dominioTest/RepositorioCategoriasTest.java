package dominioTest;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import dominio.Categoria;
import dominio.RepositorioCategorias;

public class RepositorioCategoriasTest {
	@Test
	public void repoDarCategoria_R1_dadoConsumoQueCorresponde() {
		Categoria categoria = RepositorioCategorias.getInstance().categoriaCorrespondiente((double) 120);
		assertEquals("R1", categoria.getCategoria());
	}

	@Test
	public void repoDarOtraCategoria_R1_dadoConsumoQueNoCorresponde() {
		Categoria categoria = RepositorioCategorias.getInstance().categoriaCorrespondiente((double) 500);
		assertNotEquals("R1", categoria.getCategoria());
	}
}
