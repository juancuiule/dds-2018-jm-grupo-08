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

public class ActuadoresTest {
	Sensor sensorTemperatura100 = new SensorMock(100d);
	Dispositivo aireAcondicionado = new Dispositivo("Aire", new ComportamientoInteligente(new InterfazDeFabricaMock()));

	@Test
	public void disparaAccionCuandoCumpleRegla() {
		
		Consumer<Dispositivo> accionARealizar = (Dispositivo unDispositivo) -> unDispositivo.encender();
		Actuador encenderDispositivo = new Actuador(accionARealizar);
		Regla regla = new Regla(
				new ArrayList<Actuador>(Arrays.asList(encenderDispositivo)),
				aireAcondicionado,
				sensorTemperatura100,
				((Double valor) -> valor > 80)
		);
		regla.ejecutarSiCorresponde();
		assertTrue(aireAcondicionado.estaEncendido());
	}
}
