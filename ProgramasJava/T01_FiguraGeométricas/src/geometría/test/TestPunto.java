package geometría.test;

import geometría.Punto;

public class TestPunto {
	public static void main(String[] args) {
		Punto punto1 = new Punto(1.0,2.0);
		Punto punto2 = new Punto(4.5,6.11);
		
		System.out.println("Punto 1: " + punto1);
		System.out.println("Punto 2: " + punto2);
		
		punto2.setX(4.0);
		punto2.setY(6.0);
		
		System.out.println("Punto 1: " + punto1);
		System.out.println("Punto 2: " + punto2);
		
		System.out.println("Distancia desde el punto1 al punto2: " + punto1.distancia(punto2));
		System.out.println("Distancia desde el punto2 al punto1: " + punto2.distancia(punto1));
		
		Punto puntoOrigen = new Punto();
		
		System.out.println("Punto origen: " + puntoOrigen);
		
		System.out.println("Distancia desde punto1 al origen:" + punto1.distancia(puntoOrigen));
		System.out.println("Distancia desde punto2 al origen:" + punto2.distancia(puntoOrigen));
		
	}
}
