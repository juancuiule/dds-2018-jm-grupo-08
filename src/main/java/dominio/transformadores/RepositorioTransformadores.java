package dominio.transformadores;

import dominio.Repositorio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class RepositorioTransformadores extends Repositorio<Transformador> {
	static RepositorioTransformadores instancia;

	public static RepositorioTransformadores getInstance() {
		if (instancia == null) {
			instancia = new RepositorioTransformadores();
		}
		return instancia;
	}

	public RepositorioTransformadores() {
		this.elementos = new ArrayList<Transformador>();
	}
	
	public Stream<Transformador> tranformadoresActivos() {
		return this.filtrarSegun(transformador -> transformador.estaActivo());
	}
}