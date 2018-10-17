package controllers;

import spark.Request;
import spark.Response;

public class LoginController {
    public static String respond(Request req, Response res) {
        return "Login page";
    }

    public static String react(Request req, Response res) {
        String username = req.params("username");
        String password = req.params("password");
        if(authenticate(username,password)){
            req.session().attribute("auth",true);
            res.redirect("/roleSelection");
        }else{
            return "Usuario y/o contraseña invalidos";
        }
        return null;
    }

    private static Boolean authenticate(String username, String password) {
        return true;
    }

}
