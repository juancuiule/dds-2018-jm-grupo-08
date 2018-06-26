package dominio.dispositivo;

import java.time.Period;

public class Dispositivo {
    private Comportamiento comportamiento;
    private String nombre;

    public Dispositivo(String nombre, Comportamiento comportamiento) {
        this.comportamiento = comportamiento;
        this.nombre = nombre;
    }
    
    public Double consumoPorHora() {
    	return comportamiento.consumoPorHora();
    }
    
    
    public Boolean estaEncendido() {
        try{
            return comportamiento.estaEncendido();
        }catch(MensajeNoEntendidoException  e){
            return false;
        }
    }
    public Boolean estaApagado(){
        try{
            return comportamiento.estaApagado();
        }catch(MensajeNoEntendidoException  e){
            return false;
        }
    }
    public Double consumoEnUltimasHoras(Double horas){
        return comportamiento.consumoEnUltimasHoras(horas);
    }
    public Double consumoEnElPeriodo(Period periodo){
        return comportamiento.consumoEnElPeriodo(periodo);
    }
    public void apagar(){
        try{
            comportamiento.apagar();
        }catch(MensajeNoEntendidoException e){
            // Nada
        }
    }
    public void encender(){
        try{
            comportamiento.encender();
        }catch(MensajeNoEntendidoException e){
            // Nada
        }
    }
    public void ahorrarEnergia(){
        try{
            comportamiento.ahorrarEnergia();
        }catch(MensajeNoEntendidoException e){
            // Nada
        }
    }
    public void agregarModuloAdaptador() {
    	try{
    		this.comportamiento = comportamiento.convertirAInteligente();
        }catch(MensajeNoEntendidoException e){
            // Nada
        }
    }
}
