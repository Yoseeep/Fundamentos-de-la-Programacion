package fp.hamburgueserias;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class Visita implements Comparable<Visita>{
	private String email;
	private String ciudad;
	private String codigoPostal;
	private LocalDateTime entrada;
	private LocalDateTime salida;
	private Double temperatura;
	private List<Evaluacion> evaluaciones;
	
	
	public Visita(String email, String ciudad, String codigoPostal, LocalDateTime entrada, LocalDateTime salida,
			Double temperatura, List<Evaluacion> evaluaciones) {
		Checkers.check("El momento de salida debe ser posterior al momento de entrada.", 
				salida.isAfter(entrada));
		Checkers.check("El email debe contener el carácter ‘@’.", email.contains("@"));
		Checkers.check("La lista de evaluaciones debe contener al menos un elemento.", 
				evaluaciones.size() >= 1);
		Checkers.check("El día de entrada tiene que ser el mismo que el de salida.",
				entrada.toLocalDate().equals(salida.toLocalDate()));
		
		this.email = email;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.entrada = entrada;
		this.salida = salida;
		this.temperatura = temperatura;
		this.evaluaciones = List.copyOf(evaluaciones);
	}
	
	public LocalDateTime getSalida() {
		return salida;
	}
	public void setSalida(LocalDateTime salida) {
		Checkers.check("El momento de salida debe ser posterior al momento de entrada.", 
				salida.isAfter(entrada));
		Checkers.check("El día de entrada tiene que ser el mismo que el de salida.",
				entrada.toLocalDate().equals(salida.toLocalDate()));
		this.salida = salida;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	public String getEmail() {
		return email;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public LocalDateTime getEntrada() {
		return entrada;
	}

	public List<Evaluacion> getEvaluaciones() {
		return List.copyOf(evaluaciones);
	}
	
	public Duration getTiempoTrascurrido() {
		return Duration.between(entrada, salida);
	}
	
	public Integer numeroEvaluaciones() {
		return this.evaluaciones.size();
	}
	
	@SuppressWarnings({ "null", "unused" })
	public Paladar paladar() {
		Paladar res = null;
		Double mediaEvaluaciones = this.getEvaluaciones().stream()
										.mapToDouble( e -> e.puntuacionFinal() )
										.average().orElse((Double) null);
		if (mediaEvaluaciones == null) {
			return null;
		}
		
		if( 9 <= mediaEvaluaciones ) {
			res = Paladar.BAJO;
		}
		
		if( 6 < mediaEvaluaciones && mediaEvaluaciones < 9 ) {
			res = Paladar.MEDIO;
		}
		
		if( mediaEvaluaciones <= 6 ) {
			res = Paladar.ALTO;
		}
				
		return res;
	}

	public String toString() {
		return "Visita [email=" + email + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + ", entrada="
				+ entrada + ", salida=" + salida + ", temperatura=" + temperatura + ", evaluaciones=" + evaluaciones
				+ "]";
	}

	public int hashCode() {
		return Objects.hash(email, entrada, salida);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visita other = (Visita) obj;
		return Objects.equals(email, other.email) && Objects.equals(entrada, other.entrada)
				&& Objects.equals(salida, other.salida);
	}

	public int compareTo(Visita o) {
		int res = this.entrada.compareTo(o.getEntrada());
		if (res == 0) {
			res = this.salida.compareTo(o.getSalida());
			if (res == 0) {
				res = this.email.compareTo(o.getEmail());
			}
		}
		return res;
	}
	
	
	
	
	
}
