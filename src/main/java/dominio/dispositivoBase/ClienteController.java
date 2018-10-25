package dominio.dispositivoBase;

import dominio.Cliente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFactory;

import java.util.Optional;

public class ClienteController {

	public void agregarUnDispositivoAunCliente(Cliente cliente, String nombre) {		
		DispositivoFactory dispositivoParaElCliente = new DispositivoFactory();
		cliente.agregarDispositivo(dispositivoParaElCliente.dispositivoFactory(nombre));
	}
}
