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

import dominio.Cliente;
import dominio.TipoDeDocumento;
import dominio.dispositivo.Comportamiento;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFactory;
import dominio.dispositivo.Rango;
import dominio.transformadores.Punto;
import dominioTest.mocks.InterfazDeFabricaMock;

public class PruebaUnoTest {

	EntityManager manager = PerThreadEntityManagers.getEntityManager();
	
	EntityTransaction transaction =  manager.getTransaction();
	
	Cliente raul = new Cliente("Raul","Gomez",TipoDeDocumento.DNI,
			new Integer(33333334), new Integer(800445), 
			LocalDate.of(2018, 01, 11), "Lujan","raulG","1234",
			this.listaDeDispositivos(),true,new Punto(1d,2d));
	
	@Before
	public void transaccionBegin() {
		transaction.begin();
	}
	
	@After
	public void transaccionRollback() {
		transaction.rollback();
	}
	
	
	@Test
	public void persistirAraul() {
		 manager.persist(raul);
		 
		 Query query= manager.createQuery("from Cliente");
		 
		 List<Cliente> clienteRaul = query.getResultList();
		 
		 Assert.assertEquals(clienteRaul.get(0), raul);
		 
	}
	
	
	@Test
	public void persistirCambiosGeolocalizacion() {
		 manager.persist(raul);
		 
		 Query query= manager.createQuery("from Cliente");
		 
		 List<Cliente> clienteRaul = query.getResultList();
		 
		 Punto nuevoPunto = new Punto(2d,1d);
		 
		 clienteRaul.get(0).setPunto(nuevoPunto);
		 
		 transaction.rollback();
		 
		 manager.persist(clienteRaul.get(0));
		 
		 Query query2 = manager.createQuery("from Cliente");
		 
		 List<Cliente> clienteRaul2 = query2.getResultList();
		 
		 Assert.assertEquals(clienteRaul.get(0).getPunto(), nuevoPunto);
		 
		 
	}
	
	
	private List<Dispositivo> listaDeDispositivos(){
				
		ComportamientoInteligente comportamientoI = new ComportamientoInteligente(new InterfazDeFabricaMock(),0.2);
		ComportamientoEstandar comportamientoE = new ComportamientoEstandar(1.2, 6d);
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
		Dispositivo aire3500 = new Dispositivo(comportamientoI, "aire", new Rango(0.1, 0.72));
		Dispositivo ventiladorPie = new Dispositivo(comportamientoE, "ventilador", new Rango(0.24, 0.745));
		
		
		dispositivos.add(ventiladorPie);
		dispositivos.add(aire3500);	
		
		return dispositivos;
		
	}
	
}
