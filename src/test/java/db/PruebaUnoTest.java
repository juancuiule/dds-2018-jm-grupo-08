package db;

import dominio.Cliente;
import dominio.RepositorioClientes;
import dominio.TipoDeDocumento;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.Rango;
import dominio.transformadores.Punto;
import dominio.transformadores.RepositorioTransformadores;
import dominio.transformadores.Transformador;
import dominioTest.mocks.DispositivoFisicoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PruebaUnoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	Cliente raul;
	Transformador transformador;
	RepositorioClientes repoClientes = RepositorioClientes.getInstance();
	RepositorioTransformadores repoTransformadores = RepositorioTransformadores.getInstance();

	boolean configured = false;

	public void dbConfig() {
		if (!configured) {
			transformador = new Transformador(new Punto(2d, 2d), true);
			repoTransformadores.agregar(transformador);

			raul = new Cliente("Raul", "Gomez", TipoDeDocumento.DNI, new Integer(33333334), new Integer(800445),
					LocalDate.of(2018, 01, 11), "Lujan", "raulG", "1234", this.listaDeDispositivos(), true,
					new Punto(1d, 2d));
			repoClientes.agregar(raul);

			configured = true;
		}
	}

	@Before
	public void fixture() {
		this.dbConfig();
	}

	@Test
	public void persistirARaul() {
		Cliente clienteRaul = repoClientes.findOne("numeroDeDocumento = 33333334");
		Assert.assertEquals(clienteRaul, raul);
	}

	@Test
	public void persistirCambiosGeolocalizacion() {
		Cliente clienteRaul = repoClientes.findOne("numeroDeDocumento = 33333334");
		Punto nuevoPunto = new Punto(2d, 1d);
		clienteRaul.setPunto(nuevoPunto);

		clienteRaul = repoClientes.findOne("numeroDeDocumento = 33333334");
		Assert.assertEquals(clienteRaul.getPunto(), nuevoPunto);
	}

	// Private methods
	private List<Dispositivo> listaDeDispositivos() {
		ComportamientoInteligente comportamientoI = new ComportamientoInteligente(new DispositivoFisicoMock(), 0.2);
		ComportamientoEstandar conportamientoE = new ComportamientoEstandar(1.2, 6d);
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
		Dispositivo aire3500 = new Dispositivo(comportamientoI, "aire", new Rango(0.1, 0.72));
		Dispositivo ventiladorPie = new Dispositivo(conportamientoE, "ventilador", new Rango(0.24, 0.745));

		dispositivos.add(ventiladorPie);
		dispositivos.add(aire3500);

		return dispositivos;
	}
}
