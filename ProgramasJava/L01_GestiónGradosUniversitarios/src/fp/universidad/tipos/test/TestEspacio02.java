package fp.universidad.tipos.test;

import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.Espacio;
import fp.universidad.tipos.TipoAsignatura;
import fp.universidad.tipos.TipoEspacio;

public class TestEspacio02 {

	public static void main(String[] args) {
		testConstructor1();
		testSetCapacidad();

	}
	
	private static void testConstructor1() {
		System.out.println("\ntestConstructor1Correcto");
		try {
			Espacio a=new Espacio(TipoEspacio.TEORIA, "A3.10", 70, 3);
			System.out.println(a);
			System.out.println("Tipo: "+a.getTipo());
			System.out.println("Nombre: "+a.getNombre());
			System.out.println("Capacidad: "+a.getCapacidad());
			System.out.println("Planta: " + a.getPlanta());
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1Incorrecto");
		try {
			Espacio a=new Espacio(TipoEspacio.TEORIA, "A3.10", 0, 3);
			System.out.println(a);
			System.out.println("Tipo: "+a.getTipo());
			System.out.println("Nombre: "+a.getNombre());
			System.out.println("Capacidad: "+a.getCapacidad());
			System.out.println("Planta: " + a.getPlanta());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testSetCapacidad() {
		System.out.println("\ntestSetCapacidadCorrecto");
		try {
			Espacio a=new Espacio(TipoEspacio.TEORIA, "A3.10", 70, 3);
			System.out.println("(1) Capacidad de a: " + a.getCapacidad());
			a.setCapacidad(50);
			System.out.println("(2) Capacidad de a: " + a.getCapacidad());
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\ntestSetCapacidadIncorrecto");
		try {
			Espacio a=new Espacio(TipoEspacio.TEORIA, "A3.10", 70, 3);
			System.out.println("(1) Capacidad de a: " + a.getCapacidad());
			a.setCapacidad(0);
			System.out.println("(2) Capacidad de a: " + a.getCapacidad());
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}

}