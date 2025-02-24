package fp.aeropuerto;

import java.time.LocalDate;
import java.util.Objects;

import útiles.Checkers;

public record Persona(String dni, String nombre, String apellidos, LocalDate fechaNacimiento)
		implements Comparable<Persona> {

	public Persona {
		Checkers.checkNoNull(dni, nombre, apellidos, fechaNacimiento);
		Checkers.check("La fecha de nacimiento no puede ser mayor que la actual",
				LocalDate.now().isAfter(fechaNacimiento));
	}

	public Persona(String nombre, String apellidos) {
		this("", nombre, apellidos, LocalDate.of(1995, 1, 1));
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Persona))
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public int compareTo(Persona o) {
		return this.dni.compareTo(o.dni);
	}

}
