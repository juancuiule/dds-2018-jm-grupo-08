package dominio.transformadores;

import java.time.Period;
import java.util.List;
import dominio.Cliente;

public class Transformador {
	Punto punto;
	boolean activo;
	List<Cliente> clientesSuministrados;

	public Transformador(Punto punto, boolean activo) {
		this.punto = punto;
		this.activo = activo;
	}
	
	public Double energiaQueSuministra() {
		return 1.0;
	}

	public boolean estaActivo() {
		return activo;
	}
	
	public Punto getPunto() {
		return punto;
	}
	
	public void conectarCliente(Cliente cliente) {
		clientesSuministrados.add(cliente);
	}

	public Double consumo(Period periodo) {
		return clientesSuministrados.stream()
				.mapToDouble(cliente -> cliente.consumo(periodo)).sum();
	}
}