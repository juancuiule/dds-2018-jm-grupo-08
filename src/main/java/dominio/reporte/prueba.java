package dominio.reporte;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Convert;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.mockito.Mock.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import static org.mockito.Mockito.*;

import dominio.Cliente;
import dominio.TipoDeDocumento;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.ConversorDeFecha;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFisico;
import dominio.transformadores.Punto;
import dominioTest.mocks.DispositivoFisicoMock;

public class prueba {

	public static void main(String[] args) {
//		ReporteHogar reporteHogar = new ReporteHogar();
	ReporteDispositivo reporteDispositivo = new ReporteDispositivo();
		EntityManager manager =  PerThreadEntityManagers.getEntityManager();
		
		
		LocalDate fechaDesde = LocalDate.of(2018,02,25);
		LocalDate fechaHasta = LocalDate.of(2018,03,8);

		
		
//		Query query= manager.createNativeQuery("select avg(kwConsumidos) promedioKw\r\n" + 
//				"from consumo C left join dispositivo D on C.dispositivo_id= D.id join cliente CL on C.cliente_id = CL.id\r\n" + 
//				"where CL.numeroDeDocumento = ?1\r\n" + 
//				"group by D.nombre");
//		query.setParameter(1,new Integer(33333334));
//		
//		List<Double> result = query.getResultList();
		
		// query para los nombresde los dispositivos
//		Query query2= manager.createNativeQuery("select avg(kwConsumidos) promedioKw, D.nombre\r\n" + 
//				"from consumo C left join dispositivo D on C.dispositivo_id= D.id join cliente CL on C.cliente_id = CL.id\r\n" + 
//				"where CL.numeroDeDocumento = ?1\r\n" + 
//				"group by D.nombre");
//		
//		query2.setParameter(1,new Integer(33333334));
		
//		List<Map<Double,String>> resultAvg = query2.getResultList();
	
//		List<Object[]> listDatos2=reporteDispositivo.consumoPromedioPorDispositivo(new Integer(33333334));
//		List<Object[]> listDatos =  query2.getResultList();
//		 for (Object[] datos : listDatos2) {
//		     System.out.println(datos[0] + "--" + datos[1]);
//		 }
		
		 Query query= manager.createNativeQuery("select sum(kwConsumidos) totalDeKw,CL.nombre, CL.apellido, CL.numeroDeDocumento\r\n" + 
		 		"from consumo C join cliente CL on C.cliente_id = CL.id\r\n" + 
		 		"where (C.fechaInicio <= ?1 and C.fechaFin >= ?2 ) and CL.numeroDeDocumento = ?3\r\n" + 
		 		"group by CL.nombre, CL.apellido, CL.numeroDeDocumento");
	      
		 query.setParameter(1,Date.valueOf(fechaDesde));
		 query.setParameter(2,Date.valueOf(fechaHasta));
		 query.setParameter(3,new Integer(33333334));
		 
		 List<Object[]> listDatos =  query.getResultList();
		 for (Object[] datos : listDatos) {
		     System.out.println(datos[0]+ "--"+datos[1]+"--"+datos[2]+"--"+datos[3]);
		 }
		 
		 System.out.println(listDatos.size());
		 
		 
	}

}
