package controllers;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {
	
	public static ModelAndView respond(Request req, Response res) {
		
		Usuario user = req.session().attribute("user").getRolAdmin();
		return new ModelAndView(user,"indexAdmin.hbs");
				
	
		
	}

}
