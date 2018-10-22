package dominio.transformadores;

import dominio.Cliente;
import dominio.Repositorio;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioTransformadores extends Repositorio<Transformador> {
	static RepositorioTransformadores instancia;
	
	public static RepositorioTransformadores getInstance() {
		if (instancia == null) {
			instancia = new RepositorioTransformadores("Transformador");
		}
		return instancia;
	}

	public RepositorioTransformadores(String tableName) {
		super(tableName);
	}
	
	public Stream<Transformador> tranformadoresActivos() {
		return this.filtrarSegun(transformador -> transformador.estaActivo());
	}
	
	public Transformador transformadorMasCercano(Cliente cliente) {
		return this.findAll().stream().min(new ComparadorDistancias(cliente)).get();
	}
	
	public void asignarTransformador(Cliente cliente) {
		transformadorMasCercano(cliente).conectarCliente(cliente);
	}

	public Stream<Transformador> filtrarSegun(Predicate<Transformador> unaCondicion) {
		// estaria bueno si podemos hacer algunos de estos filtros desde la db... y no en memoria trayendo todo
		return this.findAll().stream().filter(unaCondicion);
	}
}