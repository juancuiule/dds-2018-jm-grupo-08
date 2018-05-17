package dominio;

import java.util.List;

public class Sensor {
    private List<Regla> subscriptores;

    public Sensor(List<Regla> subscriptores) {
        this.subscriptores = subscriptores;
    }
    
    public void agregarSubscripcion(Regla subscripcion){
        subscriptores.add(subscripcion);
    }
    
    public void publicar() {
        subscriptores.forEach(subscriptor -> subscriptor.ejecutarTareas());
    }

    public void removerSubscripcion(Regla subscripcion) {
        subscriptores.remove(subscripcion);
    }
}
