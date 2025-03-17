package fp.universidad.tipos.test;

import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.TipoAsignatura;

public class TestAsignaturaNoValido {

	public static void main(String[] args) {
		testConstructor();

	}
	
	private static void testConstructor() {
		System.out.println("\ntestConstructorCorrecto");
		try {
			Asignatura a = new Asignatura("Fundamento de la Programación", "0000230", 12d, TipoAsignatura.ANUAL, 1);
			System.out.println(a);
			System.out.println("Créditos: " + a.creditos());
			System.out.println("Tipo: " + a.tipo());
			System.out.println("Curso: " + a.curso());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		System.out.println("\ntestConstructorCréditosIncorrecto");
		try {
			Asignatura a = new Asignatura("Fundamento de la Programación", "0000230", 12d, TipoAsignatura.ANUAL, 5);
			System.out.println(a);
			System.out.println("Créditos: " + a.creditos());
			System.out.println("Tipo: " + a.tipo());
			System.out.println("Curso: " + a.curso());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		System.out.println("\ntestConstructorCódigoIncorrecto1");
		try {
			Asignatura a = new Asignatura("Fundamento de la Programación", "00002B0", 12d, TipoAsignatura.ANUAL, 5);
			System.out.println(a);
			System.out.println("Créditos: " + a.creditos());
			System.out.println("Tipo: " + a.tipo());
			System.out.println("Curso: " + a.curso());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		System.out.println("\ntestConstructorCódigoIncorrecto2");
		try {
			Asignatura a = new Asignatura("Fundamento de la Programación", "00002301", 12d, TipoAsignatura.ANUAL, 4);
			System.out.println(a);
			System.out.println("Créditos: " + a.creditos());
			System.out.println("Tipo: " + a.tipo());
			System.out.println("Curso: " + a.curso());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		System.out.println("\ntestConstructorCreditosIncorrecto");
		try {
			Asignatura a = new Asignatura("Fundamento de la Programación", "0000230", 0d, TipoAsignatura.ANUAL, 4);
			System.out.println(a);
			System.out.println("Créditos: " + a.creditos());
			System.out.println("Tipo: " + a.tipo());
			System.out.println("Curso: " + a.curso());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
