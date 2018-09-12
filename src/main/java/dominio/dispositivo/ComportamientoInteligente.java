package dominio.dispositivo;

import java.time.Period;

public class ComportamientoInteligente implements Comportamiento {
	private InterfazDeFabrica interfazDeFabrica;
	private Double consumoPorHora;

	public ComportamientoInteligente(InterfazDeFabrica interfazDeFabrica) {
		this.interfazDeFabrica = interfazDeFabrica;
	}

	public ComportamientoInteligente(InterfazDeFabrica interfazDeFabrica, Double consumoPorHora) {
		super();
		this.interfazDeFabrica = interfazDeFabrica;
		this.consumoPorHora = consumoPorHora;
	}

	public Double consumoPorHora() {
		return consumoPorHora;
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
		return interfazDeFabrica.consumoEnLasUltimasHoras(horas);
	}

	@Override
	public Double consumoEnElPeriodo(Double diasUltimoMes) {
		return interfazDeFabrica.consumoEnElPeriodo(diasUltimoMes);
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
