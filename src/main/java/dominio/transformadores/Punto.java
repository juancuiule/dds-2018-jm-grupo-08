package dominio.transformadores;

public class Punto {
	double posLat;
	double posLong;

	public Punto(double posLat, double posLong) {
		this.posLat = posLat;
		this.posLong = posLong;
	}
	public Double getPosLat() {
		return posLat;
	}
	public Double getPosLong() {
		return posLong;
	}
}