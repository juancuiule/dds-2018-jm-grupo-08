package dominio;

import java.util.List;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public abstract class Repositorio<T> implements WithGlobalEntityManager {
	public String tableName;

	public Repositorio(String tableName) {
		this.tableName = tableName;
	}

	public List<T> findAll() {
		return entityManager().createQuery("from ".concat(this.tableName)).getResultList();
	}

    public List<T> findAllWhere(String where) {
        return entityManager().createQuery("from " + this.tableName + " where ( " + where + " )").getResultList();
    }

	@SuppressWarnings("unchecked")
	public T findOne(String where) {
		return (T) entityManager()
				.createQuery("from " + this.tableName + " where ( " + where + " )").getSingleResult();
	}

    public Optional<T> findOneOptional(String where) {
        List<T> results = entityManager()
                .createQuery("from " + this.tableName + " where ( " + where + " )").getResultList();
        T result;
        if (results.isEmpty()){
            result = null;
        }else{
            result = results.get(0);
        }
        return Optional.ofNullable(result);
    }
	
	public void agregar(T elemento) {
		entityManager().persist(elemento);
	}

	public void persistir(T elemento) { agregar(elemento); }
}
