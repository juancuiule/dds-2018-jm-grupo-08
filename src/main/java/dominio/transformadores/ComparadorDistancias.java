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
		Double distancia1 = distance(transformador1.getPunto(), cliente.getPunto());
		Double distancia2 = distance(transformador2.getPunto(), cliente.getPunto());
		if (distancia1 < distancia2) {
			return -1;
		} else if (distancia1 > distancia2) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
