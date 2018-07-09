package dominio.dispositivo;

import java.time.Period;

public interface InterfazDeFabrica {

	Double consumoEnLasUltimasHoras();

	Double consumoEnElPeriodo(Period periodo);

	Boolean estaEncendido();

	Boolean estaApagado();

	void apagar();

	void encender();

	void ahorrarEnergia();

	Double consumoPorHora();
}
