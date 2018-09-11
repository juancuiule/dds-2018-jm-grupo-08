package dominioTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import dominio.SensorLegacy;
import org.junit.Test;

import dominio.ActuadorLegacy;
import dominio.ReglaLegacy;
import dominio.dispositivo.*;
import dominioTest.mocks.*;

public class ActuadorLegacyTest {
	SensorLegacy sensorLegacyTemperatura100 = new SensorLegacyMock(100d); // SensorLegacy que siempre devuelve 100
	Dispositivo aireAcondicionado = new Dispositivo("Aire", new ComportamientoInteligente(new InterfazDeFabricaMock()));

	Consumer<Dispositivo> encender = (Dispositivo unDispositivo) -> unDispositivo.encender();
	Consumer<Dispositivo> apagar = (Dispositivo unDispositivo) -> unDispositivo.apagar();

	ActuadorLegacy encenderDispositivo = new ActuadorLegacy(encender);
	ActuadorLegacy apagarDispositivo = new ActuadorLegacy(apagar);

	@Test
	public void disparaAccionCuandoCumpleRegla() {
		// Enciendo el dispositivo si la temperatura es mayor a 80
		ReglaLegacy regla = new ReglaLegacy(new ArrayList<ActuadorLegacy>(Arrays.asList(encenderDispositivo)), aireAcondicionado,
				sensorLegacyTemperatura100, ((Double valor) -> valor > 80));
		regla.ejecutarSiCorresponde();
		assertTrue(aireAcondicionado.estaEncendido());
	}

	@Test
	public void noHaceNadaSiLaCondicionNoSeCumple() {
		ReglaLegacy regla = new ReglaLegacy(new ArrayList<ActuadorLegacy>(Arrays.asList(encenderDispositivo)), aireAcondicionado,
				sensorLegacyTemperatura100, ((Double valor) -> valor < 30));
		regla.ejecutarSiCorresponde();
		assertFalse(aireAcondicionado.estaEncendido());
	}

	@Test
	public void funcionaConListasDeActuadores() {
		// Lo enciende, lo apaga y lo vuelve a encender
		ReglaLegacy regla = new ReglaLegacy(
				new ArrayList<ActuadorLegacy>(Arrays.asList(encenderDispositivo, apagarDispositivo, encenderDispositivo)),
				aireAcondicionado, sensorLegacyTemperatura100, ((Double valor) -> valor > 30));
		// Empieza apagado
		assertTrue(aireAcondicionado.estaApagado());

		regla.ejecutarSiCorresponde();

		assertTrue(aireAcondicionado.estaEncendido());
	}
}
