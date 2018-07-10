package dominio;

import java.time.LocalDate;
import java.time.Period;

import dominio.dispositivo.Dispositivo;

public class Optimizacion {

	private Dispositivo dispositivo;
	private Double limite;

	public Optimizacion(Dispositivo dispositivo, Double limite) {
		this.dispositivo = dispositivo;
		this.limite = limite;
	}

	public void restringirConsumo() {
		Period periodoUltimoMes = Period.between(LocalDate.now().plusMonths(-1), LocalDate.now());
		if(dispositivo.consumoEnElPeriodo(periodoUltimoMes) > limite) {
			dispositivo.restringirConsumo();
		}
	}

}
