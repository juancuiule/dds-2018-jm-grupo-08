package dominio.dispositivo;

import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ComportamientoInteligente extends Comportamiento {
	
	@OneToOne
    private InterfazDeFabrica interfazDeFabrica;
 //   private Double consumoPorHora;

    public ComportamientoInteligente(InterfazDeFabrica interfazDeFabrica) {
        this.interfazDeFabrica = interfazDeFabrica;
    }
    
    public ComportamientoInteligente(InterfazDeFabrica interfazDeFabrica, Double consumoPorHora) {
        super();
        this.interfazDeFabrica = interfazDeFabrica;
        this.consumoPorHora = consumoPorHora;
    }

   

    @Override
    public Boolean estaEncendido() {
        return interfazDeFabrica.estaEncendido();
    }

    @Override
    public Boolean estaApagado() {
        return interfazDeFabrica.estaApagado();
    }

    @Override
    public Double consumoEnUltimasHoras(Double horas) {
        return interfazDeFabrica.consumoEnLasUltimasHoras();
    }

    @Override
    public Double consumoEnElPeriodo(Period periodo) {
        return interfazDeFabrica.consumoEnElPeriodo(periodo);
    }

    @Override
    public void apagar() {
        interfazDeFabrica.apagar();
    }

    @Override
    public void encender() {
        interfazDeFabrica.encender();
    }

    @Override
    public void ahorrarEnergia() {
        interfazDeFabrica.ahorrarEnergia();
    }

	@Override
	public void restringirConsumo() {
		// TODO Auto-generated method stub
		
	}
}
