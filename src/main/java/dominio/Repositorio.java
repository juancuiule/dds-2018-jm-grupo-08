package dominio;

import java.util.List;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public abstract class Repositorio<T> implements WithGlobalEntityManager {
	private String tableName;

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
                .createQuery(String.format("from %s where ( %s )", this.tableName, where)).getResultList();

        T result = null;
        if (!results.isEmpty()){
            result = results.get(0);
        }
        return Optional.ofNullable(result);
    }
	
	public void agregar(T elemento) {
		persistir(elemento);
	}
	public void persistir(T elemento) { entityManager().persist(elemento); }
}
