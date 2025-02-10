package fp.tipos;

import java.time.LocalDate;

public record Persona(String dni, String nombre, String
		apellidos, LocalDate fechaNacimiento) implements Comparable<Persona> {
	
	public Integer edad() {
		return fechaNacimiento.until(LocalDate.now()).getYears();
	}

	public String toString() {
		return "(" + this.dni + "; " + this.apellidos + "; " + this.nombre + ")";
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Persona) {
			Persona p = (Persona) o;
			res = this.dni.equals(p.dni) && this.nombre.equals(p.nombre) && this.apellidos.equals(p.apellidos);
		}
		return res;
	}
	
	public int hashCode() {
		return 3*this.dni.hashCode() + 11*this.nombre.hashCode() + 23*this.apellidos.hashCode() + 37*this.fechaNacimiento.hashCode();
	}

	
	public int compareTo(Persona o) {
		return this.dni.compareTo(o.dni);
	}
}
