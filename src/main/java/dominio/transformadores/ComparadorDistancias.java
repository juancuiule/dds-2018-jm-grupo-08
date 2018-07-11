package dominio.transformadores;

import java.util.Comparator;
import static dominio.transformadores.CalculadorDeDistancia.distance;

import dominio.Cliente;

public class ComparadorDistancias implements Comparator<Transformador>{
	private Cliente cliente;
	
	public ComparadorDistancias(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	@Override
	public int compare(Transformador transformador1, Transformador transformador2) {
		return (distance(transformador1.getPunto(), cliente.getPunto()) < distance(transformador2.getPunto(), cliente.getPunto()));
		return 0;
	}
	
}
