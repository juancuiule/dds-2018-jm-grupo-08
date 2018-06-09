package dominio.dispositivo;

import java.time.Period;

public interface InterfazDeFabrica {

    Double consumoEnElPeriodo(Period periodo);

    void apagar();

    void encender();

    void ahorrarEnergia();

    Double consumoEnLasUltimasHoras();

}
