package geometría.test;

import geometría.Punto;
import geometría.Circunferencia;
import geometría.CircunferenciaImpl2;
import geometría.CircunferenciaImpl1;

public class TestCircunferencia4 {
	public static void main(String[] args) {
		testConstructor();
		testSetRadio();
	}

	private static void testConstructor() {
		Punto centro = new Punto(1.0, -1.0);
		System.out.println("\nTEST del Constructor correcto");
		try {
			Circunferencia c = new CircunferenciaImpl2(centro, 3.0);
			System.out.println("c: " + c);
		} catch (Exception e) {
			System.out.println("Excepción capturada:\n " + e);
		}
		System.out.println("\nTEST del Constructor radio 0");
		try {
			Circunferencia c = new CircunferenciaImpl2(centro, 0d);
			System.out.println("c: " + c);
		} catch (Exception e) {
			System.out.println("Excepción capturada:\n " + e);
		}
		System.out.println("\nTEST del Constructor radio negativo");
		try {
			Circunferencia c = new CircunferenciaImpl2(centro, -2.5);
			System.out.println("c: " + c);
		} catch (Exception e) {
			System.out.println("Excepción capturada:\n " + e);
		}
	}

	private static void testSetRadio() {
		Punto centro = new Punto(1.0, -1.0);
		Circunferencia c = new CircunferenciaImpl1(centro, 3.0);
		System.out.println("\nTEST setRadio con radio correcto");
		try {
			c.setRadio(10d);
			System.out.println("c: " + c);
		} catch (Exception e) {
			System.out.println("Excepción capturada:\n " + e);
		}
		System.out.println("\nTEST setRadio con radio cero");
		try {
			c.setRadio(0d);
			System.out.println("c: " + c);
		} catch (Exception e) {
			System.out.println("Excepción capturada:\n " + e);
		}
		System.out.println("\nTEST setRadio con radio negativo");
		try {
			c.setRadio(-20d);
			System.out.println("c: " + c);
		} catch (Exception e) {
			System.out.println("Excepción capturada:\n " + e);
		}
	}
}
