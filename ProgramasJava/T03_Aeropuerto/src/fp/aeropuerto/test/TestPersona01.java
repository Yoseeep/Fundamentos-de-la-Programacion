package fp.aeropuerto.test;

import java.time.LocalDate;

import fp.aeropuerto.Persona;

public class TestPersona01 {

	public static void main(String[] args) {
		testConstructor01();
		testConstructor02();
		
	}
	
		
	//Prueba el constructor-1 con todos los parámetros
	private static void testConstructor01() {
		try {
			System.out.println("Test constructor-1 con todos los parámetros");
			//Costruya a continuación una persona "p" con los siguientes datos:
			//dni=12345678A, nombre=Manolito, apellidos=Gafotas, fecha de nacimiento=15/1/2000
			Persona p = new Persona("12345678A","Manolito", "Gafotas", LocalDate.of(2000, 1, 15));
			
			System.out.println("\t"+p);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir la persona");
			System.out.println("\t"+e);
		}
		try {
			System.out.println("Test constructor-1 con la fecha errónea");
			//Costruya a continuación una persona "p" con los siguientes datos:
			//dni=12345678A, nombre=Manolito, apellidos=Gafotas, fecha de nacimiento=15/1/2030
			Persona p = new Persona("12345678A","Manolito", "Gafotas", LocalDate.of(2030, 1, 15));
			
			System.out.println("\t"+p);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir la persona");
			System.out.println("\t"+e);
		}
		try {
			System.out.println("\nTest constructor-1 con todos los parámetros nulos");
			//Costruya a continuación una persona "p" con todos los parámetros nulos
			Persona p = new Persona(null,null,null,null);
			
			System.out.println("\t"+p);
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir la persona");
			System.out.println("\t"+e);
		}
	}
	//Prueba el constructor-2 que tiene sólo tiene nombre y apellidos 	
	private static void testConstructor02() {
		try {
			System.out.println("\nTest constructor-2 con apellidos y nombre");
			//Costruya a continuación una persona "p" con los siguientes datos:
			//nombre=Manolito, apellidos=Gafotas
			Persona p = new Persona("Manolito","Gafotas");
			
			System.out.println("\t"+p);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir la persona");
			System.out.println("\t"+e);
		}
		try {
			System.out.println("\nTest constructor-2 con parámetros nulos");
			Persona p = new Persona(null,"Gafotas");
			System.out.println("\t"+p);
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir la persona");
			System.out.println("\t"+e);
		}
	}

}
