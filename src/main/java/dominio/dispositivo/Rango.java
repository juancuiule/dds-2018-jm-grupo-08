package dominio.dispositivo;

import dominio.PersistentObject;

import javax.persistence.*;

@Embeddable
public class Rango {
    // Atributes
    private Double cotaSuperior;
    private Double cotaInferior;

    // Constructors
    public Rango() {
    }

    public Rango(Double cotaSuperior, Double cotaInferior) {
        super();
        this.cotaSuperior = cotaSuperior;
        this.cotaInferior = cotaInferior;
    }

    // Accessors
    public Double getCotaSuperior() {
        return cotaSuperior;
    }

    public void setCotaSuperior(Double cotaSuperior) {
        this.cotaSuperior = cotaSuperior;
    }

    public Double getCotaInferior() {
        return cotaInferior;
    }

    public void setCotaInferior(Double cotaInferior) {
        this.cotaInferior = cotaInferior;
    }
}
