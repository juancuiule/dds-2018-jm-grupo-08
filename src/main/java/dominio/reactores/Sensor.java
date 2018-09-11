package domain;

import java.util.ArrayList;


public class Sensor {
	
	//Attributes
	
	
	private ArrayList<Regla> reglas;
	private int magnitud;
	
	
    //Methods
	
	public void comunicar() {
			reglas.forEach(regla -> regla.evaluar(this));
	 	  //System.out.println("Mensaje del sensor!");
	}

	public int getMagnitud() {
		return magnitud;
	}

	public void agregarRegla(Regla regla1) {
		reglas.add(regla1);
		
	}
	
	// un sensor puede medir la temperatura, humedad, etc
	
	public void medirMagnitud() {
		//aca se incrementa o disminuye la magnitud 
	}

	
}
	
	
	


