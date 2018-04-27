package dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
	private Categoria categoria;

	public TipoDeDocumento getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public LocalDate getFechaDeAlta() {
		return fechaDeAlta;
	}

	public Categoria getCategoria() {
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

	public Stream<Dispositivo> dispositivosQueCumplen(Predicate<Dispositivo> unaCondicion) {
		return this.dispositivos.stream().filter(unaCondicion);
	}

	public Stream<Dispositivo> dispositivosEncendidos() {
		return dispositivosQueCumplen(dispositivo -> dispositivo.estaEncendido());
	}

	public Integer cantidadDeDispositivos() {
		return this.dispositivos.size();
	}

	public Integer cantidadDeDispositivosEncendidos() {
		return (int) this.dispositivosEncendidos().count();
	}

	public Integer cantidadDeDispositivosApagados() {
		return this.cantidadDeDispositivos() - (int) this.dispositivosEncendidos().count();
	}

	public Boolean hayAlgunDispositivoEncendido() {
		return this.cantidadDeDispositivosEncendidos() > 0;
	}

	public Double consumo() {
		return this.dispositivosEncendidos().mapToDouble((Dispositivo dispositivo) -> dispositivo.getkWh()).sum();
	}

	public void recategorizar() {
		RepositorioCategorias repositorio = RepositorioCategorias.getInstance();
		this.categoria = repositorio.categorias().stream()
				.filter(categoria -> categoria.correspondeCategoria(this.consumo())).findFirst().get();
	}

}
