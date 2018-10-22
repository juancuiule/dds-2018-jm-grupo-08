package controllers;

import dominio.Usuario;
import spark.Request;
import spark.Response;

import java.util.Optional;

import static utils.Authenticator.*;

public class LoginController {
    public static String respond(Request req, Response res) {
        return "Login page";
    }

    public static String react(Request req, Response res) {
        String username = req.params("username");
        String password = req.params("password");
        Optional<Usuario> usuario = authenticateUser(username, password);

        if(usuario.isPresent()){
            req.session().attribute("auth",true);
            req.session().attribute("user",usuario.get());
            res.redirect("/roleSelection");
        }else{
            return "Usuario y/o contraseña invalidos";
        }
        return null;
    }
}
