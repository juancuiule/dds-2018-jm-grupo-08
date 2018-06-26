package dominio.dispositivo;

import java.time.Period;

public interface Comportamiento {
    Boolean estaEncendido();
    Boolean estaApagado();
    Double consumoEnUltimasHoras(Double horas);
    Double consumoEnElPeriodo(Period periodo);
    void apagar();
    void encender();
    void ahorrarEnergia();
    ComportamientoInteligente convertirAInteligente();
    Double consumoPorHora();
}
