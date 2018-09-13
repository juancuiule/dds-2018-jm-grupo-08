package db;

import dominio.Cliente;
import dominio.TipoDeDocumento;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.Rango;
import dominio.transformadores.Punto;
import dominio.transformadores.RepositorioTransformadores;
import dominio.transformadores.Transformador;
import dominioTest.mocks.InterfazDeFabricaMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PruebaUnoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    Cliente raul;

    @Before
    public void fixture() {
        RepositorioTransformadores.getInstance().agregar(new Transformador(new Punto(2d,2d),true));
        raul = new Cliente("Raul","Gomez",TipoDeDocumento.DNI,
                new Integer(33333334), new Integer(800445),
                LocalDate.of(2018, 01, 11), "Lujan","raulG","1234",
                this.listaDeDispositivos(),true,new Punto(1d,2d));
    }

    @Test
    public void persistirAraul() {
        entityManager().persist(raul);
        Query query= entityManager().createQuery("from Cliente");
        List<Cliente> clienteRaul = query.getResultList();
        Assert.assertEquals(clienteRaul.get(0), raul);
    }

    @Test
    public void persistirCambiosGeolocalizacion() {
    	entityManager().persist(raul);
    	
    	Query query= entityManager().createQuery("from Cliente");
    	List<Cliente> clienteRaul = query.getResultList();
    	Punto nuevoPunto = new Punto(2d,1d);
    	clienteRaul.get(0).setPunto(nuevoPunto);
    	entityManager().persist(clienteRaul.get(0));
    	
    	Query query2 = entityManager().createQuery("from Cliente");
    	clienteRaul = query2.getResultList();
    	Assert.assertEquals(clienteRaul.get(0).getPunto(), nuevoPunto);
    }

    // Private methods
    private List<Dispositivo> listaDeDispositivos(){
        ComportamientoInteligente comportamientoI = new ComportamientoInteligente(new InterfazDeFabricaMock(),0.2);
        ComportamientoEstandar conportamientoE = new ComportamientoEstandar(1.2, 6d);
        List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
        Dispositivo aire3500 = new Dispositivo(comportamientoI, "aire", new Rango(0.1, 0.72));
        Dispositivo ventiladorPie = new Dispositivo(conportamientoE, "ventilador", new Rango(0.24, 0.745));

        dispositivos.add(ventiladorPie);
        dispositivos.add(aire3500);

        return dispositivos;
    }

}
