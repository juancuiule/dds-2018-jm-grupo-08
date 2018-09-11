package dominioTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import org.junit.Test;

import dominio.Actuador;
import dominio.Sensor;
import dominio.Regla;
import dominio.dispositivo.*;
import dominioTest.mocks.*;

public class ActuadorTest {
	Sensor sensorTemperatura100 = new SensorMock(100d); // Sensor que siempre devuelve 100
	Dispositivo aireAcondicionado = new Dispositivo("Aire", new ComportamientoInteligente(new InterfazDeFabricaMock()));

	Consumer<Dispositivo> encender = (Dispositivo unDispositivo) -> unDispositivo.encender();
	Consumer<Dispositivo> apagar = (Dispositivo unDispositivo) -> unDispositivo.apagar();

	Actuador encenderDispositivo = new Actuador(encender);
	Actuador apagarDispositivo = new Actuador(apagar);

	@Test
	public void disparaAccionCuandoCumpleRegla() {
		// Enciendo el dispositivo si la temperatura es mayor a 80
		Regla regla = new Regla(new ArrayList<Actuador>(Arrays.asList(encenderDispositivo)), aireAcondicionado,
				sensorTemperatura100, ((Double valor) -> valor > 80));
		regla.ejecutarSiCorresponde();
		assertTrue(aireAcondicionado.estaEncendido());
	}

	@Test
	public void noHaceNadaSiLaCondicionNoSeCumple() {
		Regla regla = new Regla(new ArrayList<Actuador>(Arrays.asList(encenderDispositivo)), aireAcondicionado,
				sensorTemperatura100, ((Double valor) -> valor < 30));
		regla.ejecutarSiCorresponde();
		assertFalse(aireAcondicionado.estaEncendido());
	}

	@Test
	public void funcionaConListasDeActuadores() {
		// Lo enciende, lo apaga y lo vuelve a encender
		Regla regla = new Regla(
				new ArrayList<Actuador>(Arrays.asList(encenderDispositivo, apagarDispositivo, encenderDispositivo)),
				aireAcondicionado, sensorTemperatura100, ((Double valor) -> valor > 30));
		// Empieza apagado
		assertTrue(aireAcondicionado.estaApagado());

		regla.ejecutarSiCorresponde();

		assertTrue(aireAcondicionado.estaEncendido());
	}
}
