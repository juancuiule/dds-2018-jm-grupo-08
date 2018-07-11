package dominio.transformadores;

import java.time.Period;
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

	public Double consumoDeZona(Period periodo) {
		return transformadoresDeLaZona().mapToDouble(transformador -> transformador.consumo(periodo)).sum();
	}
}