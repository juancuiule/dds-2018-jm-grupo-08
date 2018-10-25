package dominio.repositorios;

import dominio.Administrador;
import dominio.Repositorio;
import dominio.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;


public class RepositorioAdministradores extends Repositorio<Administrador> implements WithGlobalEntityManager {
    public static RepositorioAdministradores instancia;

    public RepositorioAdministradores(String tableName) {
        super(tableName);
    }

    public static RepositorioAdministradores getInstance() {
        if(instancia == null) {
            instancia =  new RepositorioAdministradores("Administrador");
        }
        return instancia;
    }
}
