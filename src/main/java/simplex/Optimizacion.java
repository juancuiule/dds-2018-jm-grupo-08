package simplex;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import dominio.dispositivo.Dispositivo;

public class Optimizacion {

	private Dispositivo dispositivo;
	private Double limite;

	public Optimizacion(Dispositivo dispositivo, Double limite) {
		this.dispositivo = dispositivo;
		this.limite = limite;
	}

	public void restringirConsumo() {
		double diasUltimoMes = ChronoUnit.DAYS.between( LocalDate.now().plusMonths(-1),LocalDate.now());
		if(dispositivo.consumoEnElPeriodo(diasUltimoMes) > limite) {
			dispositivo.restringirConsumo();
		}
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}
}
