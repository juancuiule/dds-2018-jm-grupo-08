package dominio.transformadores;

import dominio.Cliente;
import dominio.Repositorio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.Comparator;
import static dominio.transformadores.CalculadorDeDistancia.distance;

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
	
	public Transformador transformadorMasCercano(Cliente cliente) {
		return this.elementos.stream().min(new ComparadorDistancias(cliente)).get();
	}
	
	public void asignarTransformador(Cliente cliente) {
		transformadorMasCercano(cliente).conectarCliente(cliente);
	}
}