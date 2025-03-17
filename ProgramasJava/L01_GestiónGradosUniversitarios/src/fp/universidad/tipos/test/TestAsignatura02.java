package fp.universidad.tipos.test;

import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.TipoAsignatura;

public class TestAsignatura02 {

	public static void main(String[] args) {
		testConstructor();

	}
	
	private static void testConstructor() {
		System.out.println("\ntestConstructorCorrecto");
		try {
			Asignatura a=new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,1);
			System.out.println(a);
			System.out.println("Créditos: "+a.creditos());
			System.out.println("Tipo: "+a.tipo());
			System.out.println("Curso: "+a.curso());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructorCréditosIncorrectos");
		try {
			Asignatura a=new Asignatura("Fundamentos de Programación","0000230",0d,
					TipoAsignatura.ANUAL,1);
			System.out.println(a);
			System.out.println("Créditos: "+a.creditos());
			System.out.println("Tipo: "+a.tipo());
			System.out.println("Curso: "+a.curso());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructorCursoIncorrecto");
		try {
			Asignatura a=new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,5);
			System.out.println(a);
			System.out.println("Créditos: "+a.creditos());
			System.out.println("Tipo: "+a.tipo());
			System.out.println("Curso: "+a.curso());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructorCódigoIncorrecto- 1");
		try {
			Asignatura a=new Asignatura("Fundamentos de Programación","A000230",12d,
					TipoAsignatura.ANUAL,1);
			System.out.println(a);
			System.out.println("Créditos: "+a.creditos());
			System.out.println("Tipo: "+a.tipo());
			System.out.println("Curso: "+a.curso());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("\ntestConstructorCódigoIncorrecto- 2");
		try {
			Asignatura a=new Asignatura("Fundamentos de Programación","000230",12d,
					TipoAsignatura.ANUAL,1);
			System.out.println(a);
			System.out.println("Créditos: "+a.creditos());
			System.out.println("Tipo: "+a.tipo());
			System.out.println("Curso: "+a.curso());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		
	}

}
