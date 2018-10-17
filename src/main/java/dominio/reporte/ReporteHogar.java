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
	
	public Double consumoHogar(LocalDate fechaInicio, LocalDate fechaFin, Cliente cliente) {
		double dni = cliente.getNumeroDeDocumento();
		
		Query query= manager.createNativeQuery("select sum(kwConsumidos) totalDeKw" + 
		 		"from consumo C join cliente CL on C.cliente_id = CL.id\r\n" + 
		 		"where (C.fechaInicio >= ?1 and C.fechaFin <= ?2 ) and CL.numeroDeDocumento = ?3\r\n" + 
		 		"group by CL.nombre, CL.apellido, CL.numeroDeDocumento");
		
		query.setParameter(1,Date.valueOf(fechaInicio));
		query.setParameter(2,Date.valueOf(fechaFin));
		query.setParameter(3,dni);
		
		List<Object> listDatos =  query.getResultList();
		 
		 Double resultado = Double.parseDouble(listDatos.get(0).toString());
		
		return resultado;
	
	
	
	}
	
}
