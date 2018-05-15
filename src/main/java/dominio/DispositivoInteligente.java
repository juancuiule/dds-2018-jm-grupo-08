package dominio;

import java.time.LocalDate;
import java.time.Period;

public class DispositivoInteligente {
    private String nombre;
    private Double kWh;
    private Boolean encendido;
    private Boolean ahorroDeEnergia;

    public String getNombre() {
        return nombre;
    }

    public Double getkWh() {
        return kWh;
    }

    public Boolean getEncendido() {
        return encendido;
    }

    public DispositivoInteligente(Cliente dueño, String nombre, Double kWh, Boolean encendido) {
        this.nombre = nombre;
        this.kWh = kWh;
        this.encendido = encendido;
        this.ahorroDeEnergia = false;
        
        this.sumarPuntaje(dueño);
    }
    
    public void sumarPuntaje(Cliente dueño) {
        dueño.agregarPuntaje(15);
    }

    public void apagar() { 
        this.encendido = false;
    }

    public void encender() {
        this.ahorroDeEnergia = false;
        this.encendido = true;
    }

    public Boolean estaEncendido() {
        return this.encendido;
    }

    public Boolean estaApagado() { 
        return !this.estaEncendido();
    }

    public Boolean esIgualA(DispositivoInteligente dispositivoAComparar) { /*usar esto directamente en los test*/
        return nombre.equals(dispositivoAComparar.getNombre()) && kWh == dispositivoAComparar.getkWh()
                && encendido == dispositivoAComparar.getEncendido();
    }
    
    public Double cantidadConsumidaEnHoras(Integer horas) {
        return kWh * horas;
    }
    
    public Double cantidadConsumidaEnPeriodo(LocalDate inicio, LocalDate fin) {
        return Period.between(inicio, fin).getDays() * cantidadConsumidaEnHoras(24);
    }
    
    public void activarAhorroDeEnergia() {
        this.ahorroDeEnergia = true;
    }
}