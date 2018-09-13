package dominioTest.mocks;

import dominio.reactores.Regla;
import dominio.reactores.Sensor;

public class ReglaLuminosidadMock2 extends Regla{

	@Override
	public Boolean cumpleConCondiciones(Sensor unSensor) {
		// TODO Auto-generated method stub
		return unSensor.getMagnitud() >= 7;
	}

}
