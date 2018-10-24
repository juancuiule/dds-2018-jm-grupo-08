package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominioTest.mocks.DispositivoFisicoMock;
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

class SubPeriodo {
	String fechas;
	Double consumo;
	Double consumoAcumulado;

	public SubPeriodo(String fechas, Double consumo, Double consumoAcumulado) {
		this.fechas = fechas;
		this.consumo = consumo;
		this.consumoAcumulado = consumoAcumulado;
	}

	public String getFechas() {
		return fechas;
	}

	public Double getConsumo() {
		return consumo;
	}

	public Double getConsumoAcumulado() {
		return consumoAcumulado;
	}
}

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
		return (Math.random() * (300 - 40 + 1)) + 40;
	}
}

public class UserController {

	static List<Mes> meses = new ArrayList<Mes>(
			Arrays.asList(new Mes(1, "Enero", false), new Mes(2, "Febrero", false), new Mes(3, "Marzo", false),
					new Mes(4, "Abril", false), new Mes(5, "Mayo", false), new Mes(6, "Junio", false),
					new Mes(7, "Julio", false), new Mes(8, "Agosto", false), new Mes(9, "Septiembre", false),
					new Mes(10, "Octubre", true), new Mes(11, "Noviembre", false), new Mes(12, "Diciembre", false)));

	static List<SubPeriodo> subperiodos = new ArrayList<SubPeriodo>(
			Arrays.asList(new SubPeriodo("1-5", 150.3, 150.3), new SubPeriodo("6-10", 200d, 350.3),
					new SubPeriodo("11-15", 15.3, 365.6), new SubPeriodo("16-20", 50.3, 415.9),
					new SubPeriodo("21-25", 114.1, 530d), new SubPeriodo("25-30", 100d, 630d)));

	static List<DispositivoDecorator> dispositivos = new ArrayList<DispositivoDecorator>(Arrays.asList(
			new DispositivoDecorator(
					new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0))),
			new DispositivoDecorator(new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0))),
			new DispositivoDecorator(
					new Dispositivo("Tostadora", new ComportamientoInteligente(new DispositivoFisicoMock())))));

	public static ModelAndView dashboard(Request req, Response res) {
		return estadoDelHogar(req, res);
	}

	public static ModelAndView estadoDelHogar(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		viewModel.put("dispositivos", dispositivos);
		// falta mostrar:
		// - ultimas mediciones (podría ser la tercer columna)
		// - consumo del ultimo periodo
		return new ModelAndView(viewModel, "user-dashboard.hbs");
	}

	public static List<SubPeriodo> consumosParaPeriodo(Mes mes) {
		System.out.println("Se pidio data para el mes: " + mes.getDescripcion());
		return subperiodos; // esto debería buscar la data en el reporte, acorde al mes o periodo gral
	}

	public static String consumosParaPeriodoJson(Request req, Response res) {
		Integer mes = Integer.parseInt(req.queryParams("mes"));
		Mes mesEnCuestion = meses.get(mes - 1);
		String subperiodosJson = new Gson().toJson(consumosParaPeriodo(mesEnCuestion));
		return subperiodosJson;
	}

	public static ModelAndView consumosPorPeriodos(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		viewModel.put("periodos", meses);
		// meses.get(10) tendría que ser el periodo acutal, y tendría que
		// coincidir con el que este con selected = true en la lista de periodos/meses
		viewModel.put("subperiodos", consumosParaPeriodo(meses.get(10)));
		return new ModelAndView(viewModel, "consumos-por-periodo.hbs");
	}

}
