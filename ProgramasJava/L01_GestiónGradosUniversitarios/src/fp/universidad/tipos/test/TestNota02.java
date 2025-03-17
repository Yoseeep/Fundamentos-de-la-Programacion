package fp.universidad.tipos.test;

import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.Convocatoria;
import fp.universidad.tipos.Nota;
import fp.universidad.tipos.TipoAsignatura;

public class TestNota02 {

	public static void main(String[] args) {
		testConstructor1();
		testConstructor2();

	}
	
	private static void testConstructor1() {
		System.out.println("\ntestConstructor1Correcto");
		try {
			Asignatura a = new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,1);
			Nota n = new Nota(a,2014,Convocatoria.TRIMERA,7.5,false);
			System.out.println(n);
			System.out.println("Asignatura: "+n.asignatura());
			System.out.println("Curso: "+n.curso());
			System.out.println("Convocatoria: "+n.convocatoria());
			System.out.println("Valor: "+n.valor());
			System.out.println("MencionHonor: "+n.mencionHonor());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1Correcto");
		try {
			Asignatura a = new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,1);
			Nota n = new Nota(a,2014,Convocatoria.TRIMERA,9d,true);
			System.out.println(n);
			System.out.println("Asignatura: "+n.asignatura());
			System.out.println("Curso: "+n.curso());
			System.out.println("Convocatoria: "+n.convocatoria());
			System.out.println("Valor: "+n.valor());
			System.out.println("MencionHonor: "+n.mencionHonor());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1Incorrecto");
		try {
			Asignatura a = new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,1);
			Nota n = new Nota(a,2014,Convocatoria.TRIMERA,11d,false);
			System.out.println(n);
			System.out.println("Asignatura: "+n.asignatura());
			System.out.println("Curso: "+n.curso());
			System.out.println("Convocatoria: "+n.convocatoria());
			System.out.println("Valor"+n.valor());
			System.out.println("MencionHonor: "+n.mencionHonor());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1Incorrecto");
		try {
			Asignatura a = new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,1);
			Nota n = new Nota(a,2014,Convocatoria.TRIMERA,7.5,true);
			System.out.println(n);
			System.out.println("Asignatura: "+n.asignatura());
			System.out.println("Curso: "+n.curso());
			System.out.println("Convocatoria: "+n.convocatoria());
			System.out.println("Valor"+n.valor());
			System.out.println("MencionHonor: "+n.mencionHonor());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	private static void testConstructor2() {
		System.out.println("\ntestConstructor1Correcto");
		try {
			Asignatura a = new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,1);
			Nota n = new Nota(a,2014,Convocatoria.TRIMERA,7.5);
			System.out.println(n);
			System.out.println("Asignatura: "+n.asignatura());
			System.out.println("Curso: "+n.curso());
			System.out.println("Convocatoria: "+n.convocatoria());
			System.out.println("Valor: "+n.valor());
			System.out.println("MencionHonor: "+n.mencionHonor());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1Incorrecto");
		try {
			Asignatura a = new Asignatura("Fundamentos de Programación","0000230",12d,
					TipoAsignatura.ANUAL,1);
			Nota n = new Nota(a,2014,Convocatoria.TRIMERA,11d);
			System.out.println(n);
			System.out.println("Asignatura: "+n.asignatura());
			System.out.println("Curso: "+n.curso());
			System.out.println("Convocatoria: "+n.convocatoria());
			System.out.println("Valor"+n.valor());
			System.out.println("MencionHonor: "+n.mencionHonor());
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
