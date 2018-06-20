package dominio.dispositivo;

import java.time.Period;

public class Dispositivo {
    private Comportamiento comportamiento;
    public Boolean estaEncendido() {
        try{
            return comportamiento.estaEncendido();
        }catch(ComportamientoNoAdmitidoException  e){
            return false;
        }
    }
    public Boolean estaApagado(){
        try{
            return comportamiento.estaApagado();
        }catch(ComportamientoNoAdmitidoException  e){
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
        }catch(ComportamientoNoAdmitidoException e){
            // Nada
        }
    }
    public void encender(){
        try{
            comportamiento.encender();
        }catch(ComportamientoNoAdmitidoException e){
            // Nada
        }
    }
    public void ahorrarEnergia(){
        try{
            comportamiento.ahorrarEnergia();
        }catch(ComportamientoNoAdmitidoException e){
            // Nada
        }
    }
}
