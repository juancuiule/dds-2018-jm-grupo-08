package server;

import controllers.LandingController;
import controllers.LoginController;
import controllers.AdminController;
import controllers.AdminReporteController;
import dominio.Usuario;
import spark.Request;
import spark.Response;

import java.util.Optional;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        get("/", LandingController::respond);
        get("/login", LoginController::respond);
        post("/login", LoginController::react);
        get("/admin", AdminController::respond); 
        get("/admin/reporte", AdminReporteController::respond);
        

        before("/user*",(req,res)->{
            haltIfNotAuthenticated(req);
            redirectHomeIfTrue(res,!obtenerUsuario(req).esCliente());
        });
        before("/admin*",(req,res)->{
            haltIfNotAuthenticated(req);
            redirectHomeIfTrue(res,!obtenerUsuario(req).esAdmin());
        });
    }

    private static void haltIfNotAuthenticated(Request req){
        if(!isAuthenticated(req)){
            halt(401, "Acceso restingido");
        }
    }

    private static Boolean isAuthenticated(Request req) {
        return Optional.ofNullable
                ((Boolean) req.session().attribute("auth"))
                .orElse(false);
    }

    private static void redirectHomeIfTrue(Response res, Boolean condition){
        if(condition) res.redirect("/");
    }


    private static Usuario obtenerUsuario(Request req){
        return req.session().attribute("user");
    }

}
