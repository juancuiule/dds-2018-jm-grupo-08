package dominio;

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
		
		//clientesConAhorroAutomatico.forEach(cliente -> new OptimizadorConsumo(cliente.getDispositivos(), cliente.getLimiteMensual()));		
		// Revisar:
		//    * Como se comporta el limite mensual
		//    * Tal vez haria falta una abstraccion de OPTIMIZACION
		
	}
	
}
