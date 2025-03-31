package fp.universidad.tipos.test;

import java.util.List;

import fp.universidad.tipos.Alumno;
import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.Despacho;
import fp.universidad.tipos.Espacio;
import fp.universidad.tipos.FactoriaUniversidad;
import fp.universidad.tipos.Nota;

public class TestFactoriaUniversidad {

	public static void main(String[] args) {
		List<Espacio> espacios = FactoriaUniversidad.leeEspacios("data/espacios.csv");
		for (Espacio espacio: espacios) {
			System.out.println(espacio);
		}
		
		List<Despacho> despachos = FactoriaUniversidad.leeDespacho("data/despachos.csv");
		for (Espacio despacho: despachos) {
			System.out.println(despacho);
		}
		
		List<Alumno> alumnos= FactoriaUniversidad.leeAlumnos("data/alumnos.csv");
		for (Alumno alumno: alumnos) {
			System.out.println(alumno);
		}
		
		List<Asignatura> asignaturas = FactoriaUniversidad.leeAsignaturas("data/asignaturas.txt");
		for (Asignatura asignatura: asignaturas) {
			System.out.println(asignatura);
		}
		
		List<Nota> notas = FactoriaUniversidad.leeNotas("data/notas.csv");
		for (Nota nota: notas) {
			System.out.println(nota);
		}
	}	

}
