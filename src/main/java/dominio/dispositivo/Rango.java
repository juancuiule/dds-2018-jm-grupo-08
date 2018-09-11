package dominio.dispositivo;

public class Rango {
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
