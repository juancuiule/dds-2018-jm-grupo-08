package dominio.dispositivo;

import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Fabrica")
public abstract class InterfazDeFabrica {
	
	@Id @GeneratedValue
	private Long id;

	abstract public Double consumoEnLasUltimasHoras();

	abstract public Double consumoEnElPeriodo(Double diasUltimoMes);

	abstract public Boolean estaEncendido();

	abstract public Boolean estaApagado();

	abstract public void apagar();

	abstract public  void encender();

	abstract public void ahorrarEnergia();

	abstract public Double consumoPorHora();
}
