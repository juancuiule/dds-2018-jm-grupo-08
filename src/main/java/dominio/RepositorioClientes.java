package dominio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioClientes extends Repositorio<Cliente> {
	public static RepositorioClientes instancia;
	
	public RepositorioClientes() {
		this.elementos = new ArrayList<Cliente>();
	}
	
	public static RepositorioClientes getInstance() {
		if(instancia == null) {
			instancia =  new RepositorioClientes();
		}
		return instancia;
	}
	
	public void agregarCliente(Cliente unCliente) {
		this.elementos.add(unCliente);
	}
	
	public List<Cliente> obtenerClientes() {
		return this.elementos;
	}
}
