package fp.examenes;

import java.util.Objects;

import fp.utiles.Checkers;

public record Aula(String nombre, Integer capacidad) {

	public Aula {
		Checkers.check("El nombre debe comenzar por una letra", 
				Character.isAlphabetic(nombre.charAt(0)) );
		Checkers.check("La capacidad debe ser mayor que cero", capacidad > 0);
	}
	
	public int hashCode() {
		return Objects.hash(capacidad, nombre);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(capacidad, other.capacidad) && Objects.equals(nombre, other.nombre);
	}
	
}
