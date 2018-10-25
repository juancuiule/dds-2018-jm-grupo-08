package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import dominio.Cliente;
import dominio.Usuario;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.reporte.Mes;
import dominio.reporte.ReporteHogar;
import dominio.reporte.SubPeriodo;
import dominioTest.mocks.DispositivoFisicoMock;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

class DispositivoDecorator {
	Dispositivo dispositivo;

	public DispositivoDecorator(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getEstado() {
		if (dispositivo.estaApagado()) {
			return "Apagado";
		} else if (dispositivo.estaEncendido()) {
			return "Encendido";
		} else {
			return "Sin Informacion";
		}
		// no quedo muy bien desde el modelo, pero este es el caso donde el
		// dispositivo no es inteligente y no se tiene data
	}

	public String getNombre() {
		return dispositivo.getNombre();
	}

	public String getStyle() {
		if (dispositivo.estaApagado()) {
			return "off";
		} else if (dispositivo.estaEncendido()) {
			return "on";
		} else {
			return "no-data";
		}
	}

	public Double getConsumo() {
		Integer days = LocalDate.now().minusMonths(1).lengthOfMonth();
		return dispositivo.consumoEnElPeriodo(Double.parseDouble(days.toString()));
	}
}

public class UserController {

	static List<Mes> meses = new ArrayList<Mes>(Arrays.asList(new Mes(1, "Enero"), new Mes(2, "Febrero"),
			new Mes(3, "Marzo"), new Mes(4, "Abril"), new Mes(5, "Mayo"), new Mes(6, "Junio"), new Mes(7, "Julio"),
			new Mes(8, "Agosto"), new Mes(9, "Septiembre"), new Mes(10, "Octubre"), new Mes(11, "Noviembre"),
			new Mes(12, "Diciembre")));

//	static List<DispositivoDecorator> dispositivos = new ArrayList<DispositivoDecorator>(Arrays.asList(
//			new DispositivoDecorator(
//					new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0))),
//			new DispositivoDecorator(new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0))),
//			new DispositivoDecorator(
//					new Dispositivo("Tostadora", new ComportamientoInteligente(new DispositivoFisicoMock())))));

	public static ModelAndView dashboard(Request req, Response res) {
		return estadoDelHogar(req, res);
	}

	public static ModelAndView estadoDelHogar(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		List<DispositivoDecorator> dispositivos = getCliente(req).getDispositivos().stream()
				.map(disp -> new DispositivoDecorator(disp)).collect(Collectors.toList());
		viewModel.put("dispositivos", dispositivos);
		return new ModelAndView(viewModel, "user-dashboard.hbs");
	}

	public static List<SubPeriodo> consumosParaPeriodo(Mes mes, Cliente cliente) {
		System.out.println("Se pidio data para el mes: " + mes.getDescripcion());
		return new ReporteHogar().subperiodosParaMes(mes, cliente);
	}

	public static Cliente getCliente(Request req) {
		Usuario user = req.session().attribute("user");
		return user.getRolCliente();
	}

	public static String consumosParaPeriodoJson(Request req, Response res) {
		Integer mes = Integer.parseInt(req.queryParams("mes"));
		Mes mesEnCuestion = meses.get(mes - 1);
		String subperiodosJson = new Gson().toJson(consumosParaPeriodo(mesEnCuestion, getCliente(req)));
		return subperiodosJson;
	}

	public static ModelAndView consumosPorPeriodos(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		viewModel.put("periodos", meses);
		Integer month = LocalDate.now().getMonthValue();
		viewModel.put("subperiodos", consumosParaPeriodo(meses.get(month - 1), getCliente(req)));
		return new ModelAndView(viewModel, "consumos-por-periodo.hbs");
	}

	public static ModelAndView optimizaciones(Request req, Response res) {
		return new ModelAndView(null, "optimizaciones.hbs");
	}

}
