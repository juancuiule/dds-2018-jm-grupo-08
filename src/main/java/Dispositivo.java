
public class Dispositivo {
	private String nombre;
	private Integer kWh;
	private Boolean encendido;
	
	public Dispositivo(String nombre, Integer kWh, Boolean encendido) {
		this.nombre = nombre;
		this.kWh = kWh;
		this.encendido = encendido;
	}

	public void apagar() {
		this.encendido = false;
	}

	public void encender() {
		this.encendido = true;
	}

	public Boolean getEncendido() {
		return this.encendido;
	}
}