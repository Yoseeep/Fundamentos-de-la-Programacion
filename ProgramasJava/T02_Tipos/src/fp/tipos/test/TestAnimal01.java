package fp.tipos.test;

import fp.tipos.Animal;
import fp.tipos.Familia;

public class TestAnimal01 {

	public static void main(String[] args) {
		
		Animal vacaLoli = new Animal(Familia.TERRESTRE,"vaca" , 510.0, 20, false);
		
		Animal nemo = new Animal(Familia.MARINO, "pez payaso", 0.005, 10, true);
		
		System.out.println("Vaca Loli: " + vacaLoli);
		System.out.println("Nemo: " + nemo);
		
		Animal buitre = new Animal(Familia.MARINO,"Buitre" , 6.5, 15, false);
		System.out.println(buitre);
		System.out.println("buitre vs nemo: " + buitre.equals(nemo));
		System.out.println("buitre vs buitre: " + buitre.equals(buitre));
		System.out.println("hashCode de buitre: " + buitre.hashCode());
		
		
		System.out.println("Comparación " + vacaLoli + " vs " + nemo + " --> " + vacaLoli.compareTo(nemo));
	
		
	}

}
