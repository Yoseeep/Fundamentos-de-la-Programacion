package fp.universidad.tipos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Ficheros;
import utiles.Checkers;

public class FactoriaUniversidad {
	public static Espacio creaEspacio (String s) {
		return new Espacio(s);
	}
	
	public static List<Espacio> leeEspacios(String ruta){
		List<Espacio> res = new ArrayList<Espacio>();
		List<String> lineas = Ficheros.leeFichero("Error al leer el fichero: " + ruta, ruta);
		for(String linea: lineas) {
			res.add(creaEspacio(linea));
		}
		return res;
	}
	
	
	public static Despacho creaDespacho (String s) {
		return new Despacho(s);
	}
	
	public static List<Despacho> leeDespacho(String ruta){
		List<Despacho> res = new ArrayList<Despacho>();
		List<String> lineas = Ficheros.leeFichero("Error al leer el fichero: " + ruta, ruta);
		for(String linea: lineas) {
			res.add(creaDespacho(linea));
		}
		return res;
	}
	
	
	
	public static Alumno creaAlumno(String s) {
		String[] tr = s.split(",");
		Checkers.check("La cadena no está bien formateada: " + s, tr.length == 5);
		String DNI = tr[0].trim(); 
		String nombre = tr[1].trim(); 
		String apellidos = tr[2].trim();
		LocalDate fechaNacimiento = LocalDate.parse(tr[3].trim(),DateTimeFormatter.ofPattern("d/M/y"));
		String email = tr[4].trim();
		return new Alumno(DNI, nombre, apellidos, fechaNacimiento, email);
	}
	
	public static List<Alumno> leeAlumnos(String ruta){
		List<Alumno> res = new ArrayList<Alumno>();
		List<String> lineas = Ficheros.leeFichero("Error al leer el fichero: " + ruta, ruta);
		for(String linea: lineas.subList(1, lineas.size())) {
			res.add(creaAlumno(linea));
		}
		return res;
	}
	
	
	
	public static Asignatura creaAsignatura(String s) {
		String[] tr = s.split("#");
		String nombre = tr[0].trim(); 
		String codigo = tr[1].trim(); 
		Double creditos = Double.valueOf( tr[2].trim() );
		TipoAsignatura tipo = TipoAsignatura.valueOf( tr[3].trim() );
		Integer curso = Integer.valueOf( tr[4].trim() );
		return new Asignatura(nombre, codigo, creditos, tipo, curso);
	}
	
	public static List<Asignatura> leeAsignaturas(String ruta){
		List<Asignatura> res = new ArrayList<Asignatura>();
		List<String> lineas = Ficheros.leeFichero("Error al leer el fichero: " + ruta, ruta);
		for(String linea: lineas) {
			res.add(creaAsignatura(linea));
		}
		return res;
	}

	
	
	public static Nota creaNota(String s) {
		String[] tr = s.split(",");
		Checkers.check("La cadena no está bien troceada: " + s, tr.length == 5);
		Asignatura asignatura = creaAsignatura( tr[0].trim() ); 
		Integer curso = Integer.valueOf( tr[1].trim() ); 
		Convocatoria convocatoria = Convocatoria.valueOf( tr[2].trim() ); 
		Double valor = Double.valueOf(tr[3].trim());
		Boolean mencionHonor = tr[4].trim().equals("true");
		return new Nota(asignatura, curso, convocatoria, valor, mencionHonor);
	}
	
	public static List<Nota> leeNotas(String ruta){
		List<Nota> res = new ArrayList<Nota>();
		List<String> lineas = Ficheros.leeFichero("Error al leer el fichero: " + ruta, ruta);
		for(String linea: lineas) {
			res.add(creaNota(linea));
		}
		return res;
	}
	
}
