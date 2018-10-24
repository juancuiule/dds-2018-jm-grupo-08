package utils;

import dominio.Administrador;
import dominio.Usuario;
import dominio.repositorios.RepositorioAdministradores;
import dominio.repositorios.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class LoadDB implements WithGlobalEntityManager {
    public static void main(String[] args){
        EntityManager em = PerThreadEntityManagers.getEntityManager();

        Administrador admin = new Administrador("Pepe", "Lolo", LocalDate.now(), 1);
        Usuario user = new Usuario(null,admin,"pepe", "1234");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
            em.persist(admin);
            em.persist(user);
        transaction.commit();
    }

}
