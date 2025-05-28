package fp.carreras.test;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import fp.carreras.Carrera;
import fp.carreras.Carreras;
import fp.carreras.Categoria;
import fp.carreras.FactoriaCarreras;
import fp.carreras.Participante;

public class TestCarreras {

	public static void main(String[] args) {
		try {
			// Map<String, List<Participante>> participantes = FactoriaCarreras.leeParticipantes("data/CSV de la sesión 2 (2).csv");
			//System.out.println(participantes); 
			// testLeeParticipantes(participantes);
			
			Carreras carreras = FactoriaCarreras.leeCarreras(
					"data/CSV de la sesión 2 (1).csv", 
					"data/CSV de la sesión 2 (2).csv"
					);
			// System.out.println(carreras);
			testLeeCarreras(carreras);
			
			testCarreraMayorDesnivelParticipante(carreras);
			
			testTiempoMedioCarrera(carreras);
			
			testGanadoresPorCategoria(carreras);
			
			testPosicionesParticipante(carreras);
			
			testCategoriaMasParticipantes(carreras);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void testLeeParticipantes(Map<String, List<Participante>> p) {
		System.out.println("\nTest leeParticipantes");
		System.out.println("Se ha leído información de " + 
							p.size() + " participantes");
	}
	
	private static void testLeeCarreras(Carreras c) {
		System.out.println("\nTest leeCarreras");
		System.out.println("Se ha leído información de " + 
							c.getCarreras().size() + " carreras");
		System.out.println("-----");
		System.out.println("En la última carrera han participado " + 
							c.participantesUltimaCarrera().size() + 
							" participantes");
	}
	
	private static void testCarreraMayorDesnivelParticipante(Carreras c) {
		System.out.println("\n\n----- Carrera con mayor desnivel de participante: --------");
		System.out.print("\nElena Blanco Vézquez: ");
		Carrera resultado = c.carreraMayorDesnivelParticipante("Elena", "Blanco Vázquez");
		System.out.println(resultado);
		System.out.print("\nAlejandro Ruiz Blanco: ");
		Carrera resultado1 = c.carreraMayorDesnivelParticipante("Alejandro", "Ruiz Blanco");
		System.out.println(resultado1);
	}
	
	private static void testTiempoMedioCarrera(Carreras c) {
		System.out.println("\n\n----- Tiempo medio de carrera: --------");
		System.out.print("\nmedmar_sev_24: ");
		Double resultado = c.tiempoMedioCarrera("medmar_sev_24");
		System.out.println(resultado);
		System.out.print("\ntr_cobre_23: ");
		Double resultado1 = c.tiempoMedioCarrera("tr_cobre_23");
		System.out.println(resultado1);
	}
	
	private static void testGanadoresPorCategoria(Carreras c) {
		System.out.println("\n\n----- Ganadores por categoría: --------");
		System.out.print("\ntr_lima_pedr_24: ");
		SortedMap<Categoria, String> resultado = c.ganadoresPorCategoria("tr_lima_pedr_24");
		System.out.println(resultado);
	}
	
	private static void testPosicionesParticipante(Carreras c) {
		System.out.println("\n\n----- Posiciones participante: --------");
		System.out.println("\nSara Navarro Díaz: ");
		Map<String, Integer> resultado = c.posicionesParticipante("Sara","Navarro Díaz");
		System.out.println("\t" + resultado);
		System.out.println("\nLuis Blanco Pérez: ");
		Map<String, Integer> resultado1 = c.posicionesParticipante("Luis","Blanco Pérez");
		System.out.println("\t" + resultado1);
	}
	
	private static void testCategoriaMasParticipantes(Carreras c) {
		System.out.println("\n\n----- Categoría con mayor número de participantes: --------");
		System.out.println("\nSara Navarro Díaz: ");
		Categoria resultado = c.categoriaMasParticipantes();
		System.out.println(resultado);		
	}

}
