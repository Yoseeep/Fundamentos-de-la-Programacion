package fp.universidad.tipos;

import java.util.HashSet;
import java.util.Set;

import utiles.Checkers;

public class Despacho extends Espacio{
	private Set<Profesor> profesores;
	
	public Despacho(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = Set.copyOf(profesores);
		chequeaCapacidadDespacho(capacidad, profesores);
	}
	
	public Despacho(String nombre, Integer capacidad, Integer planta, Profesor profesor) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = new HashSet<Profesor>();
		profesores.add(profesor);
		chequeaCapacidadDespacho(capacidad, profesores);
	}
	
	public Despacho(String nombre, Integer capacidad, Integer planta) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = new HashSet<Profesor>();
		chequeaCapacidadDespacho(capacidad, profesores);
	}

	
	private void chequeaCapacidadDespacho(Integer capacidad, Set<Profesor> profesores) {
		Checkers.check("El numero de profesores excede la capacidad del despacho",
				   capacidad <= profesores.size());
	}
	
	
	public void setProfesores(Set<Profesor> profesores) {
		chequeaCapacidadDespacho(super.getCapacidad(), profesores);
		this.profesores = Set.copyOf(profesores);
	}
	
	
	public void setTipo(TipoEspacio tipo) {
		if (tipo != TipoEspacio.OTRO) {
			throw new UnsupportedOperationException("El tipo de despacho debe ser OTRO");
		}
	}
	
	
	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public String toString() {
		return super.toString() + " " + profesores;
	}
	
	
	
	
	
	
	
	
}
