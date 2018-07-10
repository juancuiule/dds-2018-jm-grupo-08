package dominio;

import dominio.dispositivo.Dispositivo;

public class Restriccion {

	private Dispositivo dispositivo;
	private Double limite;

	public Restriccion(Dispositivo dispositivo, Double limite) {
		this.dispositivo = dispositivo;
		this.limite = limite;
	}

}
