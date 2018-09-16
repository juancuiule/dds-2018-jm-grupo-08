package dominio;

import javax.persistence.NoResultException;

public class RepositorioCategorias extends Repositorio<Categoria> {
	static RepositorioCategorias instancia;

	public RepositorioCategorias(String tableName) {
		super(tableName);
	}

	public static RepositorioCategorias getInstance() {
		if (instancia == null) {
			instancia = new RepositorioCategorias("Categoria");
		}
		return instancia;
	}

	public Categoria categoriaCorrespondiente(Double consumo) {
		try {
			Categoria categoria = this.findOne("limiteInferiorDeConsumo < " + consumo.toString() + " and "
					+ consumo.toString() + " <= limiteSuperiorDeConsumo");
			return categoria;
		} catch (NoResultException e) {
			return new Categoria("R1", 0, 150, 18.76, 0.644);
		}
		
	}
}