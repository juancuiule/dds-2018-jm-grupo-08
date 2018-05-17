package dominioTest;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import dominio.Cliente;
import dominio.TipoDeDocumento;
import dominio.DispositivoEstandar;
import dominio.DispositivoInteligente;
import dominio.Dispositivo;

public class DispositivoTest {
	Cliente cliente = new Cliente("pepe", "casas", TipoDeDocumento.DNI, 32435465, 1559293933, LocalDate.now(), "Medrano 971", "pepe", "pepe", new ArrayList<Dispositivo>());

	DispositivoEstandar dispositivoEstandar = new DispositivoEstandar("microondas", 1.5, 2);
	DispositivoInteligente dispositivoInteligente = new DispositivoInteligente(cliente, "heladera", 3.2, true);
	DispositivoInteligente dispositivoAdaptado = new DispositivoInteligente(cliente, dispositivoEstandar, true);

	@Test
	public void dispositivoEstandar_estaEncendido_siempreFalse() {
		assertFalse(dispositivoEstandar.estaEncendido());
	}

	@Test
	public void dispositivoEstandar_estaApagado_siempreFalse() {
		assertFalse(dispositivoEstandar.estaApagado());
	}

	/*@Test
	public void dispositivoEstandar_consumoEntre() {
		LocalDate inicio = LocalDate.of(2018, 12, 24);
		LocalDate fin = LocalDate.of(2018, 12, 31);
		double resultado = (31 - 24) * 1.5 * 2;
		assertEquals(resultado, dispositivoEstandar.consumoEntre(inicio, fin), 0);
	}*/
	
	@Test
	public void dispositivoInteligente_estaEncendido_esTrue() {
		assertTrue(dispositivoInteligente.estaEncendido());
	}
	
	/*@Test
	public void dispositivoInteligente_apagar_estaApagado() {
		dispositivoInteligente.apagar();
		assertTrue(dispositivoInteligente.estaApagado());
	}
	
	@Test
	public void dispositivoInteligente_encender_estaEncendido() {
		dispositivoInteligente.apagar();
		dispositivoInteligente.encender();
		assertTrue(dispositivoInteligente.estaEncendido());
	}
	
	@Test
	public void dispositivoInteligente_activarAhorraDeEnergia() {
		dispositivoInteligente.activarAhorroDeEnergia();
		assertTrue(dispositivoInteligente.getAhorroDeEnergia());
	}
	
	@Test
	public void dispositivoInteligente_encenderDesactivaAhorraDeEnergia() {
		dispositivoInteligente.activarAhorroDeEnergia();
		dispositivoInteligente.encender();
		assertFalse(dispositivoInteligente.getAhorroDeEnergia());
	}*/
	
	@Test
	public void ahoraDispositivoEstandarAdaptado_estaEncendido() {
		assertTrue(dispositivoAdaptado.estaEncendido());
	}
}
