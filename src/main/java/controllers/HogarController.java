package controllers;

import dominio.Cliente;

public class HogarController extends Controller<Cliente>{
    public HogarController(Cliente cliente, String template) {
        super(cliente, template);
    }

    @Override
    public Object generarModelo(Cliente cliente) {
        return cliente;
    }
}
