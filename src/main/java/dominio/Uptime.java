package dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Uptime {
    private Double consumoXHora;
    private LocalDate inicioUptime;
    private LocalDate finUptime;
    
    public Uptime(Double consumoXHora, LocalDate inicio, LocalDate fin) {
        super();
        this.consumoXHora = consumoXHora;
        this.inicioUptime = inicio;
        this.finUptime = fin;
    }
    
    public LocalDate getInicio() {
        return inicioUptime;
    }
    
    public Double consumoEntre(LocalDate inicioDelRango ,LocalDate finDelRango) {
        inicioDelRango = inicioDelRango.isAfter(inicioUptime)?inicioDelRango:inicioUptime;
        inicioDelRango = finDelRango.isBefore(finUptime)?finDelRango:finUptime;
        
        Double horasEnElRango = new Double(ChronoUnit.HOURS.between(inicioDelRango, finDelRango));
        return horasEnElRango * consumoXHora; 
    }
}
