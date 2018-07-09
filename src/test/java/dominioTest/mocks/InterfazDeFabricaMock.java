package dominioTest.mocks;

import java.time.Period;

import dominio.dispositivo.InterfazDeFabrica;

public class InterfazDeFabricaMock implements InterfazDeFabrica {

	@Override
	public Double consumoPorHora() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double consumoEnLasUltimasHoras() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double consumoEnElPeriodo(Period periodo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ahorrarEnergia() {
		// TODO Auto-generated method stub
	}

	Boolean encendido = false;

	@Override
	public Boolean estaEncendido() {
		// TODO Auto-generated method stub
		return encendido;
	}

	@Override
	public Boolean estaApagado() {
		// TODO Auto-generated method stub
		return !encendido;
	}

	@Override
	public void apagar() {
		// TODO Auto-generated method stub
		encendido = false;
	}

	@Override
	public void encender() {
		// TODO Auto-generated method stub
		encendido = true;
	}
}