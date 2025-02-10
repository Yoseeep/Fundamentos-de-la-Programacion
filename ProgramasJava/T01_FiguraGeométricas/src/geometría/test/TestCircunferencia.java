package geometría.test;

import geometría.CircunferenciaImpl1;
import geometría.CircunferenciaImpl2;
import geometría.Punto;

public class TestCircunferencia {
	public static void main(String[] args) {
		Punto centro = new Punto(1.0,2.0);
		CircunferenciaImpl2 miCircunferencia = new CircunferenciaImpl2(centro, 2.5);
		
		System.out.println("miCircunferencia: " + miCircunferencia);
		System.out.println("Longitud: " + miCircunferencia.longitud());
		System.out.println("Área: " + miCircunferencia.área());
		
	}
}
