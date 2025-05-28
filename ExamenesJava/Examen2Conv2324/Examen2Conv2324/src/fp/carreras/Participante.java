package fp.carreras;

import java.time.Duration;

import fp.utiles.Checkers;

public record Participante(String id, String nombre, String apellidos,
		Integer edad, Sexo sexo, Duration duracion, Categoria categoria) {
	
	public Participante{
		Checkers.check("La edad del participante debe ser igual o superior a 17 años",
				edad >= 17);
	}
	
}
