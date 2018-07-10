package dominio.transformadores;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Zona {
	Punto punto;
	double radioQueCubre;

	public Zona(Punto punto, double radioQueCubre) {
		this.punto = punto;
		this.radioQueCubre = radioQueCubre;
	}

	public Boolean estaDentroDelRadio(Transformador transformador) {
		return CalculadorDeDistancia.distance(transformador.getPunto(), this.punto) <= this.radioQueCubre;
	}

	public Stream<Transformador> transformadoresDeLaZona() {
		return RepositorioTransformadores.getInstance()
				.filtrarSegun(transformador -> estaDentroDelRadio(transformador));
	}

	public Double consumoDeZona() {
		// Buscar transormadores dentro del radio
		return 1.0;
	}
}