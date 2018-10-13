package dominio.reporte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.mockito.Mock.*;
import static org.mockito.Mockito.*;

import dominio.Cliente;
import dominio.TipoDeDocumento;
import dominio.dispositivo.ComportamientoEstandar;
import dominio.dispositivo.ComportamientoInteligente;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoFisico;
import dominio.transformadores.Punto;
import dominioTest.mocks.DispositivoFisicoMock;

public class prueba {

	public static void main(String[] args) {
		ReporteHogar reporteHogar = new ReporteHogar();
		ReporteDispositivo reporteDispositivo = new ReporteDispositivo();
		Cliente cli = mock(Cliente.class);
		when(cli.getNumeroDeDocumento()).thenReturn(new Integer(33333334));
		
//		DispositivoFisico dispositivoFisico = new DispositivoFisicoMock();
		LocalDate fechaDesde= LocalDate.of(2018,02,25);
		
		LocalDate fechaHasta = LocalDate.of(2018,03,11);
//		Cliente cliente = new Cliente("Marjorie", "Shaw", TipoDeDocumento.DNI, 33333334, 42000000, LocalDate.now(),
//				"7807 Samaritan Dr", "majshaw", "hudson",
//				new ArrayList<Dispositivo>(Arrays.asList(
//						new Dispositivo("Aire Acondicionado 2200 Frigorias", new ComportamientoEstandar(1.35, 12.0)),
//						new Dispositivo("Heladera con Freezer", new ComportamientoEstandar(0.4, 12.0)),
//						new Dispositivo("Tostadora", new ComportamientoInteligente(dispositivoFisico)))),
//				false, new Punto(20, 15));
		
//		String reporte = reporteHogar.consumoHogar(fechaDesde, fechaHasta, cli);
		
		String reporte2 = reporteDispositivo.consumoPromedioPorDispositivo(cli);
		System.out.println(cli);

	}

}
