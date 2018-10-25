package dominio.reporte;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.Cliente;

public class ReporteHogar {

	EntityManager manager = PerThreadEntityManagers.getEntityManager();

	public Double consumoHogar(LocalDate fechaInicio, LocalDate fechaFin, Cliente cliente) {
		double dni = cliente.getNumeroDeDocumento();

		Query query = manager.createNativeQuery(
				"select sum(kwConsumidos) totalDeKw " + "from Consumo C join Cliente CL on C.cliente_id = CL.id\r\n"
						+ "where (C.fechaInicio >= ?1 and C.fechaFin <= ?2 ) and CL.numeroDeDocumento = ?3\r\n"
						+ "group by CL.nombre, CL.apellido, CL.numeroDeDocumento");

		query.setParameter(1, Date.valueOf(fechaInicio));
		query.setParameter(2, Date.valueOf(fechaFin));
		query.setParameter(3, dni);

		List listDatos = query.getResultList();
		Double resultado;
		if (listDatos.isEmpty())
			return 0d;
		else {
			resultado = Double.parseDouble(listDatos.get(0).toString());
		}
		return resultado;
	}

	public List<SubPeriodo> subperiodosParaMes(Mes mes, Cliente cliente) {
		LocalDate inicio = LocalDate.parse("1." + mes.getValue() + ".2018");
		LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());
		Double consumo = this.consumoHogar(inicio, fin, cliente);
		return new ArrayList<SubPeriodo>(Arrays.asList(new SubPeriodo("1-" + inicio.lengthOfMonth(), consumo, consumo)));
	}

	public Double consumoMesPasado(Cliente cliente) {
		Integer month = LocalDate.now().getMonthValue();
		LocalDate inicio = LocalDate.parse("1." + month + ".2018");
		LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());
		return this.consumoHogar(inicio, fin, cliente);
	}
}
