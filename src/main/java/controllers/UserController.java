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

class Mes {
	Integer value;
	String descripcion;
	Boolean selected;

	public Integer getValue() {
		return value;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean getSelected() {
		return selected;
	}

	public Mes(Integer value, String descripcion, Boolean selected) {
		this.value = value;
		this.descripcion = descripcion;
		this.selected = selected;
	}
}

public class UserController {

	static List<Mes> meses = new ArrayList<Mes>(
			Arrays.asList(new Mes(1, "Enero", false), new Mes(2, "Febrero", false), new Mes(3, "Marzo", false),
					new Mes(4, "Abril", false), new Mes(5, "Mayo", false), new Mes(6, "Junio", false),
					new Mes(7, "Julio", false), new Mes(8, "Agosto", false), new Mes(9, "Septiembre", false),
					new Mes(10, "Octubre", true), new Mes(11, "Noviembre", false), new Mes(12, "Diciembre", false)));

	public static ModelAndView dashboard(Request req, Response res) {
		return estadoDelHogar(req, res);
	}

	public static ModelAndView estadoDelHogar(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>(Arrays.asList(
				new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0)),
				new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0))));
		viewModel.put("dispositivos", dispositivos);
		return new ModelAndView(viewModel, "user-dashboard.hbs");
	}

	public static ModelAndView consumosPorPeriodos(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		viewModel.put("periodos", meses);
		return new ModelAndView(viewModel, "consumos-por-periodo.hbs");
	}

}
