package dominio;

import java.util.List;
import java.util.function.Predicate;

import dominio.dispositivo.Dispositivo;

public class ReglaLegacy {

	List<ActuadorLegacy> actuadores;
	Dispositivo dispositivo;
	SensorLegacy sensorLegacy;
	Predicate<Double> condicion;
	
	public ReglaLegacy(
			List<ActuadorLegacy> unosActuadores,
			Dispositivo unDispositivo,
			SensorLegacy unSensorLegacy,
			Predicate<Double> unaCondicion
	) {
		sensorLegacy = unSensorLegacy;
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
		return condicion.test(sensorLegacy.valorActual());
	}
	
	private void ejecutar() {
		actuadores.forEach(actuador ->
			actuador.actuar(this.dispositivo)
		);
	}
	
}
