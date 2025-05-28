package fp.examenes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FactoriaExamenes {
	public static CalendarioExamenes leeExamen(String ruta){
		CalendarioExamenes res = null;
		try {
			Stream<Examen> se = Files.lines(Path.of(ruta)).skip(1)
					.map(FactoriaExamenes::parsearExamen);
			res = new CalendarioExamenes(se);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	
	
	public static Examen parsearExamen(String lineaCSV) {
		String[] camposCSV = lineaCSV.split(",");
		String asignatura = camposCSV[0].trim();
		Integer curso = Integer.valueOf( camposCSV[1].trim() );
		LocalDate fecha = LocalDate.parse(camposCSV[2].trim(),DateTimeFormatter.ofPattern("d/M/y"));
		LocalTime hora = LocalTime.parse(camposCSV[3].trim(),DateTimeFormatter.ofPattern("H:m"));
		LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
		Duration duracion = Duration.ofMinutes( Integer.valueOf(camposCSV[4].trim()) );
		TipoExamen tipo = TipoExamen.valueOf(camposCSV[5].trim().toUpperCase());
		Integer asistentes = Integer.valueOf( camposCSV[6].trim() );
		Boolean inscripcion = camposCSV[7].trim().equals("Si") ? true : false;
		String[] aulasPrevio = camposCSV[8].split(";");
		List<Aula> aulas = new ArrayList<Aula>();
		for (String lineaAula : aulasPrevio) {
			String nombre = lineaAula.split("-")[0].trim();
			Integer capacidad = Integer.valueOf( lineaAula.split("-")[1].trim() );
			aulas.add(new Aula(nombre, capacidad));
		}
		return new Examen(asignatura, curso, fechaHora, duracion, tipo, asistentes, inscripcion, aulas);
	}
}
