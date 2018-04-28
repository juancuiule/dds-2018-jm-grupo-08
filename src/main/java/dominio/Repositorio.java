package dominio;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Repositorio<T> {
	public abstract List<T> elementos();
	public Stream<T> filtrarSegun(Predicate<T> unaCondicion){
		return this.elementos().stream().filter(unaCondicion);
	}

}
