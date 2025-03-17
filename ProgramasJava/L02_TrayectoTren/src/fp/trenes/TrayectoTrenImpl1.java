package fp.trenes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.LinkedList;
import java.util.List;

import útiles.Checkers;

public class TrayectoTrenImpl1 {
	private String codigoTren;
	private String nombreTrayecto;
	private TipoTren tipo;
	private List<String> estaciones;
	private List<LocalTime> horasSalida;
	private List<LocalTime> horasLlegada;
	
	public TrayectoTrenImpl1(String codigoTren, 
						String nombreTrayecto, 
						TipoTren tipo, 
						String estacionOrigen, 
						String estacionFinal,
						LocalTime horaSalida,
						LocalTime horaLlegada
						) {
		this.codigoTren = codigoTren;
		this.nombreTrayecto = nombreTrayecto;
		this.tipo = tipo;
		this.estaciones = new LinkedList<String>();
		this.estaciones.add(estacionOrigen);
		this.estaciones.add(estacionFinal);
		this.horasSalida = new LinkedList<LocalTime>();
		this.horasSalida.add(horaSalida);
		this.horasSalida.add(null);
		this.horasLlegada = new LinkedList<LocalTime>();
		this.horasLlegada.add(null);
		this.horasLlegada.add(horaLlegada);
		chequeaCodigo(codigoTren);
		Checkers.checkNoNull(horaSalida,horaLlegada);
		Checkers.check("La hora de salida de la primera estación no debe ser anterior a la hora de llegada a la última estación", 
				horaSalida.isAfter(horaLlegada));
	}
	
	private void chequeaCodigo(String codigoTren) {
		boolean res = true;
		if (codigoTren.length() != 5) {
			res =  false;
		} else {
			for (char c: codigoTren.toCharArray()) {
				if (!Character.isDigit(c)) {
					res = false;
				}
			}
		}
		Checkers.check("El código de un tren debe estar formado por 5 dígitos", res);
		
	}
	
	public LocalTime horaSalida() {
		return horasSalida.getFirst();
	}
	
	public LocalTime horaLlegada() {
		return horasLlegada.getLast();
	}
	
	public Duration duracionTrayecto() {
		return Duration.between(horaSalida(), horaLlegada());
	}
	
	public LocalTime getHoraSalida(String estacion) {
		return horasSalida.get( estaciones.indexOf(estacion) );
	}
	
	public LocalTime getHoraLlegada(String estacion) {
		return horasLlegada.get( estaciones.indexOf(estacion) );
	}

	@Override
	public String toString() {
		String s = String.format("%-40s\t%5s\t%5s\n",this.nombreTrayecto,"LLEGADA","SALIDA");
		for (int i = 0; i < estaciones.size();i++) {
			if (this.horasLlegada.get(i)==null){
				s+=String.format("%-40s\t\t%5s\n",this.estaciones.get(i),this.horasSalida.get(i));
			}
			else {
				if (this.horasSalida.get(i)==null){
				s+=String.format("%-40s\t%5s\n",this.estaciones.get(i),this.horasLlegada.get(i));
				}
				else {
					s+=String.format("%-40s\t%5s\t%5s\n",this.estaciones.get(i),this.horasLlegada.get(i),
							this.horasSalida.get(i));
				}
			}
		}
		
		return "TrayectoTrenImpl1 [codigoTren=" + codigoTren + ", nombreTrayecto=" + nombreTrayecto + ", tipo=" + tipo
				+ ", estaciones=" + estaciones + ", horasSalida=" + horasSalida + ", horasLlegada=" + horasLlegada
				+ "]";
	}
	
	
	
}
