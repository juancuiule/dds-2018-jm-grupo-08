package db;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.Rango;
import dominioTest.mocks.DispositivoFisicoMock;

// TODO: Cambiar esto por un test de RepositorioDispositivos o algo similar
public class PruebaDosTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    Dispositivo pc;

    @Before
    public void fixture() {
    	ComportamientoInteligente comportamientoI = new ComportamientoInteligente(new DispositivoFisicoMock(),0.4);
    	pc = new Dispositivo(comportamientoI, "pc", new Rango(60d,360d));
    }

    @Test
    public void persistirNuevoNombre() {
        entityManager().persist(pc);
        List<Dispositivo> dispositivoPC = entityManager().createQuery("from Dispositivo where nombre='pc'").getResultList();

        // Mostrar por pantalla todos los intervalos en los que estuvo encendido el ultimo mes
        //System.out.println("Intervalos en los que estuvo encendido durante el ultimo mes:\n");

        dispositivoPC.get(0).setNombre("pcGamer");
        entityManager().persist(dispositivoPC.get(0));

        dispositivoPC = entityManager().createQuery("from Dispositivo").getResultList();
        Assert.assertEquals(dispositivoPC.get(0).getNombre(), "pcGamer");
    }

}

