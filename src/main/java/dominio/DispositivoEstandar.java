package dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DispositivoEstandar implements Dispositivo {
	private String nombre;
	private Double kWh;
	private Integer horasDeUsoXDia;

	public String getNombre() {
		return nombre;
	}

	public Double getkWh() {
		return kWh;
	}

	public DispositivoEstandar(String nombre, Double kWh, Integer horasDeUsoXDia) {
		this.nombre = nombre;
		this.kWh = kWh;
		this.horasDeUsoXDia = horasDeUsoXDia;
	}

    @Override
    public Double consumoEntre(LocalDate inicio, LocalDate fin) {
        Double diasEntreInicioYFin = new Double(ChronoUnit.DAYS.between(inicio, fin));
        return diasEntreInicioYFin * horasDeUsoXDia * kWh;
    }

}