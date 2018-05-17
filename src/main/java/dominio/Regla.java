package dominio;

import java.util.List;

public class Regla {
    private Sensor sensor;
    private List<Actuador> tareas;
    
    public Regla(Sensor sensor, List<Actuador> tareas) {
        this.sensor = sensor;
        sensor.agregarSubscripcion(this);
        this.tareas = tareas;
    }
    
    public void removerEstimulo() {
        sensor.removerSubscripcion(this);
    }

    public void ejecutarTareas() {
        tareas.forEach(tarea -> tarea.ejecutar());
    }

}
