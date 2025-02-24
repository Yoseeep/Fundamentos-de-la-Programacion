package fp.universidad.tipos;

public record Asignatura(String nombre, String codigo, 
		Double creditos, TipoAsignatura tipo, Integer curso) {

	public String toString() {
		return "(" + this.codigo + ") " + this.nombre;
	}
	
	//TODO
	public String acronimo() {
		return null;
		
	}
	
}
