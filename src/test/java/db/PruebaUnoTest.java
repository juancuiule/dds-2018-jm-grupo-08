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
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFactory;
import dominio.transformadores.Punto;

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
	
	
	
	
	private List<Dispositivo> listaDeDispositivos(){
		
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
		Dispositivo aire3500 = new DispositivoFactory().aire3500();
		Dispositivo ventiladorPie = new DispositivoFactory().ventiladorPie();
		
		dispositivos.add(ventiladorPie);
		dispositivos.add(aire3500);	
		
		return dispositivos;
		
	}
	
	
	
}
