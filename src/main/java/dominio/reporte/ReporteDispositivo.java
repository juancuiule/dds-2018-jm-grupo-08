package dominio.reporte;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.Cliente;

public class ReporteDispositivo {

	EntityManager manager =  PerThreadEntityManagers.getEntityManager();
	
	public String consumoPromedioPorDispositivo(Cliente cliente) {
		//seria mejor que se filtre por el id del cliente y no por su dni??
		Integer dni = cliente.getNumeroDeDocumento();
		
		Query query= manager.createQuery("select avg(c.kwConsumidos), d.nombre " + 
				"from consumo c LEFT JOIN dispositivo d ON c.dispositivo_id= d.id JOIN cliente ON d.cliente_id = c.cliente.id " + 
				"where c.numeroDeDocumento = ? " + 
				"group by d.nombre");
		
		
		
//		Query query= manager.createQuery("from consumo as c " + 
//				"join c.cliente_id as clie " + 
//				"join c.dispositivo_id as dis");
				
		query.setParameter(0,dni);
		
		//
		String kwConsumidos = query.getParameter(0).toString();
		
		return kwConsumidos;
		
	}
}
