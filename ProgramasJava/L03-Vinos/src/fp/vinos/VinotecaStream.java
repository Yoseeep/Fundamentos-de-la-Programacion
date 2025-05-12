package fp.vinos;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class VinotecaStream implements Vinoteca {
	private Set<Vino> vinos;

	public VinotecaStream() {
		this.vinos = new HashSet<Vino>();
	}

	public VinotecaStream(Collection<Vino> vinos) {
		this.vinos = Set.copyOf(vinos);
	}

	public VinotecaStream(Stream<Vino> vinos) {
		this.vinos = Set.copyOf(vinos.toList());
	}

	public String toString() {
		return "Total vinos: " + obtenerNumeroVinos();
	}

	public int hashCode() {
		return Objects.hash(vinos);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VinotecaStream other = (VinotecaStream) obj;
		return Objects.equals(vinos, other.vinos);
	}

	public void agregarVino(Vino v) {
		if (v != null) {
			this.vinos.add(v);
		}
	}

	public void eliminarVino(Vino v) {
		this.vinos.remove(v);
	}

	public int obtenerNumeroVinos() {
		return this.vinos.size();
	}

	public boolean contieneVino(Vino v) {
		return this.vinos.contains(v);
	}

	public void agregaVinos(Collection<Vino> vinos) {
		this.vinos.addAll(vinos);
	}

	public boolean contieneVinos(Collection<Vino> vinos) {
		this.vinos.removeAll(vinos);
		return false;
	}

	public int calcularNumeroVinosDePais(String pais) {
		return (int) this.vinos.stream()
				.filter(v->v.pais().toUpperCase().equals(pais.toUpperCase()))
				.count();
	}

	public Collection<Vino> obtenerVinosRangoPuntos(int inf, int sup) {
		Checkers.check("El límite inferior debe ser menor que el límete superior",
						inf <= sup);
		return this.vinos.stream()
				.filter(v-> inf <= v.puntos() && v.puntos() <= sup)
				.collect(Collectors.toCollection(ArrayList::new));
				// .toList()
				// .collect(Collectors.toList())
	}
	
	public int calcularNumeroVinosDePaisConPuntuacionSuperior(String pais, Integer umbralPuntuacion) {
		return (int) this.vinos.stream()
				.filter( v -> v.pais().equals(pais) && v.puntos() > umbralPuntuacion )
				.count();
	}

	public Set<Vino> obtenerVinosBaratos(Double umbralPrecio) {
		return this.vinos.stream()
				.filter(v->v.precio() < umbralPrecio)
				.collect(Collectors.toSet());
	}

	public boolean existeVinoDeUvaEnRegion(String region, String uva) {
		return this.vinos.stream()
				.filter(v->v.region().equals(region))
				.anyMatch(v->v.uva().equals(uva));
	}

	public Set<String> calcularUvasDeRegion(String region) {
		return this.vinos.stream()
				.filter(v->v.region().equals(region))
				.map(v->v.uva())
				.collect(Collectors.toSet());
	}

	public int calcularTotalPuntosVinosDeRegion(String region) {
		return this.vinos.stream()
				.filter(v->v.region().equals(region))
				.mapToInt(v->v.puntos())
				.sum();
	}

	public double calcularMediaPuntosVinosDeUva(String uva) {
		return this.vinos.stream()
				.filter(v->v.uva().equals(uva))
				.mapToInt(v->v.puntos())
				.average().orElse(0.0);
	}

	public Vino obtenerVinoMejorPuntuado() {
		return this.vinos.stream()
				.max(Comparator.comparing(Vino::puntos))
				.orElseThrow();
	}

	public Vino obtenerVinoMejorPuntuadoDePais(String pais) {
		return this.vinos.stream()
				.filter(v->v.pais().equals(pais))
				.max(Comparator.comparing(Vino::puntos))
				.orElseThrow();
	}

	public List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region, int n) {
		return this.vinos.stream()
				.sorted(Comparator.comparing(Vino::precio).reversed())
				.limit(n)
				.toList();
	}

	public Map<String, List<Vino>> agruparVinosPorPais() {
		return this.vinos.stream()
				.collect(Collectors.groupingBy(
						Vino::pais,
						Collectors.toList()
						));
	}

	public Map<String, Set<String>> agruparUvasPorPais() {
		return this.vinos.stream()
				.collect(Collectors.groupingBy(
						Vino::pais,
						Collectors.mapping(
								v->v.uva(), 
								Collectors.toSet()
								)
						));
	}

	public Map<String, Long> calcularCalidadPrecioPorRegionMayorDe(Double umbralCalidadPrecio) {
		return this.vinos.stream()
				.filter(v->v.calidadPrecio() > umbralCalidadPrecio)
				.collect(Collectors.groupingBy(
						Vino::region,
						Collectors.counting()
						));
	}

	public Map<String, Vino> calcularVinoMasCaroPorPais() {
		return this.vinos.stream()
				.collect(Collectors.groupingBy(
						Vino::pais,
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(Vino::precio)),
								m->m.get() )
						));
	}

	public SortedMap<String, List<Vino>> calcularNMejoresVinosPorPais(int n) {
		return this.vinos.stream()
				.collect(Collectors.groupingBy(
						Vino::pais, 
						()->new TreeMap<String, List<Vino>>(), 
						Collectors.collectingAndThen(
								Collectors.toList(), 
								l->l.stream()
									.sorted( Comparator.comparing(Vino::precio) )
									.limit(n)
									.toList() )
						));
	}

	public String calcularRegionConMejoresVinos(Double umbralCalidadPrecio) {
		return this.vinos.stream()
				.filter(v->v.calidadPrecio()>umbralCalidadPrecio)
				.collect(Collectors.groupingBy(
						Vino::region,
						Collectors.counting()
						))
				.entrySet()
				.stream()
				.max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
				.orElse(null)
				.getKey();			
	}

}
