package controllers;

import dominio.Usuario;
import dominio.dispositivo.Dispositivo;
import simplex.Optimizacion;
import simplex.OptimizadorConsumo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class OptimizacionController {

    public static ModelAndView respond(Request req, Response res){
        Usuario usuario = req.session().attribute("user");
        List<Dispositivo> dispositivos = usuario.getRolCliente().getDispositivos();
        List<Optimizacion> optimizaciones = OptimizadorConsumo.optimizar(dispositivos);
        HashMap<String, Object> viewModel = new HashMap<>();HashMap model = new HashMap();
        viewModel.put("optimizaciones", optimizaciones);
        return new ModelAndView(viewModel, "optimizaciones.hbs");
    }
}
