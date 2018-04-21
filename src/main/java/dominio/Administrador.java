package dominio;
import java.time.Duration;
import java.time.LocalDate;

public class Administrador {
	private String nombre;
	private String apellido;
	private LocalDate fechaDeAlta;
	private Integer identificador;
	private String nombreDeUsuario;
	private String contrasena;	
	
	public Administrador(String nombre, String apellido, LocalDate fechaDeAlta, Integer identificador, String nombreDeUsuario, String contrasena) {
		this.nombre = nombre;
		this.apellido = apellido;	
		this.fechaDeAlta = fechaDeAlta;
		this.identificador = identificador;
		this.nombreDeUsuario = nombreDeUsuario;
		this.contrasena = contrasena;		 
	}
	
	Duration mesesComoAdministrador() { //Hay que testear que valor devuelve
		return Duration.between(this.fechaDeAlta, LocalDate.now());
	}
}
