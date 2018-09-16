package dominio.reactores;

import dominio.PersistentObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Sensor")
public class Sensor extends PersistentObject {
	
	//Attributes
	
	@OneToMany (cascade = CascadeType.PERSIST)
	private List<Regla> reglas= new ArrayList<Regla>();
	
	@Transient
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
	
	
	


