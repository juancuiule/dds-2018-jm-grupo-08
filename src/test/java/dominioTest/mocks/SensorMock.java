package dominioTest.mocks;

import dominio.Sensor;

public class SensorMock implements Sensor {
	double valorDeRetorno;

	public SensorMock(double valorARetornar) {
		this.valorDeRetorno = valorARetornar;
	}

	@Override
	public double valorActual() {
		return valorDeRetorno;
	}
}