package db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.Cliente;
import dominio.TipoDeDocumento;
import dominio.dispositivo.Comportamiento;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFactory;
import dominio.dispositivo.Rango;
import dominio.transformadores.Punto;
import dominio.transformadores.RepositorioTransformadores;
import dominio.transformadores.Transformador;
import dominioTest.mocks.DispositivoFisicoMock;

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

