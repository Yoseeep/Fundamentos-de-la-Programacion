package fp.aeropuerto.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fp.aeropuerto.Persona;

public class TestPersona02 {

	public static void main(String[] args) {
		// 1. Primero creamos 5 personas con los datos:
		Persona p1 = new Persona("FRANCISCO MIGUEL","AARAB ORTIZ","12346678A",LocalDate.of(2005,1,15));
		Persona p2 = new Persona("ALBERTO","AGUILAR RALSTON","13457789B",LocalDate.of(2005,2,15));
		Persona p3 = new Persona("ALVARO","AGUILAR RALSTON","14568900C",LocalDate.of(2005,3,15));
		Persona p4 = new Persona("FRANCISCO MIGUEL","AARAB ORTIZ","12346678A",LocalDate.of(2005,1,15));
		Persona p5 = new Persona("PABLO","ALBA CONRADI","16791122E",LocalDate.of(2005,5,15));
		
		// 2. Crea 3 listas de pasajeros vacías y añadir respectivamente las personas:
		List<Persona> pasaj1 = new LinkedList<Persona>();
		pasaj1.add(p1); 
		pasaj1.add(p2); 
		pasaj1.add(p3);
		
		List<Persona> pasaj2 = new LinkedList<Persona>();
		pasaj2.add(p4);
		pasaj2.add(p5);
		
		List<Persona> pasaj3 = new LinkedList<Persona>();
		pasaj3.add(p1);
		pasaj3.add(p3);
		pasaj3.add(p5);
		
		// 3. Visualiza el número de elementos de las 3 listas.
		System.out.println("\nNúmero de elementos de la lista pasaj1: " + pasaj1.size());
		System.out.println("Número de elementos de la lista pasaj2: " + pasaj2.size());
		System.out.println("Número de elementos de la lista pasaj3: " + pasaj3.size());
		
		// 4. Añade a pasaj1 los elementos de pasaj3
		pasaj1.addAll(pasaj3);
		
		// 5. Visualiza el número de elementos de pasaj1.
		System.out.println("\nNúmero de elementos de la lista pasaj1: " + pasaj1.size());
		
		// 6. Crea un conjunto de pasajeros conj1. y añádele los elementos de pasaj1.
		Set<Persona> conj1 = new HashSet<Persona>();
		conj1.addAll(pasaj1);
		
		// 7. Visualiza el número de elementos de conj1.
		System.out.println("\nNúmeros de elementos del conjunto conj1: " + conj1.size());
		
		// 8. Añade a pasaj2 los elementos de conj1 desde la posición 1.
		pasaj2.addAll(1, conj1);
		
		// 9. Visualiza el número de elementos de pasaj2.
		System.out.println("\nNúmero de elementos de la lista pasaj2: " + pasaj2.size());
		
		// 10. Comprobar si el primer pasajero está en pasaj2
		boolean comprobacion1 = pasaj2.contains(p1);
		System.out.println("¿El pasajero p1 está en pasaj2?: " + comprobacion1);
		
		// 11. Eliminar el primer pasajero de pasaj2.
		pasaj2.remove(0);
		
		// 12. Visualiza el número de elementos de pasaj2
		System.out.println("\nNúmero de elementos de la lista pasaj2: " + pasaj2.size());
		
		// 13. Eliminar el pasajero p4 de pasaj2.
		pasaj2.remove(p4);
		
		// 14. Visualiza el número de elementos de pasaj2
		System.out.println("\nNúmero de elementos de la lista pasaj2: " + pasaj2.size());
		
	}

}
