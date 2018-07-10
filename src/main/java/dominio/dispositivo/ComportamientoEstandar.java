package dominio.dispositivo;

import java.time.Period;

public class ComportamientoEstandar implements Comportamiento {
    private Double consumoPorHora;
    private Double horasDeUsoPorDia;

    public ComportamientoEstandar(Double consumoPorHora, Double horasDeUsoPorDia) {
        if(horasDeUsoPorDia > 24d) {
            throw new MensajeNoEntendidoException();
        }
        this.consumoPorHora = consumoPorHora;
        this.horasDeUsoPorDia = horasDeUsoPorDia;
    }
    @Override
    public Double consumoPorHora() {
    	return consumoPorHora;
    }

    @Override
    public Boolean estaEncendido() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new MensajeNoEntendidoException();
    }

    @Override
    public Boolean estaApagado() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new MensajeNoEntendidoException();
    }

    @Override
    public Double consumoEnUltimasHoras(Double horas) {
        return consumoEnHoras(horas);
    }

    @Override
    public Double consumoEnElPeriodo(Period periodo) {
        Double horasDeUso = periodo.getDays() * horasDeUsoPorDia; 
        return consumoEnHoras(horasDeUso);
    }

    @Override
    public void apagar() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new MensajeNoEntendidoException();
    }

    @Override
    public void encender() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new MensajeNoEntendidoException();
    }

    @Override
    public void ahorrarEnergia() {
        // El dispositivo estandar no admite este tipo de comportamiento
        throw new MensajeNoEntendidoException();
    }
    
	@Override
	public void restringirConsumo() {
		// TODO Auto-generated method stub
		
	}
    
    /* PRIVATE METHODS*/
    /*private Double porcentajeDeUso(){
        Double horasDelDia = new Double(24);
        return 100 * horasDeUsoPorDia / horasDelDia;
        
    }*/
    
    private Double consumoEnHoras(Double horas) {
        return horas * consumoPorHora;
    }

}