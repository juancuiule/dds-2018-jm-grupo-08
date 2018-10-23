package server;

import controllers.LandingController;
import controllers.LoginController;
import controllers.AdminController;
import controllers.AdminReporteController;
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
            validateAuthenticationOrHalt(req);
            validateRoleOrRedirect(req,res,"user");
        });
        before("/admin*",(req,res)->{
            validateAuthenticationOrHalt(req);
            validateRoleOrRedirect(req,res,"admin");
        });
    }

    private static void validateAuthenticationOrHalt(Request req){
        Boolean isAuthenticated = Optional.ofNullable
                ((Boolean) req.session().attribute("auth"))
                .orElse(false);
        if(!isAuthenticated){
            halt(401, "Acceso restingido");
        }
    }

    private static void validateRoleOrRedirect(Request req, Response res, String expectedRole){
        String role = Optional.ofNullable((String) req.session().attribute("role"))
                .orElse("guest");
        if(!role.equals(expectedRole)){
            res.redirect("/");
        }
    }
}
