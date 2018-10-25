package dominio.dispositivo;

import dominio.PersistentObject;

import java.time.Period;

import javax.persistence.*;

@Entity
@Table(name= "DispositivoFisico")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class DispositivoFisico extends PersistentObject {

	abstract public Double consumoEnLasUltimasHoras(Double horas);

	abstract public Double consumoEnElPeriodo(Double diasUltimoMes);

	abstract public Boolean estaEncendido();

	abstract public Boolean estaApagado();

	abstract public void apagar();

	abstract public  void encender();

	abstract public void ahorrarEnergia();

	abstract public Double consumoPorHora();
}
