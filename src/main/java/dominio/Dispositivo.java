package dominio;

public class Dispositivo {
	private String nombre;
	private Double kWh;
	private Boolean encendido;

	public String getNombre() {
		return nombre;
	}

	public Double getkWh() {
		return kWh;
	}

	public Boolean getEncendido() {
		return encendido;
	}

	public Dispositivo(String nombre, Double kWh, Boolean encendido) {
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

	public Boolean estaEncendido() {
		return this.encendido == true;
	}

	public Boolean estaApagado() { 
		return !this.estaEncendido();
	}

	public Boolean esIgualA(Dispositivo dispositivoAComparar) { /*usar esto directamente en los test*/
		return nombre.equals(dispositivoAComparar.getNombre()) && kWh == dispositivoAComparar.getkWh()
				&& encendido == dispositivoAComparar.getEncendido();
	}

}