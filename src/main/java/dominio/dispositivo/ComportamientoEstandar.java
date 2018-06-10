package dominio.dispositivo;

import java.time.Period;

public class ComportamientoEstandar implements Comportamiento {
    private Double consumoPorHora;
    private Double horasDeUsoPorDia;

    public ComportamientoEstandar(Double consumoPorHora, Double horasDeUsoPorDia) {
        super();
        if(horasDeUsoPorDia > 24d) {
            throw new ComportamientoNoAdmitidoException();
        }
        this.consumoPorHora = consumoPorHora;
        this.horasDeUsoPorDia = horasDeUsoPorDia;
    }

    @Override
    public Boolean estaEncendido() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new ComportamientoNoAdmitidoException();
    }

    @Override
    public Boolean estaApagado() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new ComportamientoNoAdmitidoException();
    }

    @Override
    public Double consumoEnUltimasHoras(Double horas) {
        return consumoEnHoras(horas);
    }

    @Override
    public Double consumoEnElPeriodo(Period periodo) {
        Double horasDeUso = periodo.getDays() * 24d; 
        return consumoEnHoras(horasDeUso);
    }

    @Override
    public void apagar() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new ComportamientoNoAdmitidoException();
    }

    @Override
    public void encender() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new ComportamientoNoAdmitidoException();
    }

    @Override
    public void ahorrarEnergia() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new ComportamientoNoAdmitidoException();
    }
    
    /* PRIVATE METHODS*/
    private Double porcentajeDeUso(){
        Double horasDelDia = new Double(24);
        return 100 * horasDeUsoPorDia / horasDelDia;
        
    }
    
    private Double consumoEnHoras(Double horas) {
        return porcentajeDeUso() * horas * consumoPorHora;
    }
}