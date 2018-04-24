package dominio;

public class Categoria {
    private String categoria;
    private Integer limiteInferiorDeConsumo;
    private Integer limiteSuperiorDeConsumo;
    private Double valorFijo;
    private Double valorVariable;

    public Categoria(String categoria, Integer limiteInferiorDeConsumo, Integer limiteSuperiorDeConsumo, Double valorFijo, Double valorVariable) {
        this.categoria = categoria;
        this.limiteInferiorDeConsumo = limiteInferiorDeConsumo;
        this.limiteSuperiorDeConsumo = limiteSuperiorDeConsumo;
        this.valorFijo = valorFijo ;
        this.valorVariable  = valorVariable ;
    }

    double cargoFijo(Integer meses) {
        return this.valorFijo * meses;
    }

    double cargoVariable(Double kWh) {
        return this.valorVariable * kWh;
    }

    public boolean correspondeCategoria(Cliente cliente) {
        return (this.limiteInferiorDeConsumo < cliente.consumo()) &&
                (cliente.consumo() <= this.limiteSuperiorDeConsumo);
    }
}