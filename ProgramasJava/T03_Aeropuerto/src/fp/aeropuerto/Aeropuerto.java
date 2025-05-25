package fp.aeropuerto;

import java.util.Map.Entry;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import fp.aeropuerto.Persona;


import útiles.Checkers;

public class Aeropuerto implements Comparable<Aeropuerto>{
	private String nombre;
	private String localidad;
	List<Vuelo> vuelos;
	
	
	public String getNombre() {
		return nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public List<Vuelo> getVuelos() {
		return List.copyOf(vuelos);
	}
	
	public Integer getNumVuelos() {
		return vuelos.size();
	}
	
	public Aeropuerto(String nombre, String localidad, List<Vuelo> vuelos) {
		this.nombre = nombre;
		this.localidad = localidad;
		this.vuelos = List.copyOf(vuelos);
		Checkers.checkNoNull(nombre,localidad,vuelos);
	}
	
	public Aeropuerto(String nombre, String localidad) {
		this.nombre = nombre;
		this.localidad = localidad;
		this.vuelos = new LinkedList<Vuelo>();
		Checkers.checkNoNull(nombre,localidad);
	}
	
	public Aeropuerto(String nombre, String localidad, Stream<Vuelo> vuelos) {
		this.nombre = nombre;
		this.localidad = localidad;
		this.vuelos = vuelos.toList();
		Checkers.checkNoNull(nombre,localidad,vuelos);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Aeropuerto))
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	@Override
	public int compareTo(Aeropuerto o) {
		return this.getNombre().compareTo(o.getNombre());
	}
	
	public String toString() {
		return nombre + " - " + localidad + "(" + this.getNumVuelos() + ")";
	}
	
	
	public boolean existeVueloADestino(String destino) {
		for (Vuelo vuelo : vuelos) {
			if (vuelo.destino().toUpperCase().equals(destino.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean todosLosVueloCuestanMenosQue(Double precio) {
		for (Vuelo vuelo : vuelos) {
			if (vuelo.precio() >= precio) {
				return false;
			}
		}
		return true;
	}
		
	
	public Vuelo vueloMasDuracion() {
		Vuelo vueloMax = null;
		for (Vuelo vuelo : vuelos) {
			if (vueloMax == null || vuelo.duracion().compareTo(vueloMax.duracion()) > 0) {
				vueloMax = vuelo;
			}
		}
		return vueloMax;
	}
	
	
	public Map<String,Integer> cuentaVuelosPorDestino (){
		Map<String,Integer> cuentaDestinos = new HashMap<String, Integer>();
		for (Vuelo vuelo : vuelos) {
			if (!cuentaDestinos.containsKey(vuelo.destino())) {
				cuentaDestinos.put(vuelo.destino(), 1);
			}
			else {
				cuentaDestinos.put(vuelo.destino(), cuentaDestinos.get(vuelo.destino()) + 1 );
			}
		}
		
		return cuentaDestinos;
	}
	
	
	public Map< Compañía,Set<Vuelo> > distintosDestinosPorCompañia (){
		Map< Compañía,Set<Vuelo> > res = new HashMap< Compañía, Set<Vuelo> >();
		for (Vuelo vuelo : vuelos) {
			if( !res.containsKey(vuelo.compañia()) ) {
				Set<Vuelo> vuelos = new HashSet<Vuelo>();
				vuelos.add(vuelo);
				res.put(vuelo.compañia(), vuelos);
			}
			else {
				res.get(vuelo.compañia()).add(vuelo);
			}
		}
		return res;
	}
	
	
	public Map< Compañía,Set<Vuelo> > distintosDestinosVuelosCompletosPorCompañia (){
		Map< Compañía,Set<Vuelo> > res = new HashMap< Compañía, Set<Vuelo> >();
		for (Vuelo vuelo : vuelos) {
			if (!vuelo.vueloCompleto()) {
				continue;
			}
			
			if( !res.containsKey(vuelo.compañia()) ) {
				Set<Vuelo> vuelos = new HashSet<Vuelo>();
				vuelos.add(vuelo);
				res.put(vuelo.compañia(), vuelos);
			}
			else {
				res.get(vuelo.compañia()).add(vuelo);
			}
		}
		return res;
	}
	
	
	public List<Vuelo> vuelosPorOrdenNatural(){
		List<Vuelo> res = new ArrayList<Vuelo>(this.vuelos);
		Collections.sort(res);
		return res;
	}
	
	public Vuelo máximoVueloPorOrdenNatural() {
		return Collections.max(this.vuelos);
	}
	
	public List<Vuelo> vuelosPorInversoAlOrdenNatural(){
		List<Vuelo> res = new ArrayList<Vuelo>(this.vuelos);
		Collections.sort(res,Comparator.reverseOrder());
		return res;
	}
	
	public List<Vuelo> vuelosPorPrecioYHoraSalida(){
		List<Vuelo> res = new ArrayList<Vuelo>(this.vuelos);
		
		Comparator<Vuelo> cmp1 = Comparator.comparing(Vuelo::precio);
		Comparator<Vuelo> cmp2 = cmp1.thenComparing(v -> v.fechaHoraSalida().toLocalTime());
		
		Collections.sort(res,cmp2);
		return res;
	}
	
	public List<Vuelo> vuelosPorDuraciónYMayorNroPasajeros(){
		List<Vuelo> res = new ArrayList<Vuelo>(this.vuelos);
		
		Comparator<Vuelo> cmp1 = Comparator.comparing(Vuelo::duracion);
		Comparator<Vuelo> cmp2 = cmp1.thenComparing(v -> v.numeroPasajeros()).reversed();
		
		Collections.sort(res,cmp2);
		return res;
	}
	
	public List<Persona> pasajerosDePrimerVueloPorNombreYDni(){
		List<Persona> res = new ArrayList<Persona>(this.vuelos.getFirst().pasajeros());
		
		Comparator<Persona> cmp1 = Comparator.comparing(Persona::nombre).thenComparing(Persona::dni);
		
		Collections.sort(res,cmp1);
		return res;
	}
	
	public SortedSet<String> diferentesNombresDePasajerosPorOrdenAlfabéticoInverso() {
		Comparator<String> cmp1 = Comparator.reverseOrder();
		
		SortedSet<String> res = new TreeSet<String>(cmp1);
		
		for(Vuelo v : new ArrayList<Vuelo>(vuelos)) {
			res.addAll((v.pasajeros().stream().map(Persona::nombre).toList()));
		}
				
		return res;
	}
	
	public List<Persona> pasajerosDeTodosLosVuelosPorApellidosYNombre() {
		List<Persona> res = new ArrayList<Persona>();
		for(Vuelo v : this.vuelos) {
			res.addAll(v.pasajeros());
		}
		
		Comparator<Persona> cmp1 = Comparator.comparing(Persona::apellidos).thenComparing(Persona::nombre);
		
		Collections.sort(res,cmp1);
				
		return res;
	}
	
	public List<Persona> pasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre() {
		Comparator<Persona> cmp1 = Comparator.comparing(Persona::apellidos).thenComparing(Persona::nombre);
				
		SortedSet<Persona> res = new TreeSet<Persona>(cmp1);
		for(Vuelo v : this.vuelos) {
			res.addAll(v.pasajeros());
		}
				
		return res.stream().toList();
	}
	
	public int númeroVuelosADestino(String destino) {
		return (int) this.vuelos.stream()
				.filter(v->v.destino().equals(destino))
				.count();
	}
	
	public long númeroPasajerosADestino(String destino) {
		return this.vuelos.stream()
				.filter(v->v.destino().equals(destino))
				.mapToInt(v->v.numeroPasajeros())
				.sum();
	}
	
	public Vuelo vueloMenorRecaudaciónVuelosCompletos() {
		Comparator<Vuelo> comp = Comparator.comparing(v->v.precio()*v.numeroPasajeros());
		return this.vuelos.stream().filter(Vuelo::vueloCompleto).min(comp).get();
	}
	
	public String códigoDeAlgúnVueloADestinoConPlazasLibres(String destino) {
		Vuelo res= this.vuelos.stream()
				.filter(v -> v.destino().equals(destino) && ! v.vueloCompleto())
				.findAny().orElse(null);
		
		return res != null ? res.codigo() : null;
	}
	
	public boolean existeVueloPrecioMenorQue(Double precio) {
		return this.vuelos.stream()
				.anyMatch(v -> v.precio() <= precio);
	}
	
	public double promedioPreciosVuelosCompletos() {
		return this.vuelos.stream().filter(Vuelo::vueloCompleto).mapToDouble(Vuelo::precio).average().orElse(0);
	}
	
	public double sumaPreciosDistintosVuelosCompletos() {
		return this.vuelos.stream().filter(Vuelo::vueloCompleto).mapToDouble(Vuelo::precio).distinct().sum();
	}
	
	public long contarDistintosPasajeros() {
		return this.vuelos.stream().flatMap(v->v.pasajeros().stream()).distinct().count();
	}
	
	public Map<String,List<Vuelo>> mapListaVuelosPorDestinos(){
		return this.vuelos.stream().collect(Collectors.groupingBy(Vuelo::destino));
	}
	
	public Map<LocalDate,Set<Vuelo>> mapSetVuelosPorFechaLlegada(){
		return this.vuelos.stream()
				.filter(v->v.conEscalas())
				.collect(Collectors.groupingBy(
						v->v.fechaHoraLlegada().toLocalDate(),
						Collectors.toSet()
						)) ;
	}
	
	public Map<LocalDate,SortedSet<Vuelo>> mapSetOrdenadoVuelosPorFechaSalida(){
		Comparator<Vuelo> cmp = Comparator.comparing(Vuelo::numeroPasajeros);
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v->v.fechaHoraSalida().toLocalDate(),
						Collectors.toCollection( ()->new TreeSet<Vuelo>(cmp) )
						)) ;
	}
	
	public Map<Compañía,Integer> mapNumVuelosCompletosPorCompañia(){
		return this.vuelos.stream()
				.filter(Vuelo::vueloCompleto)
				.collect(Collectors.groupingBy(
						Vuelo::compañia,
						Collectors.collectingAndThen(
								Collectors.counting(), 
								l->l.intValue() )));
	}
	
	public Map<String,SortedSet<Double>> mapPreciosDiferentesOrdenadosPorDestino() {
		Comparator<Double> cmp = Comparator.reverseOrder();
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						Vuelo::destino,
						Collectors.mapping(
								Vuelo::precio, 
								Collectors.toCollection( ()->new TreeSet<Double>(cmp) ))
						));
	}
	
	public Map<Compañía,Double> mapPrecioMedioPorCompañia() {
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						Vuelo::compañia,
						()-> new TreeMap<Compañía,Double>(Comparator.naturalOrder()),
						Collectors.averagingDouble(Vuelo::precio)	
						)) ;
	}
	

	public Map<String, Double> mapPrecioVuelosConEscalasMásBaratoPorDestino(){
		return this.vuelos.stream()
				.filter(Vuelo::conEscalas)
				.collect(Collectors.groupingBy(
						Vuelo::destino,
						Collectors.collectingAndThen(
								Collectors.minBy(Comparator.comparing(Vuelo::precio)),
								v->v.get().precio()))
				);
	}
	
	

	public Map<LocalTime,Integer> mapSumaPlazasLibresPorHoraDeLlegada(Compañía cp){
		Comparator<LocalTime> cmp = Comparator.reverseOrder();
		return this.vuelos.stream()
				.filter( v -> v.compañia().equals(cp) )
				.collect(Collectors.groupingBy(
						v -> v.fechaHoraLlegada().toLocalTime(),
						()->new TreeMap<>( cmp ),
						Collectors.summingInt( v -> v.numeroPlazas() - v.numeroPasajeros() )
						)
				);
	}
	
	public String destinoMayorNúmeroDeplazasLibres() {
		Comparator<Entry<String,Integer>> cmp = Comparator.comparing(Entry<String,Integer>::getKey);
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						Vuelo::destino, 
						Collectors.summingInt(v -> v.numeroPlazas() - v.numeroPasajeros())
						))
				.entrySet().stream()
				.max(cmp).get().getKey()
				;
	}

	public List<Double> promediosDePasajerosPorFechasDeSalida(){
		Comparator<Entry<LocalDate,Double>> cmp = Comparator.comparing(Entry<LocalDate,Double>::getKey); 
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v -> v.fechaHoraSalida().toLocalDate(),
						Collectors.averagingDouble(Vuelo::numeroPasajeros)
						))
				.entrySet().stream()
				.sorted(cmp)
				.map(e->e.getValue())
				.toList();
	}
	
	
	public List<Entry<LocalDate,Double>> promediosDePasajerosPorFechasDeSalida2(){
		Comparator<Entry<LocalDate,Double>> cmp = Comparator.comparing(Entry<LocalDate,Double>::getKey); 
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v -> v.fechaHoraSalida().toLocalDate(),
						Collectors.averagingDouble(Vuelo::numeroPasajeros)
						))
				.entrySet().stream()
				.sorted(cmp)
				.toList();
	}
	
	public SortedMap<Duration,SortedSet<String>> mapDestinosPorDuración(){
		Comparator<Duration> cmp = Comparator.reverseOrder();
		Comparator<String> cmp1 = Comparator.reverseOrder();
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v->v.duracion(),
						()->new TreeMap<Duration,SortedSet<String>>(cmp),
						Collectors.mapping(
								v->v.destino(), 
								Collectors.toCollection( ()->new TreeSet<String>(cmp1) )
								)						
						));
	}
	
	
	public SortedMap<String,Double> mapPorcentajeVuelosPorDestino(Double precio){
		double numFiltro = this.vuelos.stream()
				.filter( v -> v.precio() >= precio )
				.count();
		return this.vuelos.stream()
				.filter( v->v.precio()>=precio )
				.collect(Collectors.groupingBy(
						v->v.destino(),
						() -> new TreeMap<String, Double>(),
						Collectors.collectingAndThen(
								Collectors.counting(), 
								c -> c == 0 ? null : (c/numFiltro) * 100
								)
						));	
	}
	
	public Compañía compañíaConMayorSumaDePlazasLibres() {
		Comparator<Entry<Compañía,Integer>> cmp = Comparator.comparing( Entry<Compañía,Integer> :: getValue );
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v->v.compañia(),
						Collectors.summingInt(v -> v.numeroPlazas() - v.numeroPasajeros())
						))
				.entrySet().stream()
				.max(cmp).get().getKey();
	}
	
	public LocalDate segundoDíaConMenosVuelos() {
		Comparator<Entry<LocalDate,Long>> cmp = Comparator.comparing(Entry<LocalDate,Long>::getValue);
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v->v.fechaHoraSalida().toLocalDate(),
						Collectors.counting()
						))
				.entrySet().stream()
				.sorted(cmp)
				.map(Entry<LocalDate,Long>::getKey)
				.toList().get(1); 
	}
	
	public SortedMap<LocalDate,Set<Double>> mapPreciosSuperioresPorFechas() {
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v->v.fechaHoraLlegada().toLocalDate(),
						TreeMap::new,
						Collectors.collectingAndThen(
								Collectors.mapping(
										Vuelo::precio, 
										Collectors.toList()),
								 lp -> filtraPromedios(lp)
								 )
				));
	}
	private static Set<Double> filtraPromedios(List<Double> lp) {
		Double minimo = lp.stream().min(Comparator.naturalOrder()).get();
		Double maximo = lp.stream().max(Comparator.naturalOrder()).get();
		Double promedio = ( minimo + maximo ) / 2;
		return lp.stream()
				.filter( p -> p >= promedio )
				.collect(Collectors.toSet());
	}
		
	public String destinoConMayorDiferenciaDePrecio(){
		Comparator<Entry<String,Double>> cmp = Comparator.comparing(Entry<String,Double>::getValue);
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v->v.destino(),
						Collectors.collectingAndThen(
								Collectors.mapping(
										Vuelo::precio, 
										Collectors.toList()
										), 
								lp -> lp.stream().max(Comparator.naturalOrder()).get() 
									- lp.stream().min(Comparator.naturalOrder()).get()
								)
						))
				.entrySet().stream()
				.max(cmp).get().getKey()
				;
	}
	
	public LocalDate fechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos(Set<String> destinos) {
		Comparator< Entry<LocalDate,Integer> > cmp = Comparator.comparingDouble(Entry<LocalDate,Integer>::getValue);
		return this.vuelos.stream()
				.filter( v -> destinos.contains( v.destino() ) )
				.collect(Collectors.groupingBy(
						v -> v.fechaHoraLlegada().toLocalDate(),
						Collectors.collectingAndThen(
								Collectors.flatMapping(
										v -> v.pasajeros().stream(), 
										Collectors.toSet()
								), 
								sp -> sp.size()
								)
						))
				.entrySet().stream()
				.max(cmp).get()
				.getKey();
	}
	
	public SortedMap<String,Double> topNMediaPreciosPorDestino(Integer n) {
		Comparator<String> cmp = Comparator.reverseOrder();
		Comparator<Double> cmp1 = Comparator.reverseOrder();
		return this.vuelos.stream()
				.collect(Collectors.groupingBy(
						v -> v.destino(),
						() -> new TreeMap<String,Double>( cmp ),
						Collectors.collectingAndThen(
								Collectors.mapping(
										v->v.precio(), 
										Collectors.toList()
										), 
								lp -> mediaNMasCaros(lp, n)						
								)						
						));
	}
	
	private static Double mediaNMasCaros(List<Double> lp, Integer n) {
		Checkers.check("El número n debe ser positivo", n>0);
		Comparator<Double> cmp1 = Comparator.reverseOrder();
		return lp.stream()
				.sorted(cmp1)
				.limit(n)
				.mapToDouble(d -> d.doubleValue())
				.average().getAsDouble();
	}
}
