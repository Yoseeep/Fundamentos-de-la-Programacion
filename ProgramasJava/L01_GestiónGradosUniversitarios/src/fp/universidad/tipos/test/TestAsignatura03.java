package fp.universidad.tipos.test;

import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.TipoAsignatura;

public class TestAsignatura03 {

	public static void main(String[] args) {
		System.out.println("testMismasAsignaturas");
		Asignatura a1=new Asignatura("Fundamentos de Programación","0000230",12d,
				TipoAsignatura.ANUAL,1);
		Asignatura a2=new Asignatura("Fundamentos de Programación","0000230",12d,
				TipoAsignatura.ANUAL,1);
		int comparacion = a1.compareTo(a2);
		
		System.out.println("Asignatura 1:" + a1);
		System.out.println("Asignatura 2:" + a2);
		System.out.println("Asignatura 1 vs Asignatura 2: " + comparacion);
		
		
		
		System.out.println("\ntestDistintasAsignaturas");
		a1 = new Asignatura("Fundamentos de Programación","0000001",12d,
				TipoAsignatura.ANUAL,1);
		comparacion = a1.compareTo(a2);
		
		System.out.println("Asignatura 1:" + a1);
		System.out.println("Asignatura 2:" + a2);
		System.out.println("Asignatura 1 vs Asignatura 2: " + comparacion);
		
		
		System.out.println("\ntestDistintasAsignaturas");
		a1 = new Asignatura("Fundamentos de Programación","1200000",12d,
				TipoAsignatura.ANUAL,1);
		comparacion = a1.compareTo(a2);
		
		System.out.println("Asignatura 1:" + a1);
		System.out.println("Asignatura 2:" + a2);
		System.out.println("Asignatura 1 vs Asignatura 2: " + comparacion);
		

	}

}
