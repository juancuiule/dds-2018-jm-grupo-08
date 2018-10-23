package dominio.dispositivoBase;

import javax.persistence.Entity;

import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.Rango;

@Entity
public class EstandarDB extends DispositivoBase {

	private Double horasPorDia;
	public EstandarDB(String nombre, Double cotaSuperior, Double cotaInferior, Double consumoPorHora,Double horasPorDia) {
		super(nombre, cotaSuperior, cotaInferior, consumoPorHora);
		this.horasPorDia = horasPorDia;
	}

	@Override
	public Dispositivo devolverDisositivo() {
		Dispositivo dispositivoParaElCliente = new Dispositivo(
				new ComportamientoEstandar(this.getConsumoPorHora(),this.horasPorDia),
				this.getNombre(), new Rango(this.getCotaSuperior(),this.getCotaInferior()));
		
		return dispositivoParaElCliente;
	}

}
