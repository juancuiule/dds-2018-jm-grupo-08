package controllers;


import dominio.Administrador;
import dominio.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {
	
	public static ModelAndView respond(Request req, Response res) {
		
		Administrador user = ((Usuario) req.session().attribute("user")).getRolAdmin();
		return new ModelAndView(user,"indexAdmin.hbs");
				
	
		
	}

}
