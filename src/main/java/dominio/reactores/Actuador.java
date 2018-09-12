package dominio.reactores;

import java.util.ArrayList;
import java.util.List;

import dominio.dispositivo.Dispositivo;

public class Actuador {
	
	private List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	
	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void enviarAcciones() {
		//cada actuador define una accion
	}
	
	public void agregarDispositivo(Dispositivo unDispositivo) {
		dispositivos.add(unDispositivo);
	}
	
}
