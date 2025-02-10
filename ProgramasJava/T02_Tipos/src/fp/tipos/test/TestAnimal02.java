package fp.tipos.test;

import fp.tipos.Animal;
import fp.tipos.Familia;

public class TestAnimal02 {

	public static void main(String[] args) {
		
		Animal vacaLoli = new Animal(Familia.TERRESTRE,"vaca");
		
		Animal nemo = new Animal(Familia.MARINO, "pez payaso", true);
		
		System.out.println("Vaca Loli: " + vacaLoli);
		System.out.println("Nemo: " + nemo);
	}

}
