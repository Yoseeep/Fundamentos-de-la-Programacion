package fp.hamburgueserias.test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.SortedSet;

import fp.hamburgueserias.Competicion;
import fp.hamburgueserias.FactoriaVisitas;

public class TestCompeticion {

	public static void main(String[] args) {
		try {
			
			Competicion competicion = FactoriaVisitas.leeVisitas("data/CSV de la sesión 2.csv");
			testLeeVisitas(competicion);
			
			testGetEmailsOrdenados(competicion);
			
			testGetTotalVisitasComilones(competicion);
			
			testgetPeorHamburgueseriaPorCalidadIngredientes(competicion);
			
			testGetTopComilonPorCPEnDia(competicion);
			
			testGetHamburgueseriaGanadora(competicion);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testLeeVisitas(Competicion c) {
		System.out.println("FACTORÍA = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("Leidas " + c.getVisitas().size() + " visitas");
		System.out.println("Primera visita: " + c.getVisitas().getFirst());
		System.out.println("Primera visita: " + c.getVisitas().getLast());
	}
	
	private static void testGetEmailsOrdenados(Competicion c) {
		System.out.println("\n\nEJ1 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("Los emails de las visitas con duración mayor a 240 minutos son");
		SortedSet<String> resultado = c.getEmailsOrdenados( Duration.ofMinutes(240) );
		System.out.println(resultado);
		System.out.println("Los emails de las visitas con duración mayor a 300 minutos son");
		SortedSet<String> resultado1 = c.getEmailsOrdenados( Duration.ofMinutes(300) );
		System.out.println(resultado1);
	}

	private static void testGetTotalVisitasComilones(Competicion c) {
		System.out.println("\n\nEJ2 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("El total de visitas con un número de evaluaciones mayor a la media es:");
		Integer resultado = c.getTotalVisitasComilones();
		System.out.println(resultado);
	}
	
	private static void testgetPeorHamburgueseriaPorCalidadIngredientes(Competicion c) {
		System.out.println("\n\nEJ3 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("Si se tiene en cuenta solo la calidad de ingredientes, la peor hamburguesería es:");
		String resultado = c.getPeorHamburgueseriaPorCalidadIngredientes();
		System.out.println(resultado);
	}
	
	private static void testGetTopComilonPorCPEnDia(Competicion c) {
		System.out.println("\n\nEJ4 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("En el día 2024-06-02 la persona que más comió de cada CP es:");
		Map<String, String> resultado = c.getTopComilonPorCPEnDia(LocalDate.of(2024, 6, 2));
		System.out.println(resultado);
	}
	
	private static void testGetHamburgueseriaGanadora(Competicion c) {
		System.out.println("\n\nEJ5 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("La hamburguesería ganadora del campeonato es:");
		String resultado = c.getHamburgueseriaGanadora();
		System.out.println(resultado);
	}
	
	
	
}
