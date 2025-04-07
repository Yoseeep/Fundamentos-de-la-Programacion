package fp.universidad.tipos;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsultaUniversidad {
	
	public static Boolean todosLosAlumnosEntreEdades(List<Alumno> alumnos, Integer minEdad, Integer maxEdad) {
		Boolean res = true;
		for (Alumno alumno : alumnos) {
			if ( !(minEdad <= alumno.getEdad() && alumno.getEdad() <= maxEdad) ) {
				res = false;
				break;
			}
		}
		return res;
	}
	
	public static Asignatura asignaturaConMasCreditos(List<Asignatura> asignaturas) {
		Asignatura res = null;
		
		for (Asignatura asignatura : asignaturas) {
			if (res == null || asignatura.creditos() > res.creditos()) {
				res = asignatura;
			}
		}
		
		return res;
	}
	
	
	public static SortedMap<String, Set<Alumno>> agrupaAlumnosPorPrimerApellido(List<Alumno> alumnos){
		SortedMap<String, Set<Alumno>> res  = new TreeMap< String, Set<Alumno> >();
		for (Alumno alumno : alumnos) {
			String clave = alumno.getApellidos().split(" ")[0];
			if (! res.containsKey(clave)) {
				Set<Alumno> valor = new HashSet<Alumno>();
				valor.add(alumno);
				res.put(clave, valor);
			}
			else {
				res.get(clave).add(alumno);
				/*
				 * Set<Alumno> valor = Set.copyOf(res.get(clave)); valor.add(alumno);
				 * res.put(clave,valor);
				 */
			}
		}
		return res;
	}
	
	
	public static Map<TipoAsignatura, Integer> numAsignaturasPorTipo(List<Asignatura> asignaturas){
		SortedMap<TipoAsignatura, Integer> res = new TreeMap<TipoAsignatura, Integer>();
		
		for (Asignatura asignatura : asignaturas) {
			TipoAsignatura clave = asignatura.tipo();
			if (! res.containsKey(clave)) {
				res.put(clave, 1);
			}
			else {
				res.put(clave, res.get(clave) + 1);
			}
		}
		
		return res;
	}
	
	public static Map<Asignatura, Double> notaMediaPorAsignatura(List<Nota> notas){
		Map<Asignatura, List<Double>> aux = new HashMap<Asignatura, List<Double>>();
		Map<Asignatura, Double> res = new HashMap<Asignatura, Double>();
		
		for (Nota nota : notas) {
			Asignatura clave = nota.asignatura();
			if (! aux.containsKey(clave)) {
				List<Double> valor = new ArrayList<Double>();
				valor.add(nota.valor());
				aux.put(clave, valor);
			}
			else {
				aux.get(clave).add(nota.valor());
			}
		}
		
		//TODO
		for (java.util.Map.Entry<Asignatura, List<Double>> par: aux.entrySet()) {
			Asignatura clave = par.getKey();
			double acum = 0;
			for (double i : par.getValue()) {
				acum += i; 
			}
			res.put(clave, acum / par.getValue().size());
			/*
			 * if (! res.containsKey(clave)) { List<Double> valor = new ArrayList<Double>();
			 * valor.add(nota.valor()); aux.put(clave, valor); } else {
			 * aux.get(clave).add(nota.valor()); }
			 */
		}
		
		return res;
	}
	
}
