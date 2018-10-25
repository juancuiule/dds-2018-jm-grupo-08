package dominio;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.dispositivo.Dispositivo;

public class RepositorioDispositivos extends Repositorio<Dispositivo> implements WithGlobalEntityManager {
	public static RepositorioDispositivos instancia;

	public RepositorioDispositivos(String tableName) {
		super(tableName);
	}

	public static RepositorioDispositivos getInstance() {
		if (instancia == null) {
			instancia = new RepositorioDispositivos("Dispositivo");
		}
		return instancia;
	}
}
