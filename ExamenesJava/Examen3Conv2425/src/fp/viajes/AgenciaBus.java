package fp.viajes;

import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AgenciaBus {
	private String nombre;
	private List<Viaje> viajes;
	
	public AgenciaBus(String nombre, Stream<Viaje> sv) {
		this.nombre = nombre;
		this.viajes = sv.toList();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public List<Viaje> getViajes() {
		return viajes;
	}
	
	public String toString() {
		String acum = "";
		for (Viaje viaje : this.viajes.subList(0, this.viajes.size()-1)) {
			acum.concat( viaje.toString() + "\n" );
		}
		acum.concat(this.viajes.getLast().toString());
		return acum;
	}

	public int hashCode() {
		return Objects.hash(viajes);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgenciaBus other = (AgenciaBus) obj;
		return Objects.equals(viajes, other.viajes);
	}
	
	public Duration getMaximoDuracion() {
		Comparator<Viaje> cmp = Comparator.comparing(Viaje::numParadas);
		return this.viajes.stream()
				.max(cmp)
				.map(Viaje::getDuracion)
				.get()
				;
	}
	
	public void añadirTiempoDescanso(String parada, Integer minutos) {
		this.viajes.stream()
		.filter( v -> v.intermedias().contains(parada) )
		.forEach(v -> v.setDuracion(
				v.getDuracion().plus(Duration.ofMinutes(minutos))
				));
	}
	
	public SortedMap<String, Duration> getDuracionMinimaPorDestino(TipoViaje tipo){
		Comparator<Duration> cmp = Comparator.naturalOrder();
		return this.viajes.stream()
				.filter(v -> v.getTipo().equals(tipo))
				.collect(Collectors.groupingBy(
						v -> v.destino(),
						() -> new TreeMap<String,Duration>(),
						Collectors.collectingAndThen(
								Collectors.mapping(
										v -> v.getDuracion(), 
										Collectors.toList()
										), 
								ld -> ld.stream().min(cmp).get()
								)
						));
	}
	
	public Map<String, Set<Viaje>> getViajesPorParadas(Double precio){
		Map<String, Set<Viaje>> res = new TreeMap<String, Set<Viaje>>();
		for(Viaje viaje : this.viajes) {
			if ( precio == null || (viaje.getPrecio() <= precio) ) {
				for(Parada parada : viaje.getTrayecto()) {
					if( !res.containsKey(parada.nombre()) ) {
						res.put(parada.nombre(), new HashSet<Viaje>());
					}
					res.get(parada.nombre()).add(viaje);
				}
			}
		}
		
		return res;
	}
	
	public SortedMap<String, Double> getPrecioMedioViajesPorParada(){
		SortedMap<String,Double> res = new TreeMap<String, Double>();
		
		this.getViajesPorParadas(null)
			.entrySet().stream()
			.filter(par -> par.getValue().size() > 1)
			.forEach(par -> res.put(par.getKey(), 
									par.getValue().stream()
									.mapToDouble(v -> v.getPrecio())
									.average().getAsDouble()
									)
					);
		
		return res;			
	}	
	
}
