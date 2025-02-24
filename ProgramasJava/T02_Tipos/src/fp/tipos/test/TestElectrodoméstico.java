package fp.tipos.test;


import fp.tipos.Electrodoméstico;

public class TestElectrodoméstico {
	public static void main(String[] args) {
		Electrodoméstico e1 = new Electrodoméstico("1111", "Batidora", 24, 50.0f, 21.0f);
		Electrodoméstico e2 = new Electrodoméstico("2222", "Centro de Planchado", 36, 315.95f, 10.0f);
		
		System.out.println(e1);
		System.out.println(e2);
		
		e2.setIva(21.0f);
		System.out.println(e2.importeTotal());
		String a = "Hola";
		a.startsWith(a);
		System.out.println(a.compareToIgnoreCase(a.toUpperCase()) == 0);
	}
}
