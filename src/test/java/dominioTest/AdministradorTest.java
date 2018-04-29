package dominioTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dominio.Administrador;

public class AdministradorTest {

	Administrador administrador;
	

	@Before
	public void generarAdministrador() {
		administrador = new Administrador("Steven", "Stifler", "2018-02-01", 123, "sstifler", "sstifler");
	}

	@Test
	public void administradorCuantosMesesLleva() {
		assertEquals(3, administrador.mesesComoAdministrador());
	}
}
