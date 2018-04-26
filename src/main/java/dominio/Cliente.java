package dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	}

	public Boolean hayAlgunDispositivoEncendido() {
		return this.dispositivos.stream().anyMatch((Dispositivo dispositivo) -> dispositivo.estaEncendido());
	}

	public Long cantidadDeDispositivosEncendidos() {
		return this.dispositivos.stream().filter((Dispositivo dispositivo) -> dispositivo.estaEncendido()).count();
	}

	public Long cantidadDeDispositivosApagados() {
		return this.dispositivos.stream().filter((Dispositivo dispositivo) -> dispositivo.estaApagado()).count();
	}

	public Integer cantidadDeDispositivos() {
		return this.dispositivos.size();
	}

	public Double consumo() {
		return this.dispositivos.stream().filter((Dispositivo dispositivo) -> dispositivo.estaEncendido())
				.mapToDouble((Dispositivo dispositivo) -> dispositivo.getkWh()).sum();
	}

	public void recategorizar() {
		RepositorioCategorias repositorio = RepositorioCategorias.getInstance();
		this.categoria = repositorio.categorias().stream()
				.filter(categoria -> categoria.correspondeCategoria(this.consumo())).findFirst();
	}

}
