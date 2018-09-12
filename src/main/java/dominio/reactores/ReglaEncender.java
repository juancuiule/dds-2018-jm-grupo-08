package dominio.reactores;

public class ReglaEncender extends Regla{
	
	private int valor;

	@Override
	public Boolean cumpleConCondiciones(Sensor unSensor) {
		// es un ejemplo 
		return unSensor.getMagnitud()< valor;
	}

}
