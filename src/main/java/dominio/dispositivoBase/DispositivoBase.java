package dominio.dispositivoBase;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import dominio.PersistentObject;
import dominio.dispositivo.Dispositivo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="Tipo")
public abstract class DispositivoBase extends PersistentObject{

	private String nombre;
	private Double cotaSuperior;
	private Double cotaInferior;
	private Double consumoPorHora;
	
	
	
	public DispositivoBase(String nombre, Double cotaSuperior, Double cotaInferior, Double consumoPorHora) {
		this.nombre = nombre;
		this.cotaSuperior = cotaSuperior;
		this.cotaInferior = cotaInferior;
		this.consumoPorHora = consumoPorHora;
	}
	//metodo abstracto
	public abstract Dispositivo devolverDisositivo();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCotaSuperior() {
		return cotaSuperior;
	}

	public void setCotaSuperior(Double cotaSuperior) {
		this.cotaSuperior = cotaSuperior;
	}

	public Double getCotaInferior() {
		return cotaInferior;
	}

	public void setCotaInferior(Double cotaInferior) {
		this.cotaInferior = cotaInferior;
	}

	public Double getConsumoPorHora() {
		return consumoPorHora;
	}

	public void setConsumoPorHora(Double consumoPorHora) {
		this.consumoPorHora = consumoPorHora;
	}
	
	
}
