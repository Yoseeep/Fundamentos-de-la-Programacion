package fp.universidad.tipos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Departamento implements Comparable<Departamento> {
	private String nombre;
	private Set<Profesor> profesores;
	private Set<Asignatura> asignaturas;
	
	
	public Set<Profesor> getProfesores() {
		return profesores;
	}
	public Set<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	
	
	public void nuevoProfesor(Profesor profesor) {
		this.profesores.add(profesor);
	}
	
	public void eliminaProfesor(Profesor profesor) {
		this.profesores.remove(profesor);
	}
	
	public void nuevaAsignatura(Asignatura asignatura) {
		this.asignaturas.add(asignatura);
	}
	
	public void eliminaAsignatura(Asignatura asignatura) {
		this.asignaturas.remove(asignatura);
	}
	
	
	public Departamento(String nombre) {
		this.nombre = nombre;
		this.profesores = new HashSet<Profesor>();
		this.asignaturas = new HashSet<Asignatura>();
	}
	
	

	public int hashCode() {
		return Objects.hash(nombre);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Departamento))
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	public int compareTo(Departamento o) {
		return this.nombre.compareTo(o.nombre);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public void borraTutorias() {
		for (Profesor p : this.profesores) {
			p.borraTutorias();
		}
	}
	
	public void borraTutorias(Categoría c) {
		for (Profesor p : this.profesores) {
			if (p.getCategoría().equals(c)) {
				p.borraTutorias();
			}
		}
	
	}
	
}
