package dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioCategorias extends Repositorio<Categoria> {
	static RepositorioCategorias instancia;

	public RepositorioCategorias() {
		this.elementos = new ArrayList<Categoria>(
				Arrays.asList(new Categoria("R1", 0, 150, 18.76, 0.644), new Categoria("R2", 150, 325, 35.32, 0.644),
						new Categoria("R3", 325, 400, 60.71, 0.681), new Categoria("R4", 400, 450, 71.74, 0.738),
						new Categoria("R5", 450, 500, 110.38, 0.794), new Categoria("R6", 500, 600, 220.75, 0.832),
						new Categoria("R7", 600, 700, 443.59, 0.851), new Categoria("R8", 700, 1400, 545.96, 0.851),
						new Categoria("R9", 1400, Integer.MAX_VALUE, 887.19, 0.851)));
	}

	public static RepositorioCategorias getInstance() {
		if (instancia == null) {
			return new RepositorioCategorias();
		}
		return instancia;
	}

	public Categoria categoriaCorrespondiente(Double consumo) {
		return this.filtrarSegun(categoria -> categoria.correspondeCategoria(consumo)).findFirst()
				.orElse(this.elementos.get(1));
	}
}