package dominio.dispositivoBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFisico;
import dominio.dispositivo.Rango;

@Entity
public class InteligenteDB extends DispositivoBase{
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private DispositivoFisico dispositivoFisico;
	
	public InteligenteDB(String nombre, Double cotaSuperior, Double cotaInferior, Double consumoPorHora, DispositivoFisico dispositivoFisico) {
		super(nombre, cotaSuperior, cotaInferior, consumoPorHora);
		this.dispositivoFisico = dispositivoFisico;
	}

	@Override
	public Dispositivo devolverDisositivo() {
		Dispositivo dispositivoParaElCliente = new Dispositivo(new ComportamientoInteligente(this.dispositivoFisico,this.getConsumoPorHora()),this.getNombre(), new Rango(this.getCotaSuperior(), this.getCotaInferior()));
		return dispositivoParaElCliente;
	}

}
