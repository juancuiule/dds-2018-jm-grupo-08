package server;

import controllers.*;
import dominio.Usuario;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Optional;

public class Router {
	public static void init() {
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		Spark.staticFiles.location("/public");

		Spark.get("/", LandingController::respond);
		Spark.get("/login", LoginController::respond, engine);
		Spark.post("/login", LoginController::processLogin);
		Spark.get("/roleSelection", RoleSelectionController::respond);

		Spark.before("/user/*", (req, res) -> {
			haltIfNotAuthenticated(req);
			redirectRootIfTrue(res, !obtenerUsuario(req).esCliente());
		});
		Spark.before("/admin/*", (req, res) -> {
			haltIfNotAuthenticated(req);
			redirectRootIfTrue(res, !obtenerUsuario(req).esAdmin());
		});

		Spark.redirect.get("/admin", "/admin/dashboard");
		Spark.get("/admin/dashboard", AdminReporteController::respond, engine);
		Spark.get("/admin/reporte-de-consumo", AdminReporteController::respond, engine);
		Spark.get("/admin/alta-de-dispositivo", AdminController::altaDispositivo, engine);
		Spark.post("/admin/alta-de-dispositivo", AdminController::darDeAlta);

		Spark.redirect.get("/user", "/user/dashboard");
		Spark.get("/user/dashboard", UserController::dashboard, engine);
		Spark.get("/user/estado-del-hogar", UserController::estadoDelHogar, engine);
		Spark.get("/user/consumos-por-periodo", UserController::consumosPorPeriodos, engine);
		Spark.get("/user/consumos-por-periodo/data", UserController::consumosParaPeriodoJson);
		Spark.get("/user/optimizaciones", OptimizacionController::respond, engine);
	}

	private static void haltIfNotAuthenticated(Request req) {
		if (!isAuthenticated(req)) {
			Spark.halt(401, "Acceso restingido");
		}
	}

	private static Boolean isAuthenticated(Request req) {
		return Optional.ofNullable((Boolean) req.session().attribute("auth")).orElse(false);
	}

	private static void redirectRootIfTrue(Response res, Boolean condition) {
		if (condition)
			res.redirect("/");
	}

	private static Usuario obtenerUsuario(Request req) {
		return req.session().attribute("user");
	}
}