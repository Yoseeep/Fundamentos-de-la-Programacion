package fp.universidad.tipos.test;

import java.time.LocalDate;

import fp.universidad.tipos.Persona;

public class TestPersona02 {

	public static void main(String[] args) {
		testConstructor1();
		testConstructor2();
		testSetDni();
		TestSetEmail();

	}
	
	private static void testConstructor1() {
		System.out.println("\ntestConstructor1 Correcto");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1 DNI incorrecto (menos de 9)");
		try {
			Persona p=new Persona("4321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1 DNI incorrecto (sin letra)");
		try {
			Persona p=new Persona("123456789","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1 email incorrecto (sin @)");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez.alum.us.es");
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor1 email incorrecto (con más de una @)");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez@gonzalez@alum.us.es");
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testConstructor2() {
		System.out.println("\ntestConstructor2 Correcto");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2));
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor2 DNI incorrecto (menos de 9)");
		try {
			Persona p=new Persona("4321Z","Manuel","Gómez González",LocalDate.of(2002,4,2));
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructor2 DNI incorrecto (sin letra)");
		try {
			Persona p=new Persona("123456789","Manuel","Gómez González",LocalDate.of(2002,4,2));
			System.out.println(p);
			System.out.println("Email de p: "+p.getEmail());
			System.out.println("Edad de p: "+p.getEdad());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testSetDni() {
		System.out.println("\ntestSetDni Correcto");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println("(1) DNI de p: "+p.getDNI());
			p.setDNI("12345678A");
			System.out.println("(2) DNI de p: "+p.getDNI());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestSetDni Incorrecto (menos de 9)");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println("(1) DNI de p: "+p.getDNI());
			p.setDNI("12378A");
			System.out.println("(2) DNI de p: "+p.getDNI());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestSetDni Incorrecto (sin letra)");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println("(1) DNI de p: "+p.getDNI());
			p.setDNI("123456789");
			System.out.println("(2) DNI de p: "+p.getDNI());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void TestSetEmail() {
		System.out.println("\ntestSetEmail Correcto");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println("(1) Email de p: "+p.getEmail());
			p.setEmail("jmgv.ciencia@alum.us.es");
			System.out.println("(2) Email de p: "+p.getEmail());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestSetEmail Correcto (es una cadena vacía)");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println("(1) Email de p: "+p.getEmail());
			p.setEmail("");
			System.out.println("(2) Email de p: "+p.getEmail());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestSetEmail Incorrecto (sin @)");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println("(1) Email de p: "+p.getEmail());
			p.setEmail("jmgv.ciencia.alum.us.es");
			System.out.println("(2) Email de p: "+p.getEmail());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestSetEmail Incorrecto (con más de un @)");
		try {
			Persona p=new Persona("87654321Z","Manuel","Gómez González",LocalDate.of(2002,4,2),"gomez.gonzalez@alum.us.es");
			System.out.println("(1) Email de p: "+p.getEmail());
			p.setEmail("jmgv@ciencia@alum.us.es");
			System.out.println("(2) Email de p: "+p.getEmail());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
