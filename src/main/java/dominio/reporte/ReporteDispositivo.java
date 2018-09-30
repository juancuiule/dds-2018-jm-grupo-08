package dominio.reporte;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.Cliente;

public class ReporteDispositivo {

	EntityManager manager =  PerThreadEntityManagers.getEntityManager();
	
	public String consumoPromedioPorDispositivo(Cliente cliente) {
		//seria mejor que se filtre por el id del cliente y no por su dni??
		String dni = cliente.getNumeroDeDocumento().toString();
		
		Query query= manager.createQuery("select avg(kwConsumidos), dispositivo.nombre\r\n" + 
				"from consumo LEFT JOIN dispositivo ON dispositivo_id= dispositivo.id JOIN cliente ON consumo.cliente_id = cliente.id\r\n" + 
				"where cliente.numeroDeDocumento =: dni\r\n" + 
				"group by nombre");
		
		query.setParameter("dni",dni);
		
		//
		String kwConsumidos = query.getParameter(0).toString();
		
		return kwConsumidos;
		
	}
}
