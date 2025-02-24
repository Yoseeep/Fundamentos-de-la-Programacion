package fp.universidad.tipos;

public record Nota(Asignatura asignatura, Integer curso, Convocatoria convocatoria, Double valor, Boolean mencionHonor) {
	
	public Nota{
		if (valor < 0 && valor > 10) {
			throw new IllegalArgumentException("El valor de la nota debe estar comprendido en [0,10].");
		}
		
		if (mencionHonor && valor<9) {
			throw new IllegalArgumentException("No se puede tener mención de honor con un valor menor que 9.");
		}
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
}
