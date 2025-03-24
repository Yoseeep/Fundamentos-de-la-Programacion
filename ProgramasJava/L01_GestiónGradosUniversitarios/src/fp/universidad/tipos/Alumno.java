package fp.universidad.tipos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import utiles.Checkers;

public class Alumno extends Persona{
	private Set<Asignatura> asignaturas;
	private Expediente expediente;
	
	
	
	public Alumno(String DNI, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		super(DNI, nombre, apellidos, fechaNacimiento, email);
		this.asignaturas = new HashSet<Asignatura>();
		this.expediente = new Expediente();
		chequeaEmail(email);
	}
	
	private void chequeaEmail(String email) {
		Checkers.check("El email debe acabar en @alum.us.es", email.endsWith("@alum.us.es"));
	}
	
	public void setEmail(String email) {
		super.setEmail(email);
		chequeaEmail(email);
	}


	// TODO
	public Integer getCurso() {
		return null;
	}
	
	public void matriculaAsignatura(Asignatura a) {
		Checkers.check("El alumno ya está matriculado de esa asigntara", !this.asignaturas.contains(a));
		this.asignaturas.add(a);
	}
	
	public void eliminaAsignatura(Asignatura a) {
		Checkers.check("El alumno no está matriculado de esa asigntara", this.asignaturas.contains(a));
		this.asignaturas.remove(a);
	}
	
	public boolean estaMatriculadoEn(Asignatura a) {
		return this.asignaturas.contains(a);
	}
	
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double valor, Boolean menciónHonor) {
		Checkers.check("El alumno no está matriculado de esa asigntara", this.asignaturas.contains(a));
		Nota aux = new Nota(a, curso, convocatoria, valor, menciónHonor);
		expediente.nuevaNota(aux);
	}
	
	public String toString() {
		return "(?) " + super.toString();
	}
	

}
