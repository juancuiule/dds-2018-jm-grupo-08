package controllers;

import dominio.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.Authenticator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginController {
    private static Authenticator auth = new Authenticator();

    public static ModelAndView respond(Request req, Response res) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"login.hbs");
    }

    public static String react(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        System.out.println(req.body());
        Optional<Usuario> usuario = auth.authenticateUser(username, password);

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
