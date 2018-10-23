package dominio.dispositivo;

public class ConsumoJob {

	RepositorioConsumos repositorioConsumos = RepositorioConsumos.getInstance();
	
	public void ejecutar() {
		repositorioConsumos.persistirCosumos();
	}
}
