package dominioTest.dispositivo;

import static org.junit.Assert.*;

import java.time.Period;

import org.junit.Before;
import org.junit.Test;

import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.ComportamientoNoAdmitidoException;

// FALTA IMPLEMENTAR UNA CLASE FabricaPosta

public class ComportamientoInteligenteTest {
	private ComportamientoInteligente comportamientoAProbar;
	// private FabricaPosta fabricaAProbar;

    @Before
    public void fixture() {
    	// fabricaAProbar = new FabricaPosta();
        comportamientoAProbar = new ComportamientoInteligente(fabricaAProbar);
    }

    @Test
    public void estaEncendido_DebeRetornarFalse() {
        assertFalse(comportamientoAProbar.estaEncendido());
    }

    @Test
    public void estaApagado_DebeRetornarTrue() {
        assertTrue(comportamientoAProbar.estaApagado());
    }
    
    @Test
    public void encender_estaEncendido_DebeRetornarTrue() {
        comportamientoAProbar.encender();
        assertTrue(comportamientoAProbar.estaEncendido());
    }
    
    @Test
    public void encender_estaApagado_DebeRetornarFalse() {
        comportamientoAProbar.encender();
        assertFalse(comportamientoAProbar.estaApagado());
    }
    
    @Test
    public void encender_apagar_estaEncendido_DebeRetornarFalse() {
    	comportamientoAProbar.encender();
    	comportamientoAProbar.apagar();
        assertFalse(comportamientoAProbar.estaEncendido());
    }
    
    @Test
    public void encender_apagar_estaApagado_DebeRetornarTrue() {
    	comportamientoAProbar.encender();
    	comportamientoAProbar.apagar();
        assertTrue(comportamientoAProbar.estaApagado());
    }    

    @Test
    public void ahorrarEnergia() { // ¿QUE DEBE HACER? -> Falta implementar la clase FabricaPosta
        comportamientoAProbar.ahorrarEnergia();
    }
}
