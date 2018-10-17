package dominio.reporte;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.Cliente;

public class ReporteHogar {

	EntityManager manager =  PerThreadEntityManagers.getEntityManager();
	
	public List<Object[]> consumoHogar(LocalDate fechaDesde, LocalDate fechaHasta, Cliente cliente) {
		double dni = cliente.getNumeroDeDocumento();
		
		Query query= manager.createNativeQuery("select sum(kwConsumidos) totalDeKw,CL.nombre, CL.apellido, CL.numeroDeDocumento\r\n" + 
		 		"from consumo C join cliente CL on C.cliente_id = CL.id\r\n" + 
		 		"where (C.fechaInicio <= ?1 and C.fechaFin >= ?2 ) and CL.numeroDeDocumento = ?3\r\n" + 
		 		"group by CL.nombre, CL.apellido, CL.numeroDeDocumento");
		
		query.setParameter("fechaDesde",Date.valueOf(fechaDesde));
		query.setParameter("fechaHasta",Date.valueOf(fechaHasta));
		query.setParameter("dni",dni);
		
		List<Object[]> listReporteHogar =  query.getResultList();
		
		return listReporteHogar;
	}
	
	
}
