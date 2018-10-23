package dominio.dispositivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.Cliente;
import dominio.Repositorio;
import dominio.RepositorioClientes;


//public class RepositorioConsumos extends Repositorio<Consumo> {
//
//	private List<Cliente> clientes = new ArrayList<Cliente>();
//	public static RepositorioConsumos instancia;
//
//	public RepositorioConsumos() {
//		clientes = RepositorioClientes.getInstance().obtenerClientes();
//
//	}
//
//	public static RepositorioConsumos getInstance() {
//		if(instancia == null) {
//			instancia =  new RepositorioConsumos();
//		}
//		return instancia;
//	}
//
//
//	public void persistirCosumos() {
//		clientes.forEach(cliente ->{this.consumoPorCliente(cliente);});
//	}
//
//	private void consumoPorCliente(Cliente cliente) {
//		cliente.getDispositivos().forEach(dispositivo -> {this.agregar(this.consumoFactory(cliente,dispositivo));});
//
//	}
//
//	private Consumo consumoFactory(Cliente cliente, Dispositivo dispositivo) {
//		return new Consumo(cliente, dispositivo, LocalDate.now().minusDays(3),LocalDate.now());
//	}
//}


public class RepositorioConsumos extends Repositorio<Consumo> {
	public static RepositorioConsumos instancia;

	public RepositorioConsumos(String tableName) {
		super(tableName);
	}

	public static RepositorioConsumos getInstance() {
		if(instancia == null) {
			instancia =  new RepositorioConsumos("Consumo");
		}
		return instancia;
	}
}
