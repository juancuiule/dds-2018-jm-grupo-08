package dominio.jobs;

import dominio.Cliente;
import dominio.RepositorioClientes;
import simplex.Optimizacion;
import simplex.OptimizadorConsumo;

import java.util.List;
import java.util.stream.Collectors;

public class OptimizarConsumoJob {
	
	RepositorioClientes repoClientes;
	
	public OptimizarConsumoJob () {
		this.repoClientes = RepositorioClientes.getInstance();
	}
	
	
	public void ejecutar() {
		List<Cliente> clientesConAhorroAutomatico = repoClientes
		                                           .filtrarSegun(unCliente -> unCliente.getAhorroAutomatico())
		                                           .collect(Collectors.toList());
		
		clientesConAhorroAutomatico.forEach(cliente -> {
			List<Optimizacion> optimizaciones = OptimizadorConsumo.optimizar(cliente.dispositivos());
			optimizaciones.forEach(optimizacion -> optimizacion.restringirConsumo());
		});	
		
	}
	
}
