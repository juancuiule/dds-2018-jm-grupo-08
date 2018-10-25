package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(req.body());

		String nombre = jsonObject.get("nombre").getAsString();
		Boolean esInteligente = jsonObject.get("inteligente").getAsBoolean();
		Double consumoPorHora = jsonObject.get("consumoPorHora").getAsDouble();
		String interfaz;
		if (esInteligente) {
			interfaz = jsonObject.get("interfaz").getAsString();
		}

		// todo: dar de alta el dispositivo
		return "{ \"message\": \"se creo un nuevo dispositivo\" }";
	}
}
