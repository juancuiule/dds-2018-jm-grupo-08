package dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DispositivoEstandar extends Dispositivo {
	private String nombre;
	private Double kWh;
	private Integer horasDeUsoXDia;
	
	public DispositivoEstandar(String nombre, Double kWh,Integer horasDeUsoXDia) {
        super(nombre, kWh);
        this.horasDeUsoXDia = horasDeUsoXDia;
    }

    @Override
	public Boolean estaEncendido() {
		return false;
	}
	
	@Override
	public Boolean estaApagado() { 
		return false;
	}

    @Override
    public Double consumoEntre(LocalDate inicio, LocalDate fin) {
        Double diasEntreInicioYFin = new Double(ChronoUnit.DAYS.between(inicio, fin));
        return diasEntreInicioYFin * horasDeUsoXDia * kWh;
    }

}