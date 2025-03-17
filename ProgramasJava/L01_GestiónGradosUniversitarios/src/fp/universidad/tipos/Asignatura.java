package fp.universidad.tipos;

import java.util.Objects;

import utiles.Checkers;

public record Asignatura(String nombre, String codigo, 
		Double creditos, TipoAsignatura tipo, Integer curso) implements Comparable<Asignatura>{
	
	public Asignatura{
		Checkers.check("Codigo no valido", compruebaCodigo(codigo));
		Checkers.check("El numero de creditos debe ser mayor que 0", ((double) creditos > 0));
		Checkers.check("El curso debe estar entre 1 y 4 años", 1 <= curso.intValue() && curso.intValue() <= 4);
	}
	
	private boolean compruebaCodigo(String codigo) {
		if (codigo.length()!=7) {
			return false;
		}
		
		for (char d: codigo.toCharArray()) {
			if (!Character.isDigit(d)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public String toString() {
		return "(" + this.codigo + ") " + this.nombre;
	}
	
	//TODO
	public String acronimo() {
		return null;
		
	}

	public int hashCode() {
		return Objects.hash(codigo);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Asignatura))
			return false;
		Asignatura other = (Asignatura) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	public int compareTo(Asignatura a) {
		return this.codigo.compareTo(a.codigo);
	}
	
}
