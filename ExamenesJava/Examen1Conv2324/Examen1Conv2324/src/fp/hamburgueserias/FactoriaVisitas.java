package fp.hamburgueserias;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FactoriaVisitas {
	
	public static Competicion leeVisitas(String ruta) {
		Competicion res = null;
		
		try {
			Stream<Visita> sv = Files.lines(Path.of(ruta)).skip(1)
						.map(FactoriaVisitas::parseaVisita);
			res = new Competicion(sv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;		
	}
	
	
	
	public static Visita parseaVisita(String lineaCSV) {
		String[] camposCSV = lineaCSV.split(";");
		String email = camposCSV[0].trim();
		String ciudad = camposCSV[1].trim();
		String codigoPostal = camposCSV[2].trim();
		LocalDateTime entrada = LocalDateTime.parse(camposCSV[3], 
				DateTimeFormatter.ofPattern("d/M/y H:m"));
		Double temperatura = Double.valueOf(camposCSV[4]);
		LocalDateTime salida = LocalDateTime.parse(camposCSV[5], 
				DateTimeFormatter.ofPattern("d/M/y H:m"));
		String[] preEvaluaciones = camposCSV[6].replace("[", "").replace("]", "")
											   .split(" - ");
		List<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
		for (String evaluacion : preEvaluaciones) {
			evaluaciones.add( parseaEvaluacion(evaluacion) );
		}
		return new Visita(email, ciudad, codigoPostal, entrada, salida, 
						  temperatura, evaluaciones);
					
 	}
	
	private static Evaluacion parseaEvaluacion(String lineaEvaluacion) {
		String[] camposEvaluacion = lineaEvaluacion.split(":");
		String hamburgueseria = camposEvaluacion[0].trim();
		String[] puntuaciones = camposEvaluacion[1].trim()
								.replace("(", "").replace(")", "")
								.split(",");
		Integer presentacion = Integer.valueOf( puntuaciones[0].trim() );
		Integer puntoCarne = Integer.valueOf( puntuaciones[1].trim() );
		Integer calidadIngredientes = Integer.valueOf( puntuaciones[2].trim() );
		Integer calidadPan = Integer.valueOf( puntuaciones[3].trim() );
		
		return new Evaluacion(hamburgueseria, presentacion, puntoCarne, 
							  calidadIngredientes, calidadPan);
	}
	
	
	
	
}
