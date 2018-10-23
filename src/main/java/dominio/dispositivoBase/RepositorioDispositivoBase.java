package dominio.dispositivoBase;

import javax.persistence.Query;

import dominio.Repositorio;
import dominio.dispositivo.Consumo;

import java.util.Optional;

//public class RepositorioDispositivoBase extends Repositorio<DispositivoBase>{
//
//	static RepositorioDispositivoBase instancia;
//
//	public RepositorioDispositivoBase(String tablename) {
////		super(tablename);
//	}
//
//	static RepositorioDispositivoBase getInstance() {
//		if(instancia == null) {
//			instancia = new RepositorioDispositivoBase("DispsitivoBase");
//		}
//		return instancia;
//	}
//
//	public void persistir(DispositivoBase dispositivo) {
//		this.agregar(dispositivo);
//	}
//
//	public DispositivoBase buscarDispositivo(String nombre) {
//
////		String consulta = "from DispositivoBase where nombre = ?1";
////		Query query;
////		DispositivoBase dispositivo = entityManger().query.createNativeQuery(consulta).getSingleResult();
////		query.setParameter(1, nombre);
////		return dispositivo;
//
//		return null;
//	}
//}

public class RepositorioDispositivoBase extends Repositorio<DispositivoBase> {
	public static RepositorioDispositivoBase instancia;

	public RepositorioDispositivoBase(String tableName) {
		super(tableName);
	}

	public static RepositorioDispositivoBase getInstance() {
		if(instancia == null) {
			instancia =  new RepositorioDispositivoBase("Consumo");
		}
		return instancia;
	}

	public Optional<DispositivoBase> buscarDispositivo(String nombre) {
		return findOneOptional("nombre = " + nombre);
	}
}