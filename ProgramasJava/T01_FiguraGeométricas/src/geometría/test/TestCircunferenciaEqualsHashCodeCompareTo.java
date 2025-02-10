package geometría.test;

import geometría.CircunferenciaImpl1;
import geometría.CircunferenciaImpl2;
import geometría.Punto;
import geometría.Circunferencia;

public class TestCircunferenciaEqualsHashCodeCompareTo {

	public static void main(String[] args) {
		Punto centro1 = new Punto(1.0,2.0);
		Punto centro2 = new Punto(5.0,2.0);
		
		Circunferencia c1 = new CircunferenciaImpl1(centro1, 5.0); 
		Circunferencia c2 = new CircunferenciaImpl2(centro1, 5.0);
		Circunferencia c3 = new CircunferenciaImpl1(centro1, 2.0);
		Circunferencia c4 = new CircunferenciaImpl1(centro2, 5.0);
		System.out.println("c1.equals(c2): " + c1.equals(c2));
		System.out.println("c1.equals(c3): " + c1.equals(c3));
		System.out.println("c2.equals(c4): " + c2.equals(c4));
		System.out.println("c4.equals(c3): " + c4.equals(c3));
		
		System.out.println("hashCode de c1: " + c1.hashCode());
		System.out.println("hashCode de c2: " + c2.hashCode());
		System.out.println("hashCode de c3: " + c3.hashCode());
		System.out.println("hashCode de c4: " + c4.hashCode());
		
		System.out.println("Comparación " +  c1 + " vs " + c2 + " --> " + c1.compareTo(c2));
		System.out.println("Comparación " +  c1 + " vs " + c4 + " --> " + c1.compareTo(c4));
		System.out.println("Comparación " +  c1 + " vs " + c3 + " --> " + c1.compareTo(c3));
		System.out.println("Comparación " +  c3 + " vs " + c1 + " --> " + c3.compareTo(c1));
	}

}
