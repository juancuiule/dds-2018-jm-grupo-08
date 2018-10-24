package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.Dispositivo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EstadoDelHogarController {
	public static ModelAndView respond(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>(Arrays.asList(
				new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0)),
				new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0))));
		viewModel.put("dispositivos", dispositivos);
		return new ModelAndView(viewModel, "user-dashboard.hbs");
	}
}
