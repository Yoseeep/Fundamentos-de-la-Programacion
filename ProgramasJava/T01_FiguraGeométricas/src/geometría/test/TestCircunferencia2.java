package geometría.test;

import geometría.Circunferencia;
import geometría.CircunferenciaImpl2;
import geometría.CircunferenciaImpl3;
import geometría.CircunferenciaImpl4;
import geometría.CircunferenciaImpl1;
import geometría.Punto;

public class TestCircunferencia2 {
	public static void main(String[] args) {
		Punto centro1 = new Punto(1.0,2.0);
		Circunferencia miCircunferencia1 = new CircunferenciaImpl1(centro1, 2.5);
		System.out.println("miCircunferencia1: " + miCircunferencia1);
		System.out.println("Longitud1: " + miCircunferencia1.longitud());
		System.out.println("Área1: " + miCircunferencia1.área());
		
		
		Punto centro2 = new Punto(1.0,2.0);
		Circunferencia miCircunferencia2 = new CircunferenciaImpl2(centro2, 2.5);
		System.out.println("miCircunferencia2: " + miCircunferencia2);
		System.out.println("Longitud2: " + miCircunferencia2.longitud());
		System.out.println("Área2: " + miCircunferencia2.área());
		
		
		Punto centro3 = new Punto(1.0,2.0);
		Circunferencia miCircunferencia3 = new CircunferenciaImpl3(centro3, 2.5);
		System.out.println("miCircunferencia3: " + miCircunferencia3);
		System.out.println("Longitud3: " + miCircunferencia3.longitud());
		System.out.println("Área3: " + miCircunferencia3.área());
		
		
		Punto centro4 = new Punto(1.0,2.0);
		Circunferencia miCircunferencia4 = new CircunferenciaImpl4(centro1, 2.5);
		System.out.println("miCircunferencia4: " + miCircunferencia4);
		System.out.println("Longitud4: " + miCircunferencia4.longitud());
		System.out.println("Área4: " + miCircunferencia4.área());
	}
}
