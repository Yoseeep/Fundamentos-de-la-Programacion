package fp.trenes.test;

import java.time.LocalTime;

import fp.trenes.TrayectoTren;
import fp.trenes.TipoTren;
import fp.trenes.TrayectoTrenImpl1;

public class TestTrayectoTren {

	public static void main(String[] args) {
		TrayectoTren tt = new TrayectoTrenImpl1("02471",
												"Sevilla-Madrid", 
												TipoTren.AVE,
												"Sevilla (Sta. Justa)",
												"Madrid (Puerta de Atocha)",
												LocalTime.of(7, 0),
												LocalTime.of(10,2));
		System.out.println(tt);
		
		tt.añadirEstacionIntermedia(1, "PUERTOLLANO", LocalTime.of(8, 40), LocalTime.of(8, 41));
		System.out.println("\n\n" + tt);
	}
}
