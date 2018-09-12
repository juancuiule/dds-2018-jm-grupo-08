package dominio.reactores;

import java.util.ArrayList;
import java.util.List;


public class Sensor {
	
	//Attributes
	
	
	private List<Regla> reglas= new ArrayList<Regla>();
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
	
	
	


