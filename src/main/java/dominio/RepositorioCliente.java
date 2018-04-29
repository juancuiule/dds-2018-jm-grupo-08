package dominio;

import java.util.ArrayList;
import java.util.List;


public class RepositorioCliente extends Repositorio<Cliente>{
	public static RepositorioCliente instancia;
	private List<Cliente> elementos = new ArrayList<Cliente>();
	public static RepositorioCliente getInstance() {
		if(instancia == null) {
			return new RepositorioCliente();
		}
		return instancia;
	}
	
}
