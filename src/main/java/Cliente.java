import java.time.LocalDate;
import java.util.List;

public class Cliente {
	private String nombre;
	private String apellido;
	private String domicilio;
	private LocalDate fechaDeAlta;
	private Integer numeroDeDocumento;
	private Integer telefono;
	private List <Integer> dispositivos;
	
	public Cliente(String nombre,String apellido,String domicilio,LocalDate fechaDeAlta,Integer numeroDeDocumento,Integer telefono,List <Integer> dispositivos) {
		this.nombre = nombre;
		this. apellido = apellido;
		this.domicilio = domicilio;
		this.fechaDeAlta = fechaDeAlta;
		this.numeroDeDocumento = numeroDeDocumento;
		this.telefono = telefono;
		this.dispositivos = dispositivos; 
	}
	
}
