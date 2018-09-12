package dominioTest.mocks;

import dominio.reactores.Actuador;

public class ActuadorLuminosidadMock extends Actuador {

	@Override
	public void enviarAcciones() {
		this.getDispositivos().forEach(dispositivo->dispositivo.encender());
	}
}
