package controllers;

import dominio.Usuario;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class RoleSelectionController {
    public static String react(Request req, Response res) {
        Usuario usuario = req.session().attribute("user");
        if(usuario.esAdmin() && usuario.esCliente()){
            return "Seleccionar rol";
        }else if (usuario.esCliente()){
            res.redirect("/user");
        }else if (usuario.esAdmin()){
            res.redirect("/admin");
        }
        return null;
    }
}
