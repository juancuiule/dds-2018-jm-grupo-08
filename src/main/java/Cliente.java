import java.time.LocalDate;
import java.util.List;


public class Cliente {
	private String nombre;
	private String apellido;
	private String domicilio;
	private LocalDate fechaDeAlta;
	private Integer numeroDeDocumento;
	private Integer telefono;
	private List <Dispositivo> dispositivos;
	
	public Cliente(String nombre,String apellido,String domicilio,LocalDate fechaDeAlta,Integer numeroDeDocumento,Integer telefono,List <Dispositivo> dispositivos) {
		this.nombre = nombre;
		this. apellido = apellido;
		this.domicilio = domicilio;
		this.fechaDeAlta = fechaDeAlta;
		this.numeroDeDocumento = numeroDeDocumento;
		this.telefono = telefono;
		this.dispositivos = dispositivos; 
	}
	
	public Boolean hayAlgunDispositivoEncendido() {
		return this.dispositivos.stream().mapToBool(((Dispositivo unDispositivo) -> unDispositivo.getEncendido())).any();
	}
	
	public Integer cantidadDeDispositivosEncendido() {
		return this.dispositivos.length((Dispositivo unDispositivo) -> unDispositivo.getEncendido());
	}
	
	public Integer cantidadDeDispositivosApagados() {
		return this.dispositivos.size((Dispositivo unDispositivo) -> not(unDispositivo.getEncendido()));
	}
	 
	public Integer cantidadDeDispositivos() {
		return this.dispositivos.size();
	}
	
}
