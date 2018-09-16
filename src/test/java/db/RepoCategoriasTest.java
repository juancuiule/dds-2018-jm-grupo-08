package db;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dominio.Categoria;
import dominio.RepositorioCategorias;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class RepoCategoriasTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

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
		beginTransaction();
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

	@Test
	public void categoriaCorrespondienteDelRepoMeTraeLaQueEsAcordeAlConsumo() {
		Categoria categoriaQueCorresponde = repo.categoriaCorrespondiente(80d);
		assertEquals(R1, categoriaQueCorresponde);
	}

	@Test
	public void traerTodasLasCategorias() {
		ArrayList<Categoria> categoriasQueEspero = new ArrayList<Categoria>(
				Arrays.asList(R1, R2, R3, R4, R5, R6, R7, R8, R9));
		List<Categoria> categorias = repo.findAll();
		assertEquals(categoriasQueEspero, categorias);
	}

}
