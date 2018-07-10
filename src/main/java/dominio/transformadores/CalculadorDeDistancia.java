package dominio.transformadores;

public class CalculadorDeDistancia {
	public static double distance(Punto unPunto, Punto otroPunto) {
		double lat1 = unPunto.getPosLat();
		double lon1 = unPunto.getPosLong();
        double lon2 = otroPunto.getPosLat();
        double lat2 = otroPunto.getPosLong();
        
	    int R = 6371; // Radio de la tierra
	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c; // queda en kms
	    return distance;
	}
}
