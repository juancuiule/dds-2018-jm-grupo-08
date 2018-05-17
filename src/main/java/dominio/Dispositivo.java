package dominio;

import java.time.LocalDate;

public abstract class Dispositivo {
    private String nombre;
    private Double kWh;
    
    public Dispositivo(String nombre, Double kWh) {
        this.nombre = nombre;
        this.kWh = kWh;
    }
    
    public abstract Double consumoEntre(LocalDate inicio,LocalDate fin);
    
    public String getNombre() {
        return nombre;
    }

    public Double getkWh() {
        return kWh;
    }

    public abstract Boolean estaEncendido();
    public abstract Boolean estaApagado();
}
