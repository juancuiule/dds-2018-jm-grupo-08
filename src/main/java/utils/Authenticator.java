package utils;

import dominio.Usuario;
import dominio.repositorios.RepositorioUsuarios;
import org.mindrot.jbcrypt.BCrypt;

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
        Optional<Usuario> usuario = repositorio.findOneOptional("username = '"+ username + "'");
        if(!usuario.isPresent())                                return Optional.empty();
        if(verifyHash(password, usuario.get().getPassword()))   return usuario;
                                                                return Optional.empty();
    }

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());

    }

    public Boolean verifyHash(String candidate, String hash) {
        return BCrypt.checkpw(candidate, hash);
    }
}
