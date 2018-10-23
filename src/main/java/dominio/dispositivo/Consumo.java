package dominio.dispositivo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.*;

import dominio.Cliente;
import dominio.PersistentObject;

@Entity
public class Consumo extends PersistentObject{

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "dispositivo_id")
	private Dispositivo dispositivo;
	
	@Convert(converter = ConversorDeFecha.class)
	private LocalDate fechaInicio;
	
	@Convert(converter = ConversorDeFecha.class)
	private LocalDate fechaFin;
	
	private double kwConsumidos;
	
	
	public Consumo(Cliente cliente, Dispositivo dispositivo, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.cliente = cliente;
		this.dispositivo = dispositivo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.setConsumo();
	}
	
	private void setConsumo() {
		double cantidad = ChronoUnit.DAYS.between(fechaInicio,fechaFin);
		kwConsumidos = dispositivo.consumoEnElPeriodo(cantidad);
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
