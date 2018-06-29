package dominio;

import java.util.List;

public class JOB {
	
	RepositorioCliente repoClientes;
	
	
	public JOB (RepositorioCliente repoClientes) {
		
		this.repoClientes = repoClientes;
		
	}
	
	
	public void ejecutar() {
		
		List<Cliente> clientesConAhorroAutomatico = repoClientes.obtenerClientes().filtrarSegun(unCliente -> unCliente.gethorroAutomatico());
		
		clientesConAhorroAutomatico.forEach(cliente -> new OptimizadorConsumo(cliente.getDispositivos(), cliente.getLimiteMensual()));		
		
	}
	
}
