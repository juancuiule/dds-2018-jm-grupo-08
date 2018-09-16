package dominio.dispositivo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ComportamientoInteligente extends Comportamiento {
	@OneToOne(cascade = CascadeType.PERSIST)
    private DispositivoFisico dispositivoFisico;
    //private Double consumoPorHora;


    public ComportamientoInteligente() {
    }

    public ComportamientoInteligente(DispositivoFisico dispositivoFisico) {
        this.dispositivoFisico = dispositivoFisico;
    }
    
    public ComportamientoInteligente(DispositivoFisico dispositivoFisico, Double consumoPorHora) {
        super();
        this.dispositivoFisico = dispositivoFisico;
        this.consumoPorHora = consumoPorHora;
    }

//	public Double consumoPorHora() {
//		return consumoPorHora;
//	}

    @Override
    public Boolean estaEncendido() {
        return dispositivoFisico.estaEncendido();
    }

    @Override
    public Boolean estaApagado() {
        return dispositivoFisico.estaApagado();
    }

    @Override
    public Double consumoEnElPeriodo(Double diasUltimoMes) {
        return dispositivoFisico.consumoEnElPeriodo(diasUltimoMes);
    }
  
    @Override
    public Double consumoEnUltimasHoras(Double horas) {
      return dispositivoFisico.consumoEnLasUltimasHoras(horas);
    }

    @Override
    public void apagar() {
        dispositivoFisico.apagar();
    }

    @Override
    public void encender() {
        dispositivoFisico.encender();
    }

    @Override
    public void ahorrarEnergia() {
        dispositivoFisico.ahorrarEnergia();
    }

	@Override
	public void restringirConsumo() {
		// TODO Auto-generated method stub
		
	}
}
