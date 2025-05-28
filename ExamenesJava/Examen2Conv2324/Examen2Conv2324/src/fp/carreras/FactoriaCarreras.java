package fp.carreras;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactoriaCarreras {
	
	public static Carreras leeCarreras(String nomfichCarreras, String nomfichParticipantes) {
		Carreras res = null;
		
		try {
			
			Stream<Carrera> sc = Files.lines(Path.of(nomfichCarreras)).skip(1)
					.map(FactoriaCarreras::parseaCarrera);
			res = new Carreras(sc);
			
			Map<String, List<Participante>> participantes = FactoriaCarreras
					.leeParticipantes(nomfichParticipantes);
			
			res.getCarreras().stream()
				.forEach(c -> c.añadeParticipantes( participantes.get(c.getId()) ));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	private static Carrera parseaCarrera(String lineaCarrera) {
		String[] camposCarrera = lineaCarrera.split(",");
		String id = camposCarrera[0].trim();
		String localidad = camposCarrera[1].trim();
		LocalDateTime fechaHora = LocalDateTime.parse(
				camposCarrera[2].trim(),
				DateTimeFormatter.ofPattern("y-M-d H:m")
				);
		Modalidad modalidad = Modalidad.valueOf(
				camposCarrera[3].trim().toUpperCase()
				);
		Integer distancia = Integer.valueOf(camposCarrera[4]);
		Integer desnivel = Integer.valueOf(camposCarrera[5]);
		return new Carrera(id, localidad, fechaHora, modalidad, distancia, desnivel);
	}
	
	
	public static Map<String, List<Participante>> leeParticipantes(String nomfichParticipantes){
		Map<String, List<Participante>> participantes = null;
		
		try {
			participantes = Files.lines(Path.of(nomfichParticipantes)).skip(1)
					.map(FactoriaCarreras::parseaParticipante)
					.collect(Collectors.groupingBy(Participante::id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return participantes;
	}
	
	private static Participante parseaParticipante(String lineaParticipante) {
		String[] camposParticipante = lineaParticipante.split(",");
		String id = camposParticipante[0].trim();
		String nombre = camposParticipante[1].trim();
		String apellidos = camposParticipante[2].trim();
		Integer edad = Integer.valueOf( camposParticipante[3].trim() );
		Sexo sexo = Sexo.valueOf( camposParticipante[4].trim().toUpperCase() );
		Duration duracion = Duration.parse(camposParticipante[5]);
		Categoria categoria = null;
		if (17 <= edad && edad <= 20){
			categoria = sexo.equals(Sexo.HOMBRE) ? Categoria.JUNIOR_M : Categoria.JUNIOR_F;
		}
		if (21 <= edad && edad <= 23){
			categoria = sexo.equals(Sexo.HOMBRE) ? Categoria.PROMESA_M : Categoria.PROMESA_F;
		}
		if (24 <= edad && edad <= 39){
			categoria = sexo.equals(Sexo.HOMBRE) ? Categoria.SENIOR_M : Categoria.SENIOR_F;
		}
		if (edad >= 40){
			categoria = sexo.equals(Sexo.HOMBRE) ? Categoria.VETERANO_M : Categoria.VETEREANO_F;
		}
		return new Participante(id, nombre, apellidos, edad, sexo, duracion, categoria);
	}
	
}
