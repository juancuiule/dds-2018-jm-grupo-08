package dominio.reporte;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.transformadores.Punto;

public class ReporteTransformador {

	EntityManager manager =  PerThreadEntityManagers.getEntityManager();
	
	public Double consumoTransformador(LocalDate fechaInicio, LocalDate fechaFin, Punto punto) {
		
		Query query = manager.createNativeQuery("SELECT SUM(kwConsumidos)" +
				"FROM Transformador T JOIN Cliente C ON T.id = C.transformador_id" +
				"WHERE T.Punto = ?1 AND C.fechaInicio >= ?1 AND C.fechaFin <= ?2" +
				"GROUP BY T.id");
		
		query.setParameter(1, punto);
		query.setParameter(2,Date.valueOf(fechaInicio));
		query.setParameter(3,Date.valueOf(fechaFin));
		
		@SuppressWarnings("unchecked")
		List<Object> listDatos =  query.getResultList();
		
		Double resultado = Double.parseDouble(listDatos.get(0).toString());
		
		return resultado;
	
	}

}