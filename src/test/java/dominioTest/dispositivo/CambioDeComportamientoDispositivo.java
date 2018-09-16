package dominioTest.dispositivo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFactory;
import dominio.dispositivo.InterfazDeFabrica;


public class CambioDeComportamientoDispositivo {
	
	DispositivoFactory tvEstandar= new DispositivoFactory();
	Dispositivo tv = new Dispositivo(null, null, null);
	InterfazDeFabrica fabrica;
	
	@Before
	public void instanciaTvEstandar() {
		tv = tvEstandar.tvFluo21();
	}
	
	@Test
	public void cambiarElComportamientoAinteligente() {
		tv.cambiarComportamiento(new ComportamientoInteligente(fabrica, 1d));
		Assert.assertTrue(tv.consumoPorHora() == 1d);
//		Assert.assertEquals(tv.consumoPorHora(), 1d);
	}

}
