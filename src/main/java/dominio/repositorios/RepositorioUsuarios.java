package dominio.repositorios;

import dominio.Repositorio;
import dominio.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;


public class RepositorioUsuarios extends Repositorio<Usuario> implements WithGlobalEntityManager {
    public static RepositorioUsuarios instancia;

    public RepositorioUsuarios(String tableName) {
        super(tableName);
    }

    public static RepositorioUsuarios getInstance() {
        if(instancia == null) {
            instancia =  new RepositorioUsuarios("Usuario");
        }
        return instancia;
    }
}
