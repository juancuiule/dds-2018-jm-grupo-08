package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.Rango;
import dominio.dispositivoBase.DispositivoBase;
import dominio.dispositivoBase.EstandarDB;
import dominio.dispositivoBase.InteligenteDB;
import dominioTest.mocks.DispositivoFisicoMock;
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
		Double cotaSuperior = jsonObject.get("cotaSuperior").getAsDouble();
		Double cotaInferior = jsonObject.get("cotaInferior").getAsDouble();
		String interfaz;
		
		if (esInteligente) {
			interfaz = jsonObject.get("interfaz").getAsString();
		}
		
		
		//Validaciones
		
		//Se podría dividir en métodos más cortos
		
		if(nombre == null || esInteligente == null || consumoPorHora == null || cotaSuperior == null || cotaInferior == null  ) {
			
			return "{ \"message\": \"Error 400\" }";
		
		} else {
			
			Dispositivo dispositivo;
			
				if(esInteligente) {	
					
					DispositivoFisicoMock dispFisico =	new DispositivoFisicoMock();
					dispFisico.setNombre("interfaz");
					InteligenteDB intel = new InteligenteDB  (nombre,cotaSuperior,cotaInferior,consumoPorHora,dispFisico );
					dispositivo = intel.devolverDisositivo();
					
				} else {
					
					Double horasDeUso = jsonObject.get("horasDeUso").getAsDouble();
					EstandarDB estandar = new EstandarDB (nombre, cotaSuperior, cotaInferior, consumoPorHora, horasDeUso);
					dispositivo = estandar.devolverDisositivo();
					}
		
		EntityManager em = PerThreadEntityManagers.getEntityManager();		
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		em.persist(dispositivo);
		transaction.commit();	
			
			}
		
		
		
		
		return "{ \"message\": \"se creo un nuevo dispositivo\" }";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
