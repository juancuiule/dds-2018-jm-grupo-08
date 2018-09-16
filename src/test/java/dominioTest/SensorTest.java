package dominioTest;

import org.junit.Before;
import org.junit.Test;

import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.reactores.Sensor;
import dominioTest.mocks.ActuadorLuminosidadMock;
import dominioTest.mocks.FabricanteSensorMock;
import dominioTest.mocks.DispositivoFisicoMock;
import dominioTest.mocks.ReglaLuminosidadMock;
import static org.junit.Assert.*;

public class SensorTest {

	Dispositivo lamparaLed = new Dispositivo("LamparaLed", new ComportamientoInteligente(new DispositivoFisicoMock()));
	Dispositivo lamparaLed2 = new Dispositivo("LamparaLed", new ComportamientoInteligente(new DispositivoFisicoMock()));
	
	ReglaLuminosidadMock reglaLuminosidad = new ReglaLuminosidadMock();
	
	ActuadorLuminosidadMock actuadorLuminosidad = new ActuadorLuminosidadMock();
	
	Sensor sensorLuminosidad = new Sensor(new FabricanteSensorMock()); 
	
	@Before
	public void intancia() {
		actuadorLuminosidad.agregarDispositivo(lamparaLed);
		actuadorLuminosidad.agregarDispositivo(lamparaLed2);
		
		reglaLuminosidad.agregarActuador(actuadorLuminosidad);
		
		sensorLuminosidad.agregarRegla(reglaLuminosidad);
	}
	
	@Test
	public void lasLamparasSeVanAEncender() {
		sensorLuminosidad.comunicar();
		
		assertTrue(lamparaLed.estaEncendido());
		
	}
}
