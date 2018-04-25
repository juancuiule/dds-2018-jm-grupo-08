package dominioTest;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import dominio.Categoria;
import dominio.RepositorioCategorias;

public class RepositorioCategoriasTest {
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
