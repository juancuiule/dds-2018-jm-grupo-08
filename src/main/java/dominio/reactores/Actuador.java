package domain;

import java.util.ArrayList;
import java.util.List;

public class Actuador {
	
	private List<DispositivoInteligente> dispositivos = new ArrayList<DispositivoInteligente>();
	
	public void enviarAcciones() {
		//realizarAcciones
		//System.out.println("Mensaje del Actuador");
		//un ejemplo
		//dispositivos.forEach(dispositivo -> dispositivo.encender());
	}
	
	public void agregarDispositivo(DispositivoInteligente unDispositivo) {
		dispositivos.add(unDispositivo);
	}
	
}
