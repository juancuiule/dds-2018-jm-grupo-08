package dominioTest.dispositivo;

import static org.junit.Assert.*;

import java.time.Period;

import org.junit.Before;
import org.junit.Test;

import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.MensajeNoEntendidoException;

public class ComportamientoEstandarTest {
    private ComportamientoEstandar comportamientoAProbar;

    @Before
    public void fixture() {
        comportamientoAProbar = new ComportamientoEstandar(30d, 6d);
    }

    @Test(expected = MensajeNoEntendidoException.class) 
    public void crearComportamiento_UsoMayorA24HorasPorDia_DebeLanzarExcepcion() {
        new ComportamientoEstandar(30d, 80d);
    }

    @Test(expected = MensajeNoEntendidoException.class) 
    public void estaEncendido_DebeLanzarExcepcion() {
        comportamientoAProbar.estaEncendido();
    }

    @Test(expected = MensajeNoEntendidoException.class)
    public void estaApagado_DebeLanzarExcepcion() {
        comportamientoAProbar.estaApagado();
    }

    @Test(expected = MensajeNoEntendidoException.class)
    public void apagar_DebeLanzarExcepcion() {
        comportamientoAProbar.apagar();
    }

    @Test(expected = MensajeNoEntendidoException.class)
    public void encender_DebeLanzarExcepcion() {
        comportamientoAProbar.encender();
    }

    @Test(expected = MensajeNoEntendidoException.class)
    public void ahorrarEnergia_DebeLanzarExcepcion() {
        comportamientoAProbar.ahorrarEnergia();
    }
    
    @Test
    public void consumoEnUltimasHoras_DadasLasUltimas12Horas_DebeSer360() {
        assertEquals(Double.valueOf(360), comportamientoAProbar.consumoEnUltimasHoras(12d));
    }

    @Test
    public void consumoEnElPeriodo_EnUnPeriodoDe10Dias_DebeSer1800() {
        assertEquals(Double.valueOf(1800),comportamientoAProbar.consumoEnElPeriodo(Period.ofDays(10)));
    }
}
