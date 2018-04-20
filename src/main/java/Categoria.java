
public class Categoria {
	String identificador; // Hay que ver si tiene sentido definir este atributo (en el futuro)
	Integer limiteInferiorDeConsumo;
	Integer limiteSuperiorDeConsumo;
	Integer valorFijo;
	Integer valorVariable;
	
	public Categoria(String identificador, Integer limiteInferiorDeConsumo, Integer limiteSuperiorDeConsumo, Integer valorFijo, Integer valorVariable) {
		this.identificador = identificador;
		this.limiteInferiorDeConsumo = limiteInferiorDeConsumo;
		this.limiteSuperiorDeConsumo = limiteSuperiorDeConsumo;
		this.valorFijo = valorFijo;
		this.valorVariable = valorVariable;
	}
	
	Integer retornarValorFijo() {
		return this.valorFijo;
	}
	
	Integer retornarValorVariable() {
		return this.valorVariable;
	}
	
	Boolean correspondeCategoria(Integer consumo) {
		return (this.limiteInferiorDeConsumo < consumo) && (consumo < this.limiteSuperiorDeConsumo);
	}	
}
