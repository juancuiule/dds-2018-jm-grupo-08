package dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

import dominio.dispositivo.Dispositivo;
import dominio.transformadores.Punto;
import dominio.transformadores.RepositorioTransformadores;
import dominio.transformadores.Transformador;

@Entity
@Table(name = "Cliente")
public class Cliente extends PersistentObject{
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

    @Embedded
    private Punto punto;

    @Enumerated(value = EnumType.STRING)
    private TipoDeDocumento tipoDeDocumento;

    @OneToOne
    private Categoria categoria;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Dispositivo> dispositivos;

    //Constructor
    @SuppressWarnings("unused")
    private Cliente() {}

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

    // Accessors
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Boolean getAhorroAutomatico() {
        return ahorroAutomatico;
    }

    public void setAhorroAutomatico(Boolean ahorroAutomatico) {
        this.ahorroAutomatico = ahorroAutomatico;
    }

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public TipoDeDocumento getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(TipoDeDocumento tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    // Public methods
    public ArrayList<Double> consumosPorHora() {
        return (ArrayList<Double>) dispositivos.stream().map(dispositivo -> dispositivo.consumoPorHora())
                .collect(Collectors.toList());
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

    public void asignarTransformador() {
        RepositorioTransformadores.getInstance().asignarTransformador(this);
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

    public void activarAhorroAutomatico() {
        this.ahorroAutomatico = true;
    }

    public void desactivarAhorroAutomatico() {
        this.ahorroAutomatico = false;
    }

}
