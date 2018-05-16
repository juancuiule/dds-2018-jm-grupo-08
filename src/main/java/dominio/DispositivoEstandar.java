package dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DispositivoEstandar implements Dispositivo {
	private String nombre;
	private Double kWh;
	private Boolean encendido;
	private Integer horasDeUsoXDia;

	public String getNombre() {
		return nombre;
	}

	public Double getkWh() {
		return kWh;
	}

	public Boolean getEncendido() {
		return encendido;
	}

	public DispositivoEstandar(String nombre, Double kWh,Integer horasDeUsoXDia , Boolean encendido) {
		this.nombre = nombre;
		this.kWh = kWh;
		this.encendido = encendido;
		this.horasDeUsoXDia = horasDeUsoXDia;
	}

	public void apagar() { 
		this.encendido = false;
	} 

	public void encender() {
		this.encendido = true;
	}

	public Boolean estaEncendido() {
		return false;
	}

	public Boolean estaApagado() { 
		return false;
	}

    @Override
    public Double consumoEntre(LocalDate inicio, LocalDate fin) {
        Double diasEntreInicioYFin = new Double(ChronoUnit.DAYS.between(inicio, fin));
        return diasEntreInicioYFin * horasDeUsoXDia * kWh;
    }

}