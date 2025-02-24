package fp.tipos;

public record Animal(Familia familia, String nombre, 
		Double pesoMedio, Integer edadMedia, 
		Boolean puedeSerDoméstico) implements Comparable<Animal>{
	
	public Animal(Familia familia, String nombre) {
		this(familia,nombre,0d,0,false);
	}
	
	public Animal(Familia familia, String nombre, Boolean puedeSerDoméstico) {
		this(familia,nombre,0d,0,puedeSerDoméstico);
	}
	
	public String toString() {
		return this.nombre + "[" + this.familia + "]";
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Animal) {
			Animal animal = (Animal) o;
			res = this.nombre.equals(animal.nombre) && this.familia.equals(animal.familia);
		}
		return res;
	}
	
	
	public int hashCode() {
		return 11*this.familia.hashCode() + 17*this.nombre.hashCode() + 31*this.pesoMedio.hashCode() 
			   + 51*this.edadMedia.hashCode() + 71*this.puedeSerDoméstico.hashCode();
	}

	
	public int compareTo(Animal o) {
		int res = this.pesoMedio.compareTo(o.pesoMedio);
		
		if (res == 0) {
			res = this.edadMedia.compareTo(o.edadMedia);
		}
		
		if (res == 0) {
			res = this.familia.toString().compareTo(o.familia.toString());
		}
		
		return res;
	}
}
