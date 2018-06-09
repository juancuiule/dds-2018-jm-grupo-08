package dominio.dispositivo;

import java.time.Period;

public class ComportamientoEstandar implements Comportamiento {
    private Double consumoPorHora;
    private Double horasDeUsoPorDia;
    private Boolean encendido;

    public ComportamientoEstandar(Double consumoPorHora, Double usoPorHora) {
        super();
        this.consumoPorHora = consumoPorHora;
        this.horasDeUsoPorDia = usoPorHora;
    }

    @Override
    public Boolean estaEncendido() {
        return encendido;
    }

    @Override
    public Boolean estaApagado() {
        return !encendido;
    }


    @Override
    public Double consumoEnUltimasHoras(Double horas) {
        return porcentajeDeUso() * horas * consumoPorHora;
    }

    @Override
    public Double consumoEnElPeriodo(Period periodo) {
        Double horasDeUso = periodo.getDays() * 24d; 
        return porcentajeDeUso() * horasDeUso * consumoPorHora;
    }

    @Override
    public void apagar() {
        // El dispositivo estandar no admite este tipo de comportamiento
    }

    @Override
    public void encender() {
        // El dispositivo estandar no admite este tipo de comportamiento
    }

    @Override
    public void ahorrarEnergia() {
        // El dispositivo estandar no admite este tipo de comportamiento
    }
    
    /* PRIVATE METHODS*/
    private Double porcentajeDeUso(){
        Double horasDelDia = new Double(24);
        return 100 * horasDeUsoPorDia / horasDelDia;
        
    }
}