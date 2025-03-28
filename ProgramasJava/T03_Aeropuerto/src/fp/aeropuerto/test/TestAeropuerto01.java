package fp.aeropuerto.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fp.aeropuerto.Aeropuerto;
import fp.aeropuerto.Persona;
import fp.aeropuerto.Vuelo;

public class TestAeropuerto01 {

	public static void main(String[] args) {
		// Construimos vuelo 1
		List<Persona> pasajeros=new ArrayList<Persona>();
		Persona p1=new Persona("12345678A","Manolito","Gafotas",LocalDate.of(2000, 1, 15));
		Persona p2=new Persona("12345678B","Harry","Potter",LocalDate.of(2005, 7, 31));
		Persona p3=new Persona("12345678C","Albert","Einstein",LocalDate.of(1879, 3, 14));
		pasajeros.add(p1); pasajeros.add(p2);pasajeros.add(p3);
		Vuelo v1=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),Duration.ofMinutes(115),890.5,150d,5,false,pasajeros);
		
		// Construimos vuelo 2
		Persona p4=new Persona("20502507P","Jose M.","Garcia",LocalDate.of(2003, 7, 15));
		pasajeros.add(p4);
		Vuelo v2=new Vuelo("IBE-002","Sevilla",LocalDateTime.of(2024,7,3,5,8),Duration.ofMinutes(180),940.0,120d,10,true,pasajeros);
		
		// Construimos una lista de vuelos
		List<Vuelo> vuelos = new LinkedList<Vuelo>();
		vuelos.add(v1); vuelos.add(v2);
		
		// Construimos un aeropuerto
		Aeropuerto aeropuerto = new Aeropuerto("San Pablo", "Sevilla", vuelos);
		
		// Representamos
		System.out.println("Representacion aeropuerto: " + aeropuerto);
		System.out.println("Representacion lista de vuelos del aeropuerto: " + aeropuerto.getVuelos());
		
		
	}

}
