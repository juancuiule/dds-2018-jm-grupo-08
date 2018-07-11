package dominio;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Repositorio<T> {
	public List<T> elementos;

	public List<T> elementos() {
		return this.elementos;
	}

	public Stream<T> filtrarSegun(Predicate<T> unaCondicion) {
		return this.elementos().stream().filter(unaCondicion);
	}
	
	public void agregar(T elemento) {
		this.elementos.add(elemento);
	}
}
