package dominio.dispositivo;

import dominio.PersistentObject;

import java.time.Period;

import javax.persistence.*;

@Entity
@Table(name = "Dispositivo")
public class Dispositivo extends PersistentObject {

	@OneToOne
    private Comportamiento comportamiento;

    private String nombre;

    @Embedded
    private Rango limiteDeConsumo;

    public Dispositivo(Comportamiento comportamiento, String nombre, Rango limiteDeConsumo) {
        super();
        this.comportamiento = comportamiento;
        this.nombre = nombre;
        this.limiteDeConsumo = limiteDeConsumo;
    }

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
    public Double consumoEnElPeriodo(Double diasUltimoMes){
    	return comportamiento.consumoEnElPeriodo(diasUltimoMes);
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
    public void cambiarComportamiento(Comportamiento nuevoComportamiento) {
        comportamiento = nuevoComportamiento;
    }

    public Rango restricciones() {
        if(limiteDeConsumo == null) {
            throw new NoExistenRestriccionesException();
        }
        return limiteDeConsumo;
    }

	public void restringirConsumo() {
        try{
            comportamiento.restringirConsumo();
        }catch(MensajeNoEntendidoException e){
            // Nada
        }
		
	}
}
