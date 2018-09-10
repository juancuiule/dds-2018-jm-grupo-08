package dominio.transformadores;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Coordenada")
public class Punto {
	
	@Id @GeneratedValue
	private Long id;
	
	double posLat;
	double posLong;

	public Punto(double posLat, double posLong) {
		this.posLat = posLat;
		this.posLong = posLong;
	}
	public Double getPosLat() {
		return posLat;
	}
	public Double getPosLong() {
		return posLong;
	}
}