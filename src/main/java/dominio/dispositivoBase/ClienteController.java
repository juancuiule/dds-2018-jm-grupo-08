package dominio.dispositivoBase;

import dominio.Cliente;
import dominio.dispositivo.Dispositivo;

public class ClienteController {

	public void agregarUnDispositivoAunCliente(Cliente cliente, String nombre) {
		DispositivoBase  dispositivo = RepositorioDispositivoBase.getInstance().buscarDispositivo(nombre);
		
		Dispositivo dispositivoParaElCliente = dispositivo.devolverDisositivo();
		
		cliente.agregarDispositivo(dispositivoParaElCliente);
	}
}
