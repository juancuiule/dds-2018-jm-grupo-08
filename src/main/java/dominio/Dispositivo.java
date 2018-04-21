package dominio;

public class Dispositivo {
	private String nombre;
    private Integer kWh;
    private Boolean encendido;
    
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getkWh() {
        return kWh;
    }

    public void setkWh(Integer kWh) {
        this.kWh = kWh;
    }

    public Boolean getEncendido() {
        return encendido;
    }

    public void setEncendido(Boolean encendido) {
        this.encendido = encendido;
    }


	
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

	public Boolean estaEncendido() {
		return this.encendido == true;
	}
	
	public Boolean estaApagado() {
		return this.encendido == false;
	}
	
	public Boolean esIgualA(Dispositivo dispositivoAComparar) {
	    return this.getNombre().equals(dispositivoAComparar.getNombre())  &&
	           this.getkWh() == dispositivoAComparar.getkWh() &&
	           this.getEncendido() == dispositivoAComparar.getEncendido();
	}

}