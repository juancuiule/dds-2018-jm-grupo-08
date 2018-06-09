package dominio.dispositivo;

import java.time.Period;

public interface Comportamiento {
    public Boolean estaEncendido();
    public Boolean estaApagado();
    public Double consumoEnUltimasHoras(Double horas);
    public Double consumoEnElPeriodo(Period periodo);
    public void apagar();
    public void encender();
    public void ahorrarEnergia();
}
