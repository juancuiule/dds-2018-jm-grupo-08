package dominioTest.mocks;

import dominio.SensorLegacy;

public class SensorLegacyMock implements SensorLegacy {
	double valorDeRetorno;

	public SensorLegacyMock(double valorARetornar) {
		this.valorDeRetorno = valorARetornar;
	}

	@Override
	public double valorActual() {
		return valorDeRetorno;
	}
}