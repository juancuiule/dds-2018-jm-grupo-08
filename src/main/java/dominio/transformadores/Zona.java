package dominio.transformadores;

import dominio.PersistentObject;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.stream.Stream;
@Entity
@Table(name = "Zona")
public class Zona extends PersistentObject {
    // Atributes
    @Embedded
    Punto punto;
    double radioQueCubre;

    // Constructors
    public Zona() {
    }

    public Zona(Punto punto, double radioQueCubre) {
        this.punto = punto;
        this.radioQueCubre = radioQueCubre;
    }

    // Accessors
    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public double getRadioQueCubre() {
        return radioQueCubre;
    }

    public void setRadioQueCubre(double radioQueCubre) {
        this.radioQueCubre = radioQueCubre;
    }

    // Public methods
    public Boolean estaDentroDelRadio(Transformador transformador) {
        return CalculadorDeDistancia.distance(transformador.getPunto(), this.punto) <= this.radioQueCubre;
    }

    public Stream<Transformador> transformadoresDeLaZona() {
        return RepositorioTransformadores.getInstance()
                .filtrarSegun(transformador -> estaDentroDelRadio(transformador));
    }

    public Double consumoDeZona(Double diasUltimoMes) {
        return transformadoresDeLaZona()
                .filter(transformador -> transformador.estaActivo())
                .mapToDouble(transformador -> transformador.consumo(diasUltimoMes))
                .sum();
    }
}