package dominioTest.mocks;

import java.time.Period;

import dominio.dispositivo.InterfazDeFabrica;

public class InterfazDeFabricaMock implements InterfazDeFabrica {

	Boolean encendido = false;

	@Override
	public Double consumoPorHora() {
		return null;
	}

	@Override
	public Double consumoEnLasUltimasHoras() {
		return null;
	}

	@Override
	public Double consumoEnElPeriodo(Double diasUltimoMes) {
		return (double) 100;
	}

	@Override
	public void ahorrarEnergia() {
	}

	@Override
	public Boolean estaEncendido() {
		return encendido;
	}

	@Override
	public Boolean estaApagado() {
		return !encendido;
	}

	@Override
	public void apagar() {
		encendido = false;
	}

	@Override
	public void encender() {
		encendido = true;
	}
}