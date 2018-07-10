package dominio.transformadores;

public class Transformador {
	Punto punto;
	boolean activo;

	public Transformador(Punto punto, boolean activo) {
		this.punto = punto;
		this.activo = activo;
	}
	
	public Double energiaQueSuministra() {
		return 1.0;
	}

	public boolean estaActivo() {
		return activo;
	}
	
	public Punto getPunto() {
		return punto;
	}
}