package dominio.dispositivo;

import dominio.PersistentObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rango")
public class Rango extends PersistentObject {

    private Double cotaSuperior;
    private Double cotaInferior;
    public Rango(Double cotaSuperior, Double cotaInferior) {
        super();
        this.cotaSuperior = cotaSuperior;
        this.cotaInferior = cotaInferior;
    }
    public Double getCotaSuperior() {
        return cotaSuperior;
    }
    
    public Double getCotaInferior() {
        return cotaInferior;
    }
    

}
