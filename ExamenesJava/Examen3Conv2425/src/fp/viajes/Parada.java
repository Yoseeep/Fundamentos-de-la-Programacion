package fp.viajes;

import java.time.LocalTime;
import java.util.Objects;

public record Parada(String nombre, LocalTime hora) {

	public int hashCode() {
		return Objects.hash(hora, nombre);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parada other = (Parada) obj;
		return Objects.equals(hora, other.hora) && Objects.equals(nombre, other.nombre);
	}
	
}
