package dominio;

import dominio.dispositivo.ConversorDeFecha;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Administrador")
public class Administrador extends PersistentObject {
	private String nombre;
	private String apellido;

	@Convert(converter = ConversorDeFecha.class)
	private LocalDate fechaDeAlta;
	private Integer identificador;

	public Administrador(String nombre, String apellido, LocalDate fechaDeAlta, Integer identificador) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeAlta = fechaDeAlta;
		this.identificador = identificador;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(LocalDate fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
}
