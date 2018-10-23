package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dominio.Cliente;
import dominio.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminReporteController {
	
	public  ModelAndView respond(Request req, Response res) {
        return new ModelAndView(generarDominio(req), "reportes.hbs");
    }
	
	
	private List<ReporteUser> generarDominio(Request req) {
		
		
		
		String fechaIni = req.queryParams("fechaInicio");
		String fechaFin = req.queryParams("fechaFin");
		
		if(fechaIni != null || fechaFin != null ) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate fechaInicio = LocalDate.parse(fechaIni,formatter);
			LocalDate fechaFinal = LocalDate.parse(fechaFin,formatter);
			
			} else {
			
			LocalDate fechaFinal = LocalDate.now();
			LocalDate fechaInicio = LocalDate.now().minusMonths(1);
			
			}
		
				   

		List<Cliente> clientes = RepositorioClientes.getInstance().findAll();
		List<ReporteUser> listaReporteUser = new ArrayList<ReporteUser>();
		ReporteHogar  queryReporte = new ReporteHogar();
		
		clientes.forEach(c -> {
		
			listaReporteUser.add(new ReporteUser(c.getNombre(),c.getApellido(),c.getNumeroDeDocumento(),c.getDomicilio(),queryReporte.consumoHogar(fechaInicio, fechaFinal, c ))	
		});
		
		return listaReporteUser;
	}

}
