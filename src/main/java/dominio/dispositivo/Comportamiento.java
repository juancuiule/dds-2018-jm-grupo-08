package dominio.dispositivo;

import java.time.Period;

public interface Comportamiento {
    Boolean estaEncendido();
    Boolean estaApagado();
    Double consumoEnUltimasHoras(Double horas);
    Double consumoEnElPeriodo(Double diasUltimoMes);
    void apagar();
    void encender();
    void ahorrarEnergia();
    Double consumoPorHora();
	void restringirConsumo();
}
