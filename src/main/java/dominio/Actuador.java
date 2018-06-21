package dominio;

import java.util.function.Function;

import dominio.dispositivo.Dispositivo;;

public class Actuador {
	
	private Function<Dispositivo, Void> accion; 
	
	public Actuador(Function<Dispositivo, Void> accionARealizar) {
		accion = accionARealizar;
	}
	
	public void actuar(Dispositivo unDispositivo) {
		// ejecutar la acción sobre el dispositivo
		accion.apply(unDispositivo);
	}
	
}
