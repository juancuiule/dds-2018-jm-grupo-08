package dominio.transformadores;

import dominio.Cliente;
// import dominio.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioTransformadores {
	static RepositorioTransformadores instancia;
	private List<Transformador> elementos;

	/* esto venia de Repositorio... lo dejo acá para que pasen los tests,
	 * despues lo ajusto al nuevo comportamiento de los demas repos
	 */
	public List<Transformador> elementos() {
		return this.elementos;
	}

	public Stream<Transformador> filtrarSegun(Predicate<Transformador> unaCondicion) {
		return this.elementos().stream().filter(unaCondicion);
	}
	
	public void agregar(Transformador elemento) {
		this.elementos.add(elemento);
	}
	/**/
	
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
	
	public Transformador transformadorMasCercano(Cliente cliente) {
		return this.elementos.stream().min(new ComparadorDistancias(cliente)).get();
	}
	
	public void asignarTransformador(Cliente cliente) {
		transformadorMasCercano(cliente).conectarCliente(cliente);
	}
}