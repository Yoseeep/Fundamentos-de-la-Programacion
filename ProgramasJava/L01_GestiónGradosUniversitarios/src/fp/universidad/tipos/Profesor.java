package fp.universidad.tipos;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.SortedSet;
import java.util.TreeSet;

import utiles.Checkers;

public class Profesor extends Persona{
	private Categoría categoría;
	private SortedSet<Tutoria> tutorías;
	
	public Profesor(String DNI, String nombre, String apellidos, LocalDate fechaNacimiento, String email,
			Categoría categoría) {
		super(DNI, nombre, apellidos, fechaNacimiento, email);
		this.categoría = categoría;
		this.tutorías = new TreeSet<Tutoria>();
		chequeaEdad();
	}
	
	private void chequeaEdad() {
		Checkers.check("El profesor debe ser mayor de edad", super.getEdad() >= 18);
	}
	
	public String toString() {
		return super.toString() + " (" + this.categoría + ")";
	}
	
	public void nuevaTutoria(LocalTime horaComienzo, Duration duracion, DayOfWeek diaSemana) {
		Tutoria tutoria = new Tutoria(diaSemana, horaComienzo, duracion);
		this.tutorías.add(tutoria);
	}
	
	public void borraTutoria(LocalTime horaComienzo, DayOfWeek diaSemana) {
		Tutoria aux = new Tutoria(diaSemana, horaComienzo, Duration.ofMinutes(15));
		this.tutorías.remove(aux);
	}
	
	public Categoría getCategoría() {
		return categoría;
	}

	public SortedSet<Tutoria> getTutorías() {
		return new TreeSet<Tutoria>(tutorías);
	}

	public void borraTutorias() {
		tutorías.clear();
	}
	
	
	
}
