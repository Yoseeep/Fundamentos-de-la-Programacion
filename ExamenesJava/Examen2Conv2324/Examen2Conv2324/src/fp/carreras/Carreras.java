package fp.carreras;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Carreras {
	private SortedSet<Carrera> carreras;
	
	public SortedSet<Carrera> getCarreras(){
		return new TreeSet<Carrera>(this.carreras);
	}
	
	private Integer numeroCarreras(){
		return this.carreras.size();
	}
	
	public Carreras(Stream<Carrera> carreras) {
		SortedSet<Carrera> res = new TreeSet<Carrera>();
		res.addAll(carreras.toList());
		this.carreras = res;
	}

	public int hashCode() {
		return Objects.hash(carreras);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carreras other = (Carreras) obj;
		return Objects.equals(carreras, other.carreras);
	}

	public String toString() {
		return "Carreras [carreras=" + carreras + "]";
	}
	
	public List<Participante> participantesUltimaCarrera(){
		Comparator<Carrera> cmp = Comparator.comparing(Carrera::getFechaHora);
		return this.carreras.stream()
				.max(cmp)
				.get()
				.getParticipantes();
	}
	
	public Carrera carreraMayorDesnivelParticipante(String nombre, String apellidos) {
		Comparator<Carrera> cmp1 = Comparator.comparing(Carrera::getDesnivel);
		Comparator<Carrera> cmp2 = Comparator.comparing(Carrera::getDistancia)
								   .reversed();
		Comparator<Carrera> cmp = cmp1.thenComparing(cmp2);
		return this.carreras.stream()
				.filter(c -> c.buscaParticipante(nombre, apellidos) == null ? false : true)
				.max(cmp)
				.orElseThrow( () -> new NoSuchElementException() );
	}
	
	public Double tiempoMedioCarrera(String idCarrera) {
		return this.carreras.stream()
				.filter(c -> idCarrera.equals( c.getId() ) )
				.findAny()
				.orElseThrow( () -> new NoSuchElementException() )
				.tiempoMedioPorKm();
	}
	
	public SortedMap<Categoria,String> ganadoresPorCategoria(String idCarrera) {
		Comparator<Participante> cmp = Comparator.comparing(Participante::duracion);
		return this.carreras.stream()
				.filter(c -> idCarrera.equals( c.getId() ) )
				.flatMap(c -> c.getParticipantes().stream() )
				.collect(Collectors.groupingBy(
						p -> p.categoria(),
						() -> new TreeMap<Categoria, String>(),
						Collectors.collectingAndThen(
								Collectors.toList(), 
								lp -> buscaGanador(lp)
								)
						));
	}
	private static String buscaGanador(List<Participante> p) {
		Comparator<Participante> cmp = Comparator.comparing(Participante::duracion);
		Participante apellidoNombre = p.stream().min(cmp).orElseThrow( () -> new NoSuchElementException() );
		return apellidoNombre.apellidos() + ", " + apellidoNombre.nombre();
	}

	public Map<String,Integer> posicionesParticipante(String nombre, String apellidos) {
		Map<String,Integer> res = new HashMap<String, Integer>();
		for (Carrera carrera : carreras) {
			Participante participante = carrera.buscaParticipante(nombre, apellidos);
			if (participante == null) {
				continue;
			}
			Integer puesto = carrera.posicionParticipante(nombre, apellidos, participante.sexo());
			if( !res.containsKey(carrera.getId()) ) {
				res.put(carrera.getId(), puesto);
			}
		}
		
		return res;
	}
	
	public Categoria categoriaMasParticipantes() {
		Comparator< Entry<Categoria,Long> > cmp = Comparator.comparing(Entry<Categoria,Long>::getValue);
		return this.carreras.stream()
				.flatMap( c -> c.getParticipantes().stream() )
				.collect(Collectors.groupingBy(
						Participante::categoria,
						Collectors.counting()
						))
				.entrySet().stream()
				.max(cmp)
				.get().getKey();
	}
	
	
}
