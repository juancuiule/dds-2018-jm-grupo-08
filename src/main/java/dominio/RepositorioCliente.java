package dominio;

public class RepositorioCliente extends Repositorio<Cliente> {
	public static RepositorioCliente instancia;
	public static RepositorioCliente getInstance() {
		if(instancia == null) {
			return new RepositorioCliente();
		}
		return instancia;
	}
}
