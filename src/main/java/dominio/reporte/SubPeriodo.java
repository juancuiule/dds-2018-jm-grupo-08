package dominio.reporte;

public class SubPeriodo {
	String fechas;
	Double consumo;
	Double consumoAcumulado;

	public SubPeriodo(String fechas, Double consumo, Double consumoAcumulado) {
		this.fechas = fechas;
		this.consumo = consumo;
		this.consumoAcumulado = consumoAcumulado;
	}

	public String getFechas() {
		return fechas;
	}

	public Double getConsumo() {
		return consumo;
	}

	public Double getConsumoAcumulado() {
		return consumoAcumulado;
	}
}