package fp.universidad.tipos.test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;

import fp.universidad.tipos.Alumno;
import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.ConsultaUniversidad;
import fp.universidad.tipos.FactoriaUniversidad;
import fp.universidad.tipos.Nota;
import fp.universidad.tipos.TipoAsignatura;


public class TestConsultaUniversidad {

	public static void main(String[] args) {
		testTodosLosAlumnosEntreEdades();
		testAsignaturaConMasCreditos();
		testAgrupaAlumnosPorPrimerApellido();
		testNumAsignaturasPorTipo();
		testNotaMediaPorAsignatura();
	}
	
	private static void testTodosLosAlumnosEntreEdades() {
		try {
			System.out.println("\ntestTodosLosAlumnosEntreEdades");
			List<Alumno> alumnos= FactoriaUniversidad.leeAlumnos("data/alumnos.csv");
			
			int edadMinima = 17;
			int edadMaxima = 20;
			Boolean resultado = ConsultaUniversidad.todosLosAlumnosEntreEdades(alumnos, edadMinima, edadMaxima);
			System.out.println("Entre: ["+ edadMinima + ", " + edadMaxima + "] : " + resultado);
			
			edadMinima = 18;
			edadMaxima = 40;
			resultado = ConsultaUniversidad.todosLosAlumnosEntreEdades(alumnos, edadMinima, edadMaxima);
			System.out.println("Entre: ["+ edadMinima + ", " + edadMaxima + "] : " + resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testAsignaturaConMasCreditos() {
		try {
			System.out.println("\ntestAsignaturaConMasCreditos");
			List<Asignatura> asignaturas = FactoriaUniversidad.leeAsignaturas("data/asignaturas.txt");
			
			Asignatura resultado = ConsultaUniversidad.asignaturaConMasCreditos(asignaturas);
			System.out.println(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testAgrupaAlumnosPorPrimerApellido() {
		try {
			System.out.println("\ntestAgrupaAlumnosPorPrimerApellido");
			List<Alumno> alumnos= FactoriaUniversidad.leeAlumnos("data/alumnos.csv");
			
			
			Integer edadMinima;
			Integer edadMaxima;
			SortedMap<String, Set<Alumno>> resultado = ConsultaUniversidad.agrupaAlumnosPorPrimerApellido(alumnos);
			
			for (Entry<String, Set<Alumno>> par : resultado.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testNumAsignaturasPorTipo() {
		try {
			System.out.println("\ntestNumAsignaturasPorTipo");
			List<Asignatura> asignaturas = FactoriaUniversidad.leeAsignaturas("data/asignaturas.txt");
			
			Map<TipoAsignatura, Integer> resultado = ConsultaUniversidad.numAsignaturasPorTipo(asignaturas);
			
			for (Entry<TipoAsignatura, Integer> par : resultado.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void testNotaMediaPorAsignatura() {
		try {
			System.out.println("\ntestNotaMediaPorAsignatura");
			List<Nota> notas = FactoriaUniversidad.leeNotas("data/notas.csv");
			
			Map<Asignatura, Double> resultado = ConsultaUniversidad.notaMediaPorAsignatura(notas);
			
			for (Entry<Asignatura, Double> par : resultado.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
