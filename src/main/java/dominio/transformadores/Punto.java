package dominio.transformadores;

import javax.persistence.Embeddable;

@Embeddable
public class Punto {
    double posLat;
    double posLong;

    public Punto() {
    }

    public Punto(double posLat, double posLong) {
        this.posLat = posLat;
        this.posLong = posLong;
    }

    public double getPosLat() {
        return posLat;
    }

    public void setPosLat(double posLat) {
        this.posLat = posLat;
    }

    public double getPosLong() {
        return posLong;
    }

    public void setPosLong(double posLong) {
        this.posLong = posLong;
    }
}