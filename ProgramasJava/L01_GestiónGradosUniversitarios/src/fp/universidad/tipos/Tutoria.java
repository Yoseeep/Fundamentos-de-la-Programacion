package fp.universidad.tipos;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import utiles.Checkers;

public record Tutoria(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) implements Comparable<Tutoria>{
	
	public Tutoria{
		Checkers.check("Las tutorias deben tener lugar de Lunes a Viernes",
					   diaSemana != DayOfWeek.SUNDAY && diaSemana != DayOfWeek.SATURDAY);
		chequeaDuracion(horaComienzo,horaFin);
	}
		
	private void chequeaDuracion(LocalTime horaComienzo, LocalTime horaFin) {
		long duracion = Duration.between(horaComienzo, horaFin).toMinutes();
		boolean res = duracion > 15;
		Checkers.check("Las tutorias deben durar al menos 15 minutos",
				   res);
	}
	
	
	public long duracion() {
		return Duration.between(horaComienzo, horaFin).toMinutes(); 
	}
	
	
	private char diaSemanaParseado() {
		char res;
		
		switch (diaSemana) {
		case MONDAY:
			res = 'L';
			break;
		case TUESDAY:
			res = 'M';
			break;
		case WEDNESDAY:
			res = 'X';
			break;
		case THURSDAY:
			res = 'J';
			break;
		case FRIDAY:
			res = 'F';
			break;
		default:
			res = '*';
		}
		
		return res;
	}
	
	
	private String horasParseadas() {
		String hInicio = this.horaComienzo.format(DateTimeFormatter.ofPattern("hh:mm"));
		String hFin = this.horaFin.format(DateTimeFormatter.ofPattern("hh:mm"));
		return hInicio + "-" + hFin;
	}
	
	
	public String toString() {
		return this.diaSemanaParseado() + " " + this.horasParseadas();
	}

	@Override
	public int hashCode() {
		return Objects.hash(diaSemana, horaComienzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Tutoria))
			return false;
		Tutoria other = (Tutoria) obj;
		return diaSemana == other.diaSemana && Objects.equals(horaComienzo, other.horaComienzo);
	}
	
	public int compareTo(Tutoria t) {
		int res = this.diaSemana.compareTo(t.diaSemana);
		if (res == 0) {
			res = this.horaComienzo.compareTo(t.horaComienzo);
		}
		return res;
	}
}
