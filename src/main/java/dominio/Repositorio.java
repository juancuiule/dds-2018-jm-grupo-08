package dominio;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public abstract class Repositorio<T> implements WithGlobalEntityManager {
	public String tableName;

	public Repositorio(String tableName) {
		this.tableName = tableName;
	}

	public List<T> findAll() {
		return entityManager().createQuery("from ".concat(this.tableName)).getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findOne(String where) {
		return (T) entityManager()
				.createQuery("from " + this.tableName + " where ( " + where + " )").getSingleResult();
	}
	
	public void agregar(T elemento) {
		entityManager().persist(elemento);
	}
}
