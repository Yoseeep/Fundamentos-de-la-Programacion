package fp.viajes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FactoriaViajes {
	public static AgenciaBus leeAgenciaBus(String nombre, String ruta) {
		AgenciaBus res = null;
		try {
			Stream<Viaje> sv = Files.lines(Path.of(ruta))
					.skip(1).map(FactoriaViajes::parseaViajes);
			
			res = new AgenciaBus(nombre, sv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	public static Viaje parseaViajes(String lineaCSV) {
		String[] camposCSV = lineaCSV.split(";");
		Double precio = Double.valueOf(camposCSV[0].trim());
		Integer distancia = Integer.valueOf(camposCSV[1].trim());
		String[] horasMinutos = camposCSV[2].split(":");
		Integer horas = Integer.valueOf(horasMinutos[0].trim());
		Integer minutos = Integer.valueOf(horasMinutos[1].trim());
		Duration duracion = Duration.ofHours(horas).plus(
							Duration.ofMinutes(minutos));
		TipoViaje tipo = TipoViaje.valueOf(camposCSV[3].trim().toUpperCase());
		String[] preParadas = camposCSV[4].replace("[", "").replace("]", "").split(",");
		List<Parada> paradas = new ArrayList<Parada>();
		for(String parada : preParadas) {
			String[] nombreHora = parada.trim().split("-");
			String nombre = nombreHora[0];
			LocalTime hora = null;
			if( !nombreHora[1].trim().toUpperCase().equals("FIN") ) {
				hora = LocalTime.parse(nombreHora[1],DateTimeFormatter.ofPattern("H:m"));
			}
			paradas.add(new Parada(nombre,hora));
		}
		return new Viaje(precio, distancia, duracion, tipo, paradas);
	}
	
	
}
