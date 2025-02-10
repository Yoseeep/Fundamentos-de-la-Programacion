package fp.tipos.test;

import java.time.LocalDate;
import fp.tipos.Persona;

public class TestPersona {

	public static void main(String[] args) {
		Persona yo = new Persona("12345678A", "José Manuel", "García", LocalDate.of(2003, 7, 15));
		System.out.println(yo);
		System.out.println(yo.nombre());
		System.out.println(yo.edad());
		
		
		Persona persona1 = new Persona("40681394T", "Manoli", "García", LocalDate.of(1990, 3, 21));
		Persona persona2 = new Persona("40681394T", "Manoli", "García", LocalDate.of(1990, 3, 21));
		Persona persona3 = new Persona("81785023W", "Juani", "López", LocalDate.of(2025, 1, 2));
		System.out.println("Persona 1: " + persona1);
		System.out.println("persona1 vs persona2: " + persona1.equals(persona2));
		System.out.println("persona1 vs persona3: " + persona1.equals(persona3));
		System.out.println("hashCode persona1: " + persona1.hashCode());
		
		System.out.println("Compara " + persona1 + " vs " + persona2 + " --> " + persona1.compareTo(persona2));
		System.out.println("Compara " + persona1 + " vs " + persona3 + " --> " + persona1.compareTo(persona3));
		System.out.println("Compara " + persona3 + " vs " + persona2 + " --> " + persona3.compareTo(persona2));
	}

}
