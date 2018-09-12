package dominio.reactores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import dominio.PersistentObject;

@Entity
public abstract class Regla extends PersistentObject {
	
	@ManyToMany
	private List<Actuador> actuadores = new ArrayList<Actuador>();
	
	// Methods 
	
	public abstract Boolean cumpleConCondiciones(Sensor unSensor);
	
	public void evaluar(Sensor unSensor) {
		if(cumpleConCondiciones(unSensor)) {
			actuadores.forEach(actuador -> actuador.enviarAcciones());
		}
	}
	
	public void agregarActuador(Actuador actuador) {
		actuadores.add(actuador);
	}
	
}
