package db;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.reactores.Sensor;
import dominioTest.mocks.ActuadorLuminosidadMock;
import dominioTest.mocks.ActuadorLuminosidadMock2;
import dominioTest.mocks.FabricanteSensorMock;
import dominioTest.mocks.DispositivoFisicoMock;
import dominioTest.mocks.ReglaLuminosidadMock;
import dominioTest.mocks.ReglaLuminosidadMock2;

public class PruebaTresTest {

	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();

	Sensor luminosidadAmbiente = new Sensor(new FabricanteSensorMock());
	ReglaLuminosidadMock reglaLuminosidad = new ReglaLuminosidadMock();
	ActuadorLuminosidadMock actuadorLuminosidad = new ActuadorLuminosidadMock();

	// Segundo Test

	ReglaLuminosidadMock2 reglaLuminosidadAhorro = new ReglaLuminosidadMock2();
	ActuadorLuminosidadMock2 actuadorLuminosidadAhorro = new ActuadorLuminosidadMock2();
	Dispositivo lamparaHalogena = new Dispositivo("LamparaLed", new ComportamientoInteligente(new DispositivoFisicoMock()));
	
	@Before
	public void transaccionBegin() {
		transaction.begin();

		luminosidadAmbiente.agregarRegla(reglaLuminosidad);
		reglaLuminosidad.agregarActuador(actuadorLuminosidad);
		actuadorLuminosidad.agregarDispositivo(lamparaHalogena);

	}

	@After
	public void transaccionRollback() {
		transaction.rollback();
	}

	@Test
	public void persistirRegla() {

		entityManager.persist(luminosidadAmbiente);

		List<Sensor> sensores = entityManager.createQuery("from Sensor").getResultList();

		Sensor deLuz = sensores.get(0);
		deLuz.comunicar();

		assertTrue(lamparaHalogena.estaEncendido());
	}

	@Test
	public void persistirNuevoCambio() {

		luminosidadAmbiente.agregarRegla(reglaLuminosidadAhorro);
		reglaLuminosidadAhorro.agregarActuador(actuadorLuminosidadAhorro);
		actuadorLuminosidadAhorro.agregarDispositivo(lamparaHalogena);

		entityManager.persist(luminosidadAmbiente);

		List<Sensor> sensores = entityManager.createQuery("from Sensor").getResultList();

		Sensor deLuz = sensores.get(0);
		deLuz.comunicar();

		assertTrue(lamparaHalogena.estaApagado());

	}

}
