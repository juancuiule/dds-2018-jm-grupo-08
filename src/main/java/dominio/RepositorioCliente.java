package dominio;

import java.util.List;

public class RepositorioCliente extends Repositorio<Cliente> {
	public static RepositorioCliente instancia;
	
	public RepositorioCliente() {
		this.elementos = new List<Cliente>;
	}
	
	public static RepositorioCliente getInstance() {
		if(instancia == null) {
			return new RepositorioCliente();
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
