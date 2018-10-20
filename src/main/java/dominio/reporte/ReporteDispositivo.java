package dominio.reporte;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class ReporteDispositivo {

	EntityManager manager =  PerThreadEntityManagers.getEntityManager();
	
	public List<Object[]> consumoPromedioPorDispositivo(Integer dni) {
		//seria mejor que se filtre por el id del cliente y no por su dni??
		
		Query query= manager.createNativeQuery("select avg(kwConsumidos) promedioKw, D.nombre\r\n" + 
				"from consumo C left join dispositivo D on C.dispositivo_id= D.id join cliente CL on C.cliente_id = CL.id\r\n" + 
				"where CL.numeroDeDocumento = ?1\r\n" + 
				"group by D.nombre");
				
		query.setParameter(1,dni);
		
		@SuppressWarnings("unchecked")
		List<Object[]> kwConsumidos =  query.getResultList();
		
		return kwConsumidos;
		
	}
}
