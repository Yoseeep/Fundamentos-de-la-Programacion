package fp.examenes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class Examen implements Comparable<Examen>{
	private String asignatura;
	private Integer curso;
	private LocalDateTime fechaHora;
	private Duration duracion;
	private TipoExamen tipo;
	private Integer asistentes;
	private Boolean inscripcion;
	private List<Aula> aulas;
	
	public Examen(String asignatura, Integer curso, LocalDateTime fechaHora, Duration duracion, 
			TipoExamen tipo, Integer asistentes, Boolean inscripcion, List<Aula> aulas) {
		Checkers.check("El número de asistentes debe ser mayor que 0", asistentes >= 0);
		Checkers.check("La duración debe ser como mínimo de una hora", 
				duracion.compareTo(Duration.ofHours(1)) >= 0);
		this.asignatura = asignatura;
		this.curso = curso;
		this.fechaHora = fechaHora;
		this.duracion = duracion;
		this.tipo = tipo;
		this.asistentes = asistentes;
		this.inscripcion = inscripcion;
		this.aulas = aulas;
	}
	
	public Integer getAsistentes() {
		return asistentes;
	}
	
	public void setAsistentes(Integer asistentes) {
		Checkers.check("El número de asistentes debe ser mayor que 0", asistentes >= 0);
		this.asistentes = asistentes;
	}
	
	public String getAsignatura() {
		return asignatura;
	}
	
	public Integer getCurso() {
		return curso;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	public Duration getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Duration duracion) {
		Checkers.check("La duración debe ser como mínimo de una hora", 
				duracion.compareTo(Duration.ofHours(1)) > 0);
		this.duracion = duracion;
	}
	
	public TipoExamen getTipo() {
		return tipo;
	}
	
	public Boolean getInscripcion() {
		return inscripcion;
	}
	
	public List<Aula> getAulas() {
		return aulas;
	}
	
	public List<Integer> getPuestos(){
		return this.aulas.stream()
				.map(Aula::capacidad)
				.toList();
	}
	
	public Integer getCapacidadMaxima() {
		return aulas.stream()
				.mapToInt(Aula::capacidad)
				.sum();
	}
	
	public Double getPorcentajeAsistentes() {
		return (100d * this.getAsistentes()) / this.getCapacidadMaxima() ;
	}

	public String toString() {
		return "Examen [asignatura=" + asignatura + ", curso=" + curso + ", fechaHora=" + fechaHora + ", duracion="
				+ duracion + ", tipo=" + tipo + ", asistentes=" + asistentes + ", inscripcion=" + inscripcion
				+ ", aulas=" + aulas + "]";
	}

	public int hashCode() {
		return Objects.hash(asignatura, curso, fechaHora);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Examen other = (Examen) obj;
		return Objects.equals(asignatura, other.asignatura) && Objects.equals(curso, other.curso)
				&& Objects.equals(fechaHora, other.fechaHora);
	}

	public int compareTo(Examen o) {
		int res = this.getFechaHora().compareTo(o.getFechaHora());
		if (res == 0) {
			res = this.getCurso().compareTo(o.getCurso());
			if (res == 0) {
				res = this.getAsignatura().compareTo(o.getAsignatura());
			}
		}
		return  res;
	}
	
	public Boolean usaAula(String nombreAula) {
		return this.aulas.stream()
				.map(Aula::nombre)
				.toList()
				.contains(nombreAula);
	}
		
}
