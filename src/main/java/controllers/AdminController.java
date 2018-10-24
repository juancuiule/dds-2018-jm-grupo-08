package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {

	public static ModelAndView respond(Request req, Response res) {
		return new ModelAndView(null, "admin-dashboard.hbs");
	}
	
	public static void redirectToDashboard(Request req, Response res) {
		res.redirect("/admin/dashboard");
	}
}
