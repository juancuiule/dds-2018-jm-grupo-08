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
		Boolean hayUsuario = Optional.ofNullable((Boolean) req.session().attribute("auth")).orElse(false);
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

		Optional<Usuario> usuario = auth.authenticateUser(username, password);
		if (usuario.isPresent()) {
			req.session().attribute("auth", true);
			req.session().attribute("user", usuario.get());
			// res.redirect("/roleSelection");
			// estaría mal acá hace un res.redirect("/roleSelection") ?
			// y que si bien el redirect no se haga, le llegue al front la ruta de /roleSelection
			
			// ahora esta funcionando porque el front termina haciendo un redirect a /login
			// y cuando entra a login se da cuenta de que ya hay usuario en la session y lo manda
			// a /roleSelection
			res.status(200);
		} else {
			res.status(403);
		}
		return "";
	}
}
