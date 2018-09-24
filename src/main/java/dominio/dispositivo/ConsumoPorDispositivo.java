package dominio.dispositivo;

import java.time.LocalDate;

import javax.persistence.*;

import dominio.Cliente;
import dominio.PersistentObject;

@Entity
public class ConsumoPorDispositivo extends PersistentObject{

	@OneToOne
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "dispositivo_id")
	private Dispositivo dispositivo;
	
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	
	public ConsumoPorDispositivo(Cliente cliente, Dispositivo dispositivo, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.cliente = cliente;
		this.dispositivo = dispositivo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}


	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	
}
