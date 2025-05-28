package fp.examenes.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;

import fp.examenes.CalendarioExamenes;
import fp.examenes.Examen;
import fp.examenes.FactoriaExamenes;
import fp.examenes.TipoExamen;

public class TestCalendarioExamenes {

	public static void main(String[] args) {
		try {
			CalendarioExamenes calendario = FactoriaExamenes.leeExamen("data/CSV_de_la_sesión_2.csv");
			testLectura();
			
			testGetExamenesPorAula(calendario);
			
			testGetExamenMayorPorcentajeAsistentes(calendario);
			
			testGetAulasExamenesTipo(calendario);
			
			testGetAulaMasOcupada(calendario);
			
			testGetFechasConMasAulasDe(calendario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testLectura() {
		System.out.println("Test lectura ===================================================================");
		CalendarioExamenes calendario = FactoriaExamenes.leeExamen("data/CSV_de_la_sesión_2.csv");
		System.out.println("Se han leido " + calendario.getNumeroExamenes() + " exámenes.");
		for (Examen examen : calendario.getExamenes()) {
			System.out.println(examen);
		}
	}
	
	private static void testGetExamenesPorAula(CalendarioExamenes c) {
		System.out.println("\nEJERCICIO 4.1 ===================================================================");
		System.out.println("Exámenes por aula:");
		Map<String,Set<Examen>> resultado = c.getExamenesPorAula();
		for(Entry<String,Set<Examen>> par : resultado.entrySet()) {
			System.out.println("Aula " + par.getKey() + ": " + par.getValue());
		}
	}
	
	private static void testGetExamenMayorPorcentajeAsistentes(CalendarioExamenes c) {
		System.out.println("\nEJERCICIO 4.2 ===================================================================");
		System.out.print("Examen con mayor porcentaje de asistentes realizado en el aula F1.30" + 
				" y con hora de comienzo posterior a las 08:30: ");
		Examen resultado = c.getExamenMayorPorcentajeAsistentes(LocalTime.of(8, 30),"F1.30");
		System.out.print(resultado);
		System.out.print("\nExamen con mayor porcentaje de asistentes realizado en el aula I2.31" + 
				" y con hora de comienzo posterior a las 15:30: ");
		Examen resultado1 = c.getExamenMayorPorcentajeAsistentes(LocalTime.of(15, 30),"I2.31");
		System.out.println(resultado1);
	}
	
	private static void testGetAulasExamenesTipo(CalendarioExamenes c) {
		System.out.println("\nEJERCICIO 4.3 ===================================================================");
		System.out.print("Aulas utilizadas en exámenes de tipo PRACTICO:");
		SortedSet<String> resultado = c.getAulasExamenesTipo(TipoExamen.PRACTICO);
		System.out.println(resultado);
		System.out.print("Aulas utilizadas en exámenes de tipo TEORICO:");
		SortedSet<String> resultado1 = c.getAulasExamenesTipo(TipoExamen.TEORICO);
		System.out.println(resultado1);
	}
	
	private static void testGetAulaMasOcupada(CalendarioExamenes c) {
		System.out.println("\nEJERCICIO 4.4 ===================================================================");
		System.out.print("Aula con mayor ocupación en la fecha 2024-05-25: ");
		String resultado = c.getAulaMasOcupada(LocalDate.of(2024, 5, 25));
		System.out.println(resultado);
		System.out.print("Aula con mayor ocupación en la fecha 2024-06-07: ");
		String resultado1 = c.getAulaMasOcupada(LocalDate.of(2024, 6, 7));
		System.out.println(resultado1);
	}
	
	private static void testGetFechasConMasAulasDe(CalendarioExamenes c) {
		System.out.println("\nEJERCICIO 4.5 ===================================================================");
		System.out.print("Fechas con más de 5 aulas: ");
		List<LocalDate> resultado = c.getFechasConMasAulasDe(5);
		System.out.println(resultado);
		System.out.print("Fechas con más de 8 aulas: ");
		List<LocalDate> resultado1 = c.getFechasConMasAulasDe(8);
		System.out.println(resultado1);
	}

}
