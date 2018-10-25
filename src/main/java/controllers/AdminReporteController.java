package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dominio.Cliente;
import dominio.RepositorioClientes;
import dominio.reporte.ReporteHogar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminReporteController {
	
	public static ModelAndView respond(Request req, Response res) {
        return new ModelAndView(generarDominio(req), "admin-dashboard.hbs");
    }

	private static HashMap<String, Object> generarDominio(Request req) {
		String fechaIni = req.queryParams("fechaInicio");
		String fechaFin = req.queryParams("fechaFin");
		
		LocalDate fechaInicio;
		LocalDate fechaFinal;
		
		if(fechaIni != null || fechaFin != null ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			fechaInicio = LocalDate.parse(fechaIni,formatter);
			fechaFinal = LocalDate.parse(fechaFin,formatter);
		} else {
			fechaFinal = LocalDate.now();
			fechaInicio = LocalDate.now().minusMonths(1);
		}

		List<Cliente> clientes = RepositorioClientes.getInstance().findAll();
		List<ReporteUser> listaReporteUser = new ArrayList<ReporteUser>();
		ReporteHogar  queryReporte = new ReporteHogar();
		
		clientes.forEach(c -> {
			listaReporteUser.add(new ReporteUser(	c.getNombre(),
													c.getApellido(),
													c.getNumeroDeDocumento(),
													c.getDomicilio(),
													queryReporte.consumoHogar(fechaInicio, fechaFinal, c )));
		});

		HashMap<String, Object> viewModel = new HashMap<>();HashMap model = new HashMap();
		viewModel.put("clientes", listaReporteUser);
		return viewModel;
	}

}
