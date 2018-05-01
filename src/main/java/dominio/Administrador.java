package dominio;

import java.time.LocalDate;

public class Administrador {
	private String nombre;
	private String apellido;
	private LocalDate fechaDeAlta;
	private Integer identificador;
	private String nombreDeUsuario;
	private String contrasena;	

	public Administrador(String nombre, String apellido, String stringFechaDeAlta, Integer identificador,
			String nombreDeUsuario, String contrasena) {
		this.nombre = nombre;
		this.apellido = apellido;
		LocalDate fechaDeAlta = LocalDate.parse(stringFechaDeAlta);
		this.fechaDeAlta = fechaDeAlta;
		this.identificador = identificador;
		this.nombreDeUsuario = nombreDeUsuario;
		this.contrasena = contrasena;
	}
	
	public LocalDate getFechaDeAlta() {
		return this.fechaDeAlta;
	}

	public int mesesComoAdministrador() { 
		return this.fechaDeAlta.until(LocalDate.now()).getMonths();
	}
}
