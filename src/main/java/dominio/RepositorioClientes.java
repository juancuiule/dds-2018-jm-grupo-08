package dominio;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioClientes extends Repositorio<Cliente> implements WithGlobalEntityManager {
	public static RepositorioClientes instancia;
	
	public RepositorioClientes(String tableName) {
		super(tableName);
	}
	
	public static RepositorioClientes getInstance() {
		if(instancia == null) {
			instancia =  new RepositorioClientes("Cliente");
		}
		return instancia;
	}
}
