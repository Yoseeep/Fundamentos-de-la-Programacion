package fp.universidad.tipos;

import java.util.Objects;

import utiles.Checkers;

public record Nota(Asignatura asignatura, Integer curso, Convocatoria convocatoria, Double valor, Boolean mencionHonor) implements Comparable<Nota>{
	
	public Nota{
		Checkers.check("La calificacion debe estar entre 0 y 10", 0 <= valor && valor <= 10);
		Checkers.check("Una nota sólo puede tener mención de honor si su valor numérico es igual o superior a 9.",
					   mencionHonor? valor>=9: true);
	}
	
	public Nota(Asignatura asignatura, Integer curso, Convocatoria convocatoria, Double valor) {
		this(asignatura, curso, convocatoria, valor, false);
	}
	
	public Calificacion calificacion() {
		Calificacion res = Calificacion.SUSPENSO;
		
		if (valor >= 9 && mencionHonor) {
			res = Calificacion.MATRICULA_DE_HONOR;
		}
		if (valor >= 9 && !(mencionHonor)) {
			res = Calificacion.SOBRESALIENTE;
		}
		if (7 <= valor && valor < 9) {
			res = Calificacion.NOTABLE;
		}
		if (5 <= valor && valor < 7) {
			res = Calificacion.APROBADO;
		}
		
		return res;
	}
	
	
	private String cursoParseado() {
		String ultimasCifras = this.curso.toString();
		ultimasCifras = ultimasCifras.substring(ultimasCifras.length()-2);
		return this.curso + "-" + (Integer.parseInt(ultimasCifras)+1);
	}
	
	public String toString() {
		return this.asignatura + ", " + this.cursoParseado() + ", " + 
			   this.convocatoria + ", " + this.valor + ", " + this.calificacion(); 
	}

	@Override
	public int hashCode() {
		return Objects.hash(asignatura, convocatoria, curso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Nota))
			return false;
		Nota other = (Nota) obj;
		return Objects.equals(asignatura, other.asignatura) && convocatoria == other.convocatoria
				&& Objects.equals(curso, other.curso);
	}
	
	public int compareTo(Nota n) {
		int res = this.curso.compareTo(n.curso);
		if (res == 0) {
			res = this.asignatura.compareTo(n.asignatura);
			if (res == 0) {
				res = this.convocatoria.compareTo(n.convocatoria);
			}
		}
		return res;
	}
}
