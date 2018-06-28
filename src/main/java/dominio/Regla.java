package dominio;

import java.util.List;
import java.util.function.Predicate;

import dominio.dispositivo.Dispositivo;

public class Regla {

	List<Actuador> actuadores;
	Dispositivo dispositivo;
	Sensor sensor;
	Predicate<Sensor> condicion;
	
	public Regla(
			List<Actuador> unosActuadores,
			Dispositivo unDispositivo,
			Sensor unSensor,
			Predicate<Sensor> unaCondicion
	) {
		sensor = unSensor;
		condicion = unaCondicion;
		actuadores = unosActuadores;
		dispositivo = unDispositivo;
	}
	
	public void ejecutarSiCorresponde() {
		if(this.comprobarCondicion()) {
			this.ejecutar();
		}
	}
	
	public boolean comprobarCondicion() {
		return condicion.test(sensor);
	}
	
	private void ejecutar() {
		actuadores.forEach(actuador ->
			actuador.actuar(this.dispositivo)
		);
	}
	
}
