package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {

	public static ModelAndView dashboard(Request req, Response res) {
		return reporteDeConsumo(req, res);
	}

	public static ModelAndView reporteDeConsumo(Request req, Response res) {
		// mandar la data del reporte al mostrar
		return new ModelAndView(null, "admin-dashboard.hbs");
	}
	
	public static ModelAndView altaDispositivo(Request req, Response res) {
		// mandar las interfaces disponibles
		return new ModelAndView(null, "alta-de-dispositivo.hbs");
	}

	public static String darDeAlta(Request req, Response res) {
		System.out.println(req.body());
		// todo: dar de alta el dispositivo
		return "{ \"message\": \"se creo un nuevo dispositivo\" }";
	}
}
