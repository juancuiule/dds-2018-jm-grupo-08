package dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dominio.dispositivo.Dispositivo;
import dominio.transformadores.Punto;
import dominio.transformadores.RepositorioTransformadores;
import dominio.transformadores.Transformador;

@Entity
@Table(name = "Cliente")
public class Cliente {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nombre;
	private String apellido;
	private Integer numeroDeDocumento;
	private Integer telefono;
	private LocalDate fechaDeAlta;
	private String domicilio;
	private String nombreDeUsuario;
	private String contrasena;
	private Integer puntaje;
	private Boolean ahorroAutomatico;
	
	@OneToOne
	private Punto punto;
	
	@Enumerated(value = EnumType.STRING)
	private TipoDeDocumento tipoDeDocumento;
	
	@OneToOne
	private Categoria categoria;
	
	@OneToMany
	@JoinColumn(name = "cliente_id")
	private List<Dispositivo> dispositivos;
	
	@SuppressWarnings("unused")
	private Cliente() {}

	public ArrayList<Double> consumosPorHora() {
		return (ArrayList<Double>) dispositivos.stream().map(dispositivo -> dispositivo.consumoPorHora())
				.collect(Collectors.toList());
	}

	public TipoDeDocumento getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public LocalDate getFechaDeAlta() {
		return fechaDeAlta;
	}

	public Categoria categoria() {
		return this.categoria;
	}

	public List<Dispositivo> dispositivos() {
		return dispositivos;
	}

	public void agregarDispositivo(Dispositivo dispositivo) {
		this.dispositivos.add(dispositivo);
	}

	public Cliente(String nombre, String apellido, TipoDeDocumento tipoDeDocumento, Integer numeroDeDocumento,
			Integer telefono, LocalDate fechaDeAlta, String domicilio, String nombreDeUsuario, String contrasena,
			List<Dispositivo> dispositivos, Boolean ahorroAutomatico, Punto punto) {
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
		this.ahorroAutomatico = ahorroAutomatico;
		this.puntaje = 0;
		this.punto = punto;
		
		this.recategorizar();
		this.asignarTransformador();
	}
	
	public void asignarTransformador() {
		RepositorioTransformadores.getInstance().asignarTransformador(this);
	}

	public Punto getPunto() {
		return punto;
	}

	public Stream<Dispositivo> dispositivosQueCumplen(Predicate<Dispositivo> unaCondicion) {
		return this.dispositivos.stream().filter(unaCondicion);
	}

	public Stream<Dispositivo> dispositivosEncendidos() {
		return dispositivosQueCumplen(dispositivo -> dispositivo.estaEncendido());
	}

	public int cantidadDeDispositivos() {
		return this.dispositivos.size();
	}

	public int cantidadDeDispositivosEncendidos() {
		return (int) this.dispositivosEncendidos().count();
	}

	public int cantidadDeDispositivosApagados() {
		return this.cantidadDeDispositivos() - this.cantidadDeDispositivosEncendidos();
	}

	public Boolean hayAlgunDispositivoEncendido() {
		return this.cantidadDeDispositivosEncendidos() > 0;
	}

	public Double consumo(Double diasUltimoMes) {
		return this.dispositivos().stream()
				.mapToDouble((Dispositivo dispositivo) -> dispositivo.consumoEnElPeriodo(diasUltimoMes)).sum();
	}

	public void recategorizar() {
		double diasUltimoMes = ChronoUnit.DAYS.between( LocalDate.now().plusMonths(-1),LocalDate.now());
		RepositorioCategorias repositorio = RepositorioCategorias.getInstance();
		this.categoria = repositorio.categoriaCorrespondiente(this.consumo(diasUltimoMes));
	}

	public Boolean getAhorroAutomatico() {
		return ahorroAutomatico;
	}

	public void activarAhorroAutomatico() {
		this.ahorroAutomatico = true;
	}

	public void desactivarAhorroAutomatico() {
		this.ahorroAutomatico = false;
	}

}
