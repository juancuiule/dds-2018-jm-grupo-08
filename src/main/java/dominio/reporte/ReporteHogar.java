package dominio.reporte;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.Cliente;

public class ReporteHogar {

	EntityManager manager =  PerThreadEntityManagers.getEntityManager();
	
	public String consumoHogar(LocalDate fechaDesde, LocalDate fechaHasta, Cliente cliente) {
		String dni = cliente.getNumeroDeDocumento().toString();
		
		Query query= manager.createQuery("select sum(kwConsumidos) "+
		"from consumo JOIN cliente ON consumo.cliente_id = cliente.id "+
				"where (fechaDesde<=: fechaInicio and fechaHasta>=:fechaFin) and cliente.numeroDeDocumento =: dni");
		
		query.setParameter("fechaDesde",fechaDesde);
		query.setParameter("fechaHasta",fechaHasta);
		query.setParameter("dni",dni);
		
		return query.getParameterValue(0).toString();
	}
}
