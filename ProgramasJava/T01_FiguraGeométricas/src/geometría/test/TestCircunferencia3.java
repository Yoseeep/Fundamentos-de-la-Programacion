package geometría.test;

import geometría.CircunferenciaImpl2;
import geometría.Punto;
import geometría.Circunferencia;
import geometría.CircunferenciaImpl1;

public class TestCircunferencia3 {

	public static void main(String[] args) {
		Punto centro = new Punto(2d, 3d);
		Circunferencia c1 = new CircunferenciaImpl2(centro, 1d);
		Circunferencia c2 = new CircunferenciaImpl1(centro, 3.6);
		
		System.out.println(c1);
		System.out.println(c2);
		c1.setRadio(-20d);
		System.out.println(c1);
		System.out.println(c2);

	}

}
/* Se cree una circunferencia c1 con radio 0, por ejemplo, con la
implementación 2 y otra c2 con radio 3.6 con la implementación 1. En el fondo
da igual las implementaciones que usemos
2. Visualizar las dos circunferencias.
3. Se modifica el radio de c1 a -20.
4. Visualizar otra vez las dos circunferencias.*/
