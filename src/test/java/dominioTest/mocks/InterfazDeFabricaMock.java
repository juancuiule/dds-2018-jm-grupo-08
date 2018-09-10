package dominioTest.mocks;

import java.time.Period;

import dominio.dispositivo.InterfazDeFabrica;

public class InterfazDeFabricaMock extends InterfazDeFabrica {

	Boolean encendido = false;

	public Double consumoPorHora() {
		return null;
	}

	public Double consumoEnLasUltimasHoras() {
		return null;
	}

	public Double consumoEnElPeriodo(Period periodo) {
		return (double) 100;
	}

	public void ahorrarEnergia() {
	}

	public Boolean estaEncendido() {
		return encendido;
	}

	public Boolean estaApagado() {
		return !encendido;
	}

	public void apagar() {
		encendido = false;
	}

	public void encender() {
		encendido = true;
	}
}