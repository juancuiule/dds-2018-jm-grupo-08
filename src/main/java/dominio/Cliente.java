package dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

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
	private List<Dispositivo> dispositivos;
	private Optional<Categoria> categoria;

	public TipoDeDocumento getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public LocalDate getFechaDeAlta() {
		return fechaDeAlta;
	}

	public Optional<Categoria> getCategoria() {
		return this.categoria;
	}

	public Cliente(String nombre, String apellido, TipoDeDocumento tipoDeDocumento, Integer numeroDeDocumento,
			Integer telefono, LocalDate fechaDeAlta, String domicilio, String nombreDeUsuario, String contrasena,
			List<Dispositivo> dispositivos) {
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
		
		this.recategorizar();
	}

	public Boolean hayAlgunDispositivoEncendido() {
		
		return this.cantidadDeDispositivosBajoCondicion(dispositivo -> dispositivo.estaEncendido()) > 0;
		/*return this.dispositivos.stream().anyMatch((Dispositivo dispositivo) -> dispositivo.estaEncendido()); /* corregir*/
		
	}

	public Long cantidadDeDispositivosBajoCondicion(Predicate<Dispositivo> unaCondicion) {
		
		return this.dispositivos.stream().filter(unaCondicion).count();
	}

	public Integer cantidadDeDispositivos() {
		return this.dispositivos.size();
	}

	public Double consumo() {
		return this.dispositivos.stream().(this.cantidadDeDispositivosBajoUnaCondicion(dispositivo -> dispositivo.estaEncendido())) /*FIJENSE POR QUE ROMPE EL PUNTO ESE*/
				.mapToDouble((Dispositivo dispositivo) -> dispositivo.getkWh()).sum();
	}

	public void recategorizar() {
		RepositorioCategorias repositorio = RepositorioCategorias.getInstance();
		this.categoria = repositorio.categorias().stream()
				.filter(categoria -> categoria.correspondeCategoria(this.consumo())).findFirst();
	}

}
