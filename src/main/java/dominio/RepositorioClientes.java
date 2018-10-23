package dominio;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioClientes extends Repositorio<Cliente> implements WithGlobalEntityManager {
	private static RepositorioClientes instancia;
	
	private RepositorioClientes(String tableName) {
		super(tableName);
	}
	
	public static RepositorioClientes getInstance() {
		if(instancia == null) {
			instancia =  new RepositorioClientes("Cliente");
		}
		return instancia;
	}
}
