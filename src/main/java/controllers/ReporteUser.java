package controllers;

public class ReporteUser {
	
	private String nombre;
	private String apellido;
	private Integer numeroDeDocumento;
	private String domicilio;
	private Double consumoPromedio;
	
	
	//Getters y Setters
	
	public ReporteUser(String nombre, String apellido, Integer numeroDeDocumento, String domicilio,
			Double consumoPromedio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroDeDocumento = numeroDeDocumento;
		this.domicilio = domicilio;
		this.consumoPromedio = consumoPromedio;
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
	public Integer getNumeroDeDocumento() {
		return numeroDeDocumento;
	}
	public void setNumeroDeDocumento(Integer numeroDeDocumento) {
		this.numeroDeDocumento = numeroDeDocumento;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public Double getConsumoPromedio() {
		return consumoPromedio;
	}
	public void setConsumoPromedio(Double consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}
	
	

}
