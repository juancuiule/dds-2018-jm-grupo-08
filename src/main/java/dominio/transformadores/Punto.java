package dominio.transformadores;

class Punto {
	double posLat;
	double posLong;

	public Punto(double posLat, double posLong) {
		posLat = posLat;
		posLong = posLong;
	}
	public Double getPosLat() {
		return posLat;
	}
	public Double getPosLong() {
		return posLong;
	}
}