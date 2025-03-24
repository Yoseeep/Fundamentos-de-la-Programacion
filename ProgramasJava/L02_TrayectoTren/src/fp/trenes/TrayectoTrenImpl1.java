package fp.trenes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import útiles.Checkers;

public class TrayectoTrenImpl1 implements TrayectoTren{
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
				horaSalida.isBefore(horaLlegada));
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
	
	
	public List<String> getEstaciones() {
		return estaciones;
	}

	public List<LocalTime> getHorasSalida() {
		return horasSalida;
	}

	public List<LocalTime> getHorasLlegada() {
		return horasLlegada;
	}

	public String getCodigoTren() {
		return codigoTren;
	}

	public String getNombreTrayecto() {
		return nombreTrayecto;
	}

	public TipoTren getTipo() {
		return tipo;
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
		int indice  = estaciones.indexOf(estacion);
		if (indice == -1) {
			return null;
		} 
		return horasSalida.get( indice );
	}
	
	public LocalTime getHoraLlegada(String estacion) {
		int indice  = estaciones.indexOf(estacion);
		if (indice == -1) {
			return null;
		} 
		return horasLlegada.get( indice );
	}
	
	public void añadirEstacionIntermedia(int posicion, String estacion, LocalTime horaLlegada, LocalTime horaSalida) {
		Checkers.checkNoNull(posicion, estacion, horaLlegada, horaSalida);
		Checkers.check("posicion no valida", 0 < posicion && posicion < estaciones.size());
		Checkers.check("La hora de salida debe ser anterior a la hora llegada", horaLlegada.isBefore(horaSalida));
		Checkers.check("Hora de salida no valida en dicho posicion", horasSalida.get(posicion-1).isBefore(horaSalida));
		Checkers.check("Hora de llegada no valida en dicho posicion", horasLlegada.get(posicion).isAfter(horaLlegada));
		estaciones.add(posicion, estacion);
		horasLlegada.add(posicion, horaLlegada);
		horasSalida.add(posicion, horaSalida);
	}
	
	public void eliminarEstacionIntermedia(String estacion) {
		Checkers.checkNoNull(estacion);
		int indice = estaciones.indexOf(estacion);
		Checkers.check("posicion no valida", indice != 0 && indice != estaciones.size()-1);
		Checkers.check("Dicha estacion no esta en la lista de estaciones", indice == -1);
		estaciones.remove(indice);
		horasSalida.remove(indice);
		horasLlegada.remove(indice+1);
		
	}
	
	

	@Override
	public String toString() {
		String s = "TRAYECTO" + this.nombreTrayecto + " - " + this.tipo + " (Nº" + this.codigoTren + ")";
		s += "\n\tESTACION\t\t\t\tLLEGADA\tSALIDA\n";
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
		
		return s;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoTren, horasSalida, nombreTrayecto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TrayectoTrenImpl1))
			return false;
		TrayectoTrenImpl1 other = (TrayectoTrenImpl1) obj;
		return Objects.equals(codigoTren, other.codigoTren) && Objects.equals(horasSalida, other.horasSalida)
				&& Objects.equals(nombreTrayecto, other.nombreTrayecto);
	}

	@Override
	public int compareTo(TrayectoTren o) {
		int res = this.getNombreTrayecto().compareTo(o.getNombreTrayecto());
		if (res == 0) {
			res = this.horaSalida().compareTo(o.horaSalida());
		} 
		else {
			if (res == 0) {
				res = this.getCodigoTren().compareTo(o.getCodigoTren());
			}
		}
		return res;
	}





	
	
	
	
}
