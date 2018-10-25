package controllers;

import dominio.Usuario;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class LandingController {

	public static String respond(Request req, Response res) {
		Optional<Usuario> user = Optional.ofNullable(req.session().attribute("user"));

		if (user.isPresent()) {
			if (user.get().esCliente()) {
				res.redirect("/user");
			} else if (user.get().esAdmin()) {
				res.redirect("/admin");
			}
		} else {
			res.redirect("/login");
		}
		return null;
	}

}
