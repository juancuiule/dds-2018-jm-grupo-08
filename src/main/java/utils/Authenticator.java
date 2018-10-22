package utils;

import dominio.Usuario;
import dominio.repositorios.RepositorioUsuarios;

import java.util.Optional;

public class Authenticator {
    private RepositorioUsuarios repositorio;

    public Authenticator(RepositorioUsuarios repositorio) {
        this.repositorio = repositorio;
    }

    public Authenticator() {
        this.repositorio = RepositorioUsuarios.getInstance();
    }

    public Optional<Usuario> authenticateUser(String username, String password){
        return repositorio.findOneOptional("username = "+ username + " AND password = " + password );
    }
}
