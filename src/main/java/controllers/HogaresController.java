package controllers;

import dominio.RepositorioClientes;

public class HogaresController extends Controller<RepositorioClientes> {
    public HogaresController(RepositorioClientes repositorioClientes, String template) {
        super(repositorioClientes, template);
    }

    @Override
    public Object generarModelo(RepositorioClientes repositorio) {
        return repositorio.findAll();
    }
}
