package dominio;
import java.time.LocalDate;
import java.util.List;

public class Cliente {
	private String nombre;
	private String apellido;
	private TipoDeDocumento tipoDeDocumento;
	private Integer numeroDeDocumento;
	private Integer telefono;
	private LocalDate fechaDeAlta;
	private String domicilio;
	private String nombreDeUsuario;
	private String contrasena;	
	private List <Dispositivo> dispositivos;

    public TipoDeDocumento getTipoDeDocumento() {
        return tipoDeDocumento;
    }
    
    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }


    public Cliente(String nombre, String apellido, TipoDeDocumento tipoDeDocumento, Integer numeroDeDocumento, Integer telefono, LocalDate fechaDeAlta, String domicilio, String nombreDeUsuario, String contrasena, List <Dispositivo> dispositivos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.telefono = telefono;		
		this.fechaDeAlta = fechaDeAlta;
		this.domicilio = domicilio;
		this.nombreDeUsuario = nombreDeUsuario;
		this.contrasena = contrasena;		
		this.dispositivos = dispositivos; 
	}
	
	public Boolean hayAlgunDispositivoEncendido() {
		return this.dispositivos.stream().anyMatch((Dispositivo dispositivo) -> dispositivo.estaEncendido());
	}
	
	public Long cantidadDeDispositivosEncendido() {
		return this.dispositivos.stream().filter((Dispositivo dispositivo) -> dispositivo.estaEncendido()).count();
	}
	
	public Long cantidadDeDispositivosApagados() {
		return this.dispositivos.stream().filter((Dispositivo dispositivo) -> dispositivo.estaApagado()).count();
	}
	 
	public Integer cantidadDeDispositivos() {
		return this.dispositivos.size();
	}	
}
