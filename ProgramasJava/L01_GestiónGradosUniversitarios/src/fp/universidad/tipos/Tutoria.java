package fp.universidad.tipos;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record Tutoria(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {
	
	public long duracion() {
		return Duration.between(horaFin, horaComienzo).toMinutes(); 
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
		default:
			res = 'V';
			break;
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
}
