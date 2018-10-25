package dominio.reporte;

import java.time.LocalDate;

public class Mes {
	Integer value;
	String descripcion;

	public Integer getValue() {
		return value;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean getSelected() {
		Integer month = LocalDate.now().getMonthValue();
		return month == this.value;
	}

	public Mes(Integer value, String descripcion) {
		this.value = value;
		this.descripcion = descripcion;
	}
}