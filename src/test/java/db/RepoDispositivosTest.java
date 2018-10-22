package db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.RepositorioDispositivos;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.Rango;
import dominioTest.mocks.DispositivoFisicoMock;

public class RepoDispositivosTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	Dispositivo pc;
	RepositorioDispositivos repoDispositivos = RepositorioDispositivos.getInstance();

	@Before
	public void fixture() {
		ComportamientoInteligente comportamientoI = new ComportamientoInteligente(new DispositivoFisicoMock(), 0.4);
		pc = new Dispositivo(comportamientoI, "pc", new Rango(60d, 360d));
	}

	@Test
	public void persistirNuevoNombre() {
		repoDispositivos.agregar(pc);

		Dispositivo dispositivoPC = repoDispositivos.findOne("nombre = 'pc'");
		dispositivoPC.setNombre("pcGamer");

		Dispositivo pcModificada = repoDispositivos.findOne("nombre = 'pcGamer'");

		Assert.assertEquals(dispositivoPC, pcModificada);
	}

}
