package fp.viajes;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class Viaje {
	private Double precio;
	private Integer distancia;
	private Duration duracion;
	private TipoViaje tipo;
	private List<Parada> trayecto;
	
	public Viaje(Double precio, Integer distancia, Duration duracion,
				TipoViaje tipo, Parada paradaOrigen, Parada paradaDestino) {
		Checkers.check("El tipo de viaje con un solo origen y un destino no puede ser TRANSBORDO", 
				!tipo.equals(TipoViaje.TRANSBORDO));
		this.precio = precio;
		this.duracion = duracion;
		this.tipo = tipo;
		this.trayecto.add(paradaOrigen);
		this.trayecto.add(paradaDestino);
	}
	
	
	public Viaje(Double precio, Integer distancia, Duration duracion, 
			TipoViaje tipo, List<Parada> trayecto) {
		Checkers.check("El trayecto debe de tener al menos 2 paradas.", trayecto.size() >= 2);
		Checkers.check("El tipo de viaje con un solo origen y un destino no puede ser TRANSBORDO", 
				trayecto.size() == 2 ? !tipo.equals(TipoViaje.TRANSBORDO) : true );
		this.precio = precio;
		this.distancia = distancia;
		this.duracion = duracion;
		this.tipo = tipo;
		this.trayecto = List.copyOf(trayecto);
	}




	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Integer getDistancia() {
		return distancia;
	}
	
	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
	
	public Duration getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Duration duracion) {
		this.duracion = duracion;
	}
	
	public TipoViaje getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoViaje tipo) {
		Checkers.check("El tipo de viaje con un solo origen y un destino no puede ser TRANSBORDO", 
				trayecto.size() == 2 ? !tipo.equals(TipoViaje.TRANSBORDO) : true );
		this.tipo = tipo;
	}
	
	public List<Parada> getTrayecto() {
		return trayecto;
	}
	
	public void setTrayecto(List<Parada> trayecto) {
		Checkers.check("El trayecto debe de tener al menos 2 paradas.", trayecto.size() >= 2);
		Checkers.check("El tipo de viaje con un solo origen y un destino no puede ser TRANSBORDO", 
				trayecto.size() == 2 ? !tipo.equals(TipoViaje.TRANSBORDO) : true );
		this.trayecto = trayecto;
	}
	
	public Double valocidadMedia() {
		return (double) (this.distancia / this.duracion.toHours());
	}
	
	public Integer numParadas() {
		String primeraParada = this.trayecto.getFirst().nombre();
		String ultimaParada = this.trayecto.getLast().nombre();
		return (int) this.trayecto.stream()
				.map(Parada::nombre)
				.distinct()
				.filter(n -> !n.equals(primeraParada) && !n.equals(ultimaParada))
				.count();
	}
	
	public List<String> intermedias(){
		String primeraParada = this.trayecto.getFirst().nombre();
		String ultimaParada = this.trayecto.getLast().nombre();
		return this.trayecto.stream()
				.map(Parada::nombre)
				.filter(n -> !n.equals(primeraParada) && !n.equals(ultimaParada))
				.toList();
	}
	
	public String origen() {
		return this.trayecto.getFirst().nombre();
	}
	
	public String destino() {
		return this.trayecto.getLast().nombre();
	}
	
	public Integer numTransbordos() {
		List<String> nombresParadas = this.trayecto.stream()
										.map(Parada::nombre)
										.toList();
		return (int) nombresParadas.stream()
				.filter(n -> nombresParadas.indexOf(n) != nombresParadas.lastIndexOf(n))
				.count() / 2;
	}

	public int hashCode() {
		return Objects.hash(trayecto);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return Objects.equals(trayecto, other.trayecto);
	}

	public String toString() {
		return "Viaje [precio=" + precio + ", distancia=" + distancia + ", duracion=" + duracion + ", tipo=" + tipo
				+ ", trayecto=" + trayecto + "]";
	}
		
}
