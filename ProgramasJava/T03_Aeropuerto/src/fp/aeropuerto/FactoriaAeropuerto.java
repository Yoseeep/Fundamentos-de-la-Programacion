package fp.aeropuerto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import útiles.Checkers;

public class FactoriaAeropuerto {
	public static Aeropuerto leerAeropuerto(String nombre, String localidad, String ruta) {
		Aeropuerto res = null;
		
		try {
			Stream<Vuelo> sv = Files.lines(Path.of(ruta)).skip(1).map(FactoriaAeropuerto::parseaVuelo);
			System.out.println("Después de leer");
			res = new Aeropuerto(nombre, localidad,sv);
		} catch (Exception e) {
			System.out.println("No se ha encontrado el fichero " + ruta);
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static List<Persona> parseaPasajeros(String lineaCSV) {
		List<Persona> pasajeros = new LinkedList<Persona>();
		String[] tr = lineaCSV.split("#");
		
		for (String pasajero: tr) {
			String[] tr1 = pasajero.split(",");
			Checkers.check("La cadena de pasajeros está mal troceada: " + pasajero, tr1.length == 4);
			String nombre = tr1[0].trim();
			String apellido = tr1[1].trim();
			String dni = tr1[2].trim();
			LocalDate fechaNacimiento = LocalDate.parse(tr1[3].trim(),DateTimeFormatter.ofPattern("d/M/y"));
			Persona p = new Persona(dni, nombre, apellido, fechaNacimiento);
			pasajeros.add(p);
		}
		return pasajeros;
	}
	
	public static Vuelo parseaVuelo(String lineaCSV) {
		String[] tr = lineaCSV.split(";");
		Checkers.check("El vuelo no esta bien troceado: " + tr, tr.length == 9);
		
		String codigo = tr[0].trim();
		String destino = tr[1].trim();
		LocalDateTime fechaHoraSalida = LocalDateTime.parse(tr[2].trim(), DateTimeFormatter.ofPattern("d/M/y-H:m"));
		Duration duracion = Duration.ofMinutes( Long.valueOf(tr[3].trim()) );
		Double velocidad = Double.valueOf(tr[4].trim());
		Double precio = Double.valueOf(tr[5].trim());
		Integer numeroPlazas = Integer.valueOf(tr[6].trim());
		Boolean conEscala = tr[7].trim().toUpperCase().equals("S");
		List<Persona> pasajeros = parseaPasajeros(tr[8].trim());
		
		Vuelo vuelo = new Vuelo(codigo, destino, fechaHoraSalida, duracion, velocidad, precio, numeroPlazas, conEscala, pasajeros);
		return vuelo;
		
	}
}
