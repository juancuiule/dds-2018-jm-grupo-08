package dominio.dispositivo;

import javax.persistence.Entity;

@Entity
public class DispositivoFisicoGenerico extends DispositivoFisico{
    @Override
    public Double consumoEnLasUltimasHoras(Double horas) {
        return null;
    }

    @Override
    public Double consumoEnElPeriodo(Double diasUltimoMes) {
        return 500d;
    }

    @Override
    public Boolean estaEncendido() {
        return null;
    }

    @Override
    public Boolean estaApagado() {
        return null;
    }

    @Override
    public void apagar() {

    }

    @Override
    public void encender() {

    }

    @Override
    public void ahorrarEnergia() {

    }

    @Override
    public Double consumoPorHora() {
        return null;
    }
}
