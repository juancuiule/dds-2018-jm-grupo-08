package dominio.dispositivo;

import java.time.Period;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Comportamiento")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="Tipo")
public abstract class Comportamiento {

	@Id @GeneratedValue
	private Long id;

	public Double consumoPorHora;

	public Double consumoPorHora() {
	    	return consumoPorHora;
	}

    abstract Boolean estaEncendido();
    abstract Boolean estaApagado();
    abstract Double consumoEnUltimasHoras(Double horas);
    abstract Double consumoEnElPeriodo(Double diasUltimoMes);
    abstract void apagar();
    abstract void encender();
    abstract void ahorrarEnergia();
	abstract void restringirConsumo();
}
