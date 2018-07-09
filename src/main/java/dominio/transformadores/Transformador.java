package dominio.transformadores;

class Transformador {
	Punto punto;
	boolean activo;

	public Transformador(Punto punto, boolean activo) {
		punto = punto;
		activo = activo;
	}
	
	public Double energiaQueSuministra() {
		return 1.0;
	}
}