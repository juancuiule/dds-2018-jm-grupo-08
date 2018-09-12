package dominio.reactores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sensor {
	
	//Attributes
	
	@OneToMany
	private List<Regla> reglas= new ArrayList<Regla>();
	
	@OneToOne
	private FabricanteDeSensor sensorFisico;
	
	
	
    public Sensor(FabricanteDeSensor sensorFisico) {
		super();
		this.sensorFisico = sensorFisico;
	}

	//Methods
	
	public void comunicar() {
			reglas.forEach(regla -> regla.evaluar(this));
	}

	public double getMagnitud() {
		return sensorFisico.getMagnitud();
	}

	public void agregarRegla(Regla regla1) {
		reglas.add(regla1);
		
	}
	
	
}
	
	
	


