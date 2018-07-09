package dominio;

import java.util.function.Consumer;
import java.util.function.Function;

import dominio.dispositivo.Dispositivo;;

public class Actuador {

	private Consumer<Dispositivo> accion;

	public Actuador(Consumer<Dispositivo> accionARealizar) {
		accion = accionARealizar;
	}

	public void actuar(Dispositivo unDispositivo) {
		// ejecutar la acción sobre el dispositivo
		accion.accept(unDispositivo);
	}

}
