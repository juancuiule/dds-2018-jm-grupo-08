package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class Controller<tipoDeDominio> {
    private tipoDeDominio objetoDeDominio;
    private String hbsTemplate;

    public Controller(tipoDeDominio objetoDeDominio, String template) {
        this.objetoDeDominio = objetoDeDominio;
        this.hbsTemplate = template;
    }

    public  ModelAndView showResultado(Request req, Response res) {
        return new ModelAndView(generarModelo(), hbsTemplate);
    }

    public abstract Object generarModelo();
}
