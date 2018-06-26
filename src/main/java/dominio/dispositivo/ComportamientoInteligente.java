package dominio.dispositivo;

import java.time.Period;

public class ComportamientoInteligente implements Comportamiento {
    private InterfazDeFabrica interfazDeFabrica;

    public ComportamientoInteligente(InterfazDeFabrica interfazDeFabrica) {
        this.interfazDeFabrica = interfazDeFabrica;
    }

    @Override
    public Boolean estaEncendido() {
        return interfazDeFabrica.estaEncendido();
    }

    @Override
    public Boolean estaApagado() {
        return interfazDeFabrica.estaApagado();
    }

    @Override
    public Double consumoEnUltimasHoras(Double horas) {
        return interfazDeFabrica.consumoEnLasUltimasHoras();
    }

    @Override
    public Double consumoEnElPeriodo(Period periodo) {
        return interfazDeFabrica.consumoEnElPeriodo(periodo);
    }

    @Override
    public void apagar() {
        interfazDeFabrica.apagar();
    }

    @Override
    public void encender() {
        interfazDeFabrica.encender();
    }

    @Override
    public void ahorrarEnergia() {
        interfazDeFabrica.ahorrarEnergia();
    }
    
    @Override
    public ComportamientoInteligente convertirAInteligente() {
    	// El dispositivo estandar no admite este tipo de comportamiento
        throw new MensajeNoEntendidoException();
    }
}
