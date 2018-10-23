package dominio.dispositivoBase;

import javax.persistence.Query;

import dominio.Repositorio;

public class RepositorioDispositivoBase extends Repositorio<DispositivoBase>{

	static RepositorioDispositivoBase instancia;
	
	public RepositorioDispositivoBase(String tablename) {
//		super(tablename);
	}
	
	static RepositorioDispositivoBase getInstance() {
		if(instancia == null) {
			instancia = new RepositorioDispositivoBase("DispsitivoBase");
		}
		return instancia;
	}
	
	public void persistir(DispositivoBase dispositivo) {
		this.agregar(dispositivo);
	}
	
	public DispositivoBase buscarDispositivo(String nombre) {
		
//		String consulta = "from DispositivoBase where nombre = ?1";
//		Query query;
//		DispositivoBase dispositivo = entityManger().query.createNativeQuery(consulta).getSingleResult();
//		query.setParameter(1, nombre);
//		return dispositivo;
		
		return null;
	}
}
