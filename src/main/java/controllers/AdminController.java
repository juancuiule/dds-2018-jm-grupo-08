package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dominio.ConfiguracionApp;
import dominio.dispositivo.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dominio.dispositivoBase.DispositivoBase;
import dominio.dispositivoBase.EstandarDB;
import dominio.dispositivoBase.InteligenteDB;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Optional;

public class AdminController {

	public static ModelAndView dashboard(Request req, Response res) {
		return reporteDeConsumo(req, res);
	}

	public static ModelAndView reporteDeConsumo(Request req, Response res) {
		// mandar la data del reporte al mostrar
		return new ModelAndView(null, "admin-dashboard.hbs");
	}

	public static ModelAndView altaDispositivo(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("interfaces", ConfiguracionApp.interfacesFisicas);
		return new ModelAndView(viewModel, "alta-de-dispositivo.hbs");
	}

	public static String darDeAlta(Request req, Response res) {
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(req.body());

		String nombre = jsonObject.get("nombre").getAsString();
		Boolean esInteligente = jsonObject.get("inteligente").getAsBoolean();
		Double consumoPorHora = jsonObject.get("consumoPorHora").getAsDouble();
		Double cotaSuperior = jsonObject.get("cotaSuperior").getAsDouble();
		Double cotaInferior = jsonObject.get("cotaInferior").getAsDouble();
		String interfaz = null;

		if (esInteligente) {
			interfaz = jsonObject.get("interfaz").getAsString();
		}

		// Validaciones

		// Se podría dividir en métodos más cortos

		if (nombre == null || esInteligente == null || consumoPorHora == null || cotaSuperior == null
				|| cotaInferior == null) {
			res.status(400);
			return "{ \"message\": \"hubo algun error en la data que se envio\" }";

		} else {

			DispositivoBase dispositivo;

			if (esInteligente) {
				DispositivoFisico dispositivoFisico = null;
				try {
					dispositivoFisico = (DispositivoFisico) Class.forName(interfaz).newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				dispositivoFisico = Optional.ofNullable(dispositivoFisico).orElse(new DispositivoFisicoGenerico());
				dispositivo = new InteligenteDB(nombre, cotaSuperior, cotaInferior, consumoPorHora, dispositivoFisico);
			} else {
				Double horasDeUso = jsonObject.get("horasDeUso").getAsDouble();
				dispositivo = new EstandarDB(nombre, cotaSuperior, cotaInferior, consumoPorHora, horasDeUso);
			}

			EntityManager em = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaction = em.getTransaction();

			transaction.begin();
			em.persist(dispositivo);
			transaction.commit();
			res.status(201);
			return "{ \"message\": \"se creo un nuevo dispositivo\" }";
		}
	}

}
