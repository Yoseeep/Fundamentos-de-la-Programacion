package fp.aeropuerto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import útiles.Checkers;

public record Vuelo(String codigo, 
					String destino, 
					LocalDateTime fechaHoraSalida, 
					Duration duracion, 
					Double velocidad, 
					Double precio, 
					Integer numeroPlazas, 
					Boolean conEscalas,
					List<Persona> pasajeros) implements Comparable<Vuelo>{
	
	public Vuelo{
		pasajeros = List.copyOf(pasajeros); //Para hacer la lista de pasajeros Inmutable
		Checkers.checkNoNull(codigo,destino,fechaHoraSalida,duracion,velocidad,precio,numeroPlazas,conEscalas,pasajeros);
		Checkers.check("El formato del código debe ser aaa-nnn", compruebaFormatoCodigo(codigo)); // compruebaFormatoCodigo() no funciona; codigo.charAt(3) == '-' sí funciona
		Checkers.check("El precio debe ser mayor o igual que cero", precio.doubleValue() >= 0); 
		Checkers.check("El número de plazas debe ser mayor o igual que cero", numeroPlazas.intValue() >= 0);
		Checkers.check("La velocidad debe ser mayor o igual que cero", velocidad.doubleValue() >= 0);
		Checkers.check("La duración debe ser mayor que cero", duracion.isPositive());
		Checkers.check("El número de pasajeros debe ser menor o igual que el número de plazas", pasajeros.size() <= numeroPlazas.intValue()); // Cuando pongo numeroPasajeros().IntValue() no me funciona
		
	}
	public boolean compruebaFormatoCodigo(String codigo) { // Debe recibir un argumento, porque si no, detecta que codigo es vacío.
		char[] prefijo = codigo.substring(0, 3).toCharArray();
		char infijo = codigo.charAt(3);
		char[] sufijo = codigo.substring(5).toCharArray();
		
		if (codigo.length() != 7) {
			return false;
		}
		
		if (infijo != '-'){
			return false;
		}
		
		for (Character d: prefijo) {
			if (!Character.isAlphabetic(d)) {
				return false;
			}
		}
		
		for (Character d: sufijo) {
			if (!Character.isDigit(d)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public Integer numeroPasajeros() {
		return pasajeros.size(); 
	}
	 
	
	public Boolean vueloCompleto() {
		return numeroPlazas().equals(numeroPasajeros());
	}
	
	public Double porcentajeOcupacion() {
		return numeroPasajeros() / (double) numeroPlazas();
	}
	
	public LocalDateTime fechaHoraLlegada() {
		return fechaHoraSalida().plus(duracion);
	}
	
	public Compañía compañia() {
		String prefijo  = codigo.substring(0,3).toUpperCase();
		return Compañía.valueOf(prefijo);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, destino, fechaHoraSalida.toLocalDate());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vuelo))
			return false;
		Vuelo other = (Vuelo) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(destino, other.destino)
				&& Objects.equals(fechaHoraSalida.toLocalDate(), other.fechaHoraSalida.toLocalDate());
	}
	
	@Override
	public int compareTo(Vuelo o) {
		int res = codigo.compareTo(o.codigo);
		if (res == 0) {
			res = destino.compareTo(o.destino);
			if (res == 0) {
				res = this.fechaHoraSalida.toLocalDate().compareTo(o.fechaHoraSalida.toLocalDate());
			}
		}
		return res;
	}
	
	
	@Override
	public String toString() {
		return "Vuelo [codigo=" + codigo + ", destino=" + destino + ", fechaHoraSalida=" + fechaHoraSalida
				+ ", fechaHoraLlegada=" + fechaHoraSalida.plus(duracion) + ", numeroPlazas=" + numeroPlazas 
				+ ", numeroPasajeros=" + pasajeros.size() + "]";
	}
	
	
	
	
	
	
}
