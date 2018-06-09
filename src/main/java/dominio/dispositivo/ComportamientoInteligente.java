package dominio.dispositivo;

import java.time.Period;

public class ComportamientoInteligente implements Comportamiento {
    private Boolean encendido;
    private InterfazDeFabrica interfazDeFabrica;

    public ComportamientoInteligente(InterfazDeFabrica interfazDeFabrica) {
        super();
        this.interfazDeFabrica = interfazDeFabrica;
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
        return interfazDeFabrica.consumoEnLasUltimasHoras();
    }

    @Override
    public Double consumoEnElPeriodo(Period periodo) {
        return interfazDeFabrica.consumoEnElPeriodo(periodo);
    }

    @Override
    public void apagar() {
        interfazDeFabrica.apagar();
        encendido = false;
    }

    @Override
    public void encender() {
        interfazDeFabrica.encender();
        encendido = false;
    }

    @Override
    public void ahorrarEnergia() {
        interfazDeFabrica.ahorrarEnergia();
    }
}
