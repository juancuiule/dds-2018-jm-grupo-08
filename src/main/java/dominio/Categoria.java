package dominio;

public class Categoria {
	private String categoria;
	private Integer limiteInferiorDeConsumo;
	private Integer limiteSuperiorDeConsumo;
	private Double valorFijo;
	private Double valorVariable;

	public Categoria(String categoria, Integer limiteInferiorDeConsumo, Integer limiteSuperiorDeConsumo,
			Double valorFijo, Double valorVariable) {
		this.categoria = categoria;
		this.limiteInferiorDeConsumo = limiteInferiorDeConsumo;
		this.limiteSuperiorDeConsumo = limiteSuperiorDeConsumo;
		this.valorFijo = valorFijo;
		this.valorVariable = valorVariable;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public double cargoFijo(Integer meses) {
		return this.valorFijo * meses;
	}

	public double cargoVariable(Double kWh) {
		return this.valorVariable * kWh;
	}

	public boolean correspondeCategoria(Double consumo) {
		return (this.limiteInferiorDeConsumo < consumo) && (consumo <= this.limiteSuperiorDeConsumo);
	}
}