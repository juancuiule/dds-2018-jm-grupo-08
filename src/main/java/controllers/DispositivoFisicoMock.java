package controllers;

import dominio.dispositivo.DispositivoFisico;

public class DispositivoFisicoMock extends DispositivoFisico {

	Boolean encendido = false;
	private String nombre;

	@Override
	public Double consumoPorHora() {
		return null;
	}
	
	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public Double consumoEnLasUltimasHoras(Double horas) {
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