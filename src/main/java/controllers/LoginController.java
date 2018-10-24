package controllers;

import dominio.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Authenticator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginController {
	private static Authenticator auth = new Authenticator();

	public static ModelAndView respond(Request req, Response res) {
		Boolean hayUsuario = req.session().attribute("auth");
		if(hayUsuario) {
			res.redirect("/roleSelection");			
		} else {
			return new ModelAndView(null, "login.hbs");			
		}
		return null;
	}

	public static String processLogin(Request req, Response res) {
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(req.body());

		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();

		System.out.println(jsonObject);

		Optional<Usuario> usuario = auth.authenticateUser(username, password);

		if (usuario.isPresent()) {
			req.session().attribute("auth", true);
			req.session().attribute("user", usuario.get());
			res.redirect("/roleSelection");
		} else {
			res.status(403);
		}
		return "";
	}
}
