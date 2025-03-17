package fp.aeropuerto.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import fp.aeropuerto.Persona;
import fp.aeropuerto.Vuelo;

public class TestVuelo01 {

	public static void main(String[] args) {
		testConstructor01();
		//testConstructor02();
	}
	
		
	//Prueba el constructor-1 con todos los parámetros
	private static void testConstructor01() {
		//Lista con 3 pasajeros para probar los métodos de vuelo
		List<Persona> pasajeros=new ArrayList<Persona>();
		Persona p1=new Persona("12345678A","Manolito","Gafotas",LocalDate.of(2000, 1, 15));
		Persona p2=new Persona("12345678B","Harry","Potter",LocalDate.of(2005, 7, 31));
		Persona p3=new Persona("","Albert","Einstein",LocalDate.of(1879, 3, 14));
		pasajeros.add(p1); pasajeros.add(p2);pasajeros.add(p3);
	
		try { 
			System.out.println("\nTest constructor-1 con todos los parámetros");
			//Construya un vuelo "v" con los siguientes datos:
			//código=IBE-001, destino=Valencia, fecha y hora de salida= 1 de agosto de 2023 a las 9:00
			//fechay hora de llegada= el 1 de agosto de 2023 a las 10:55 y con la lista de pasajeros anteriores
			Vuelo v=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),890.5,150d,5,false,pasajeros);
			
			System.out.println("\t"+v);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
		try {
			System.out.println("\nTest constructor-1 con algún parámetro nulo");
			Vuelo v=new Vuelo(null,"Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),890.5,150d,5,false,null);
			System.out.println("\t"+v);
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
		try { 
			System.out.println("\nTest constructor-1 con el código malo");
			Vuelo v=new Vuelo("IBE001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),890.5,150d,5,false,pasajeros);
			System.out.println("\t"+v);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
		try { 
			System.out.println("\nTest constructor-1 con la duración negativa");
			Vuelo v=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(-115),890.5,150d,5,false,pasajeros);
			System.out.println("\t"+v);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
		try { 
			System.out.println("\nTest constructor-1 con la velocidad negativa");
			Vuelo v=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),-890.5,150d,5,false,pasajeros);
			System.out.println("\t"+v);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
		try {
			System.out.println("\nTest constructor-1 con el precio negativo");
			Vuelo v=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),890.5,-150d,5,false,pasajeros);
			System.out.println("\t"+v);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
		try {
			System.out.println("\nTest constructor-1 con las plazas negativas");
			Vuelo v=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),890.5,150d,-5,false,pasajeros);
			System.out.println("\t"+v);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
		try {
			System.out.println("\nTest constructor-1 con las plazas menores que el número de pasajeros");
			Vuelo v=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),890.5,150d,1,false,pasajeros);
			System.out.println("\t"+v);
			System.out.println("\tConstruido correctamente!!");
		}catch(Exception e) {
			System.out.println("\tHa habido un error al construir el vuelo");
			System.out.println("\t"+e);
		}
	}
}
