package utils;

import dominio.Usuario;
import dominio.repositorios.RepositorioUsuarios;

import java.util.Optional;

public class Authenticator {
    public static Optional<Usuario> authenticateUser(String username, String password){
        return RepositorioUsuarios.getInstance().findOneOptional("username ="+ username + "AND password =" + password );
    }
}
