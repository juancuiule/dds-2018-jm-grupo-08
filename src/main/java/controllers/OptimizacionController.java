package controllers;

import dominio.Cliente;
import dominio.Usuario;
import dominio.dispositivo.Dispositivo;
import simplex.Optimizacion;
import simplex.OptimizadorConsumo;
import spark.Request;
import spark.Response;

import java.util.List;

public class OptimizacionController {
    public static String respond(Request req, Response res){
        Usuario usuario = req.session().attribute("user");
        List<Dispositivo> dispositivos = usuario.getRolCliente().getDispositivos();
        List<Optimizacion> optimizaciones = OptimizadorConsumo.optimizar(dispositivos);
        return "Optimizaciones";
    }
}
