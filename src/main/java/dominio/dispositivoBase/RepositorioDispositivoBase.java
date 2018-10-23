package dominio.dispositivoBase;

import dominio.Repositorio;
import java.util.Optional;

public class RepositorioDispositivoBase extends Repositorio<DispositivoBase> {
	private static RepositorioDispositivoBase instancia;

	private RepositorioDispositivoBase(String tableName) {
		super(tableName);
	}

	public static RepositorioDispositivoBase getInstance() {
		if(instancia == null) {
			instancia =  new RepositorioDispositivoBase("Consumo");
		}
		return instancia;
	}

	public Optional<DispositivoBase> buscarDispositivo(String nombre) {
		return findOneOptional("nombre = " + nombre);
	}
}