package dominio.dispositivoBase;

import dominio.Cliente;
import dominio.dispositivo.Dispositivo;

import java.util.Optional;

public class ClienteController {

	public void agregarUnDispositivoAunCliente(Cliente cliente, String nombre) {
		Optional<DispositivoBase> dispositivo = RepositorioDispositivoBase.getInstance().buscarDispositivo(nombre);
		
		Dispositivo dispositivoParaElCliente = dispositivo.get().devolverDisositivo();
		
		cliente.agregarDispositivo(dispositivoParaElCliente);
	}
}
