package dominioTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import dominio.Administrador;

public class AdministradorTest {

	Administrador administrador;

	@Before
	public void generarAdministrador() {
		administrador = new Administrador("Steve", "Stifler", "2014-09-01", 123, "sstifler", "sstifler");
	}

	@Test
	public void administradorCuantosMesesLleva() {
		assertEquals(administrador.mesesComoAdministrador(), administrador.getFechaDeAlta().until(LocalDate.now()).getMonths());
	}
}
