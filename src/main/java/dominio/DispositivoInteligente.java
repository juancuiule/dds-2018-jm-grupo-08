package dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class DispositivoInteligente extends Dispositivo{
    private String nombre;
    private Double kWh;
    private Boolean encendido;
    private Boolean ahorroDeEnergia;
    private List<Uptime> uptime;
    private Optional<LocalDate> fechaDeEncendido;
    
    public Boolean getAhorroDeEnergia() {
    	return this.ahorroDeEnergia;
    }
    
    public DispositivoInteligente(Cliente cliente,String nombre, Double kWh, Boolean encendido) {
        super(nombre, kWh);
        cliente.agregarPuntaje(15);
        this.encendido = encendido;
    }
    
    public DispositivoInteligente(Cliente cliente,DispositivoEstandar primitivo, Boolean encendido) {
        super(primitivo.getNombre(), primitivo.getkWh());
        cliente.agregarPuntaje(10);
        this.encendido = encendido;
    }

    public void apagar() { 
        this.encendido = false;
        registrarConsumo();
        fechaDeEncendido = Optional.empty();
    }

    public void encender() {
        this.ahorroDeEnergia = false;
        this.encendido = true;
        registrarConsumo();
    }
    
    public void activarAhorroDeEnergia() {
        this.ahorroDeEnergia = true;
        registrarConsumo();
    }

    public Boolean estaEncendido() {
        return this.encendido;
    }

    public Boolean estaApagado() { 
        return !this.estaEncendido();
    }
    
    public Double consumoUltimasHoras(Integer horas) {
        return consumoEntre(LocalDate.now(), LocalDate.now().minus(horas,ChronoUnit.HOURS));
    }
    
    private void registrarConsumo() {
        fechaDeEncendido.ifPresent((fechaDeEncendido) ->
            uptime.add(new Uptime(kWh, fechaDeEncendido, LocalDate.now()))
        );
        fechaDeEncendido = Optional.of(LocalDate.now());
    }

    @Override
    public Double consumoEntre(LocalDate inicio, LocalDate fin) {
        return uptime.stream().mapToDouble((uptime)->uptime.consumoEntre(inicio, fin)).sum();
    }
    

}