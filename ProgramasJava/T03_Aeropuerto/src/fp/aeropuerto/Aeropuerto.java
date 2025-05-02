package fp.aeropuerto;

import java.security.KeyStore.Entry;
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
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
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
		
		return res == null ? null : res.codigo();
	}
	
	public boolean existeVueloPrecioMenorQue(Double precio) {
		return this.vuelos.stream()
				.anyMatch(v -> v.precio() <= precio);
	}
	
	//TODO: Ejercicio 17
	public double promedioPreciosVuelosCompletos() {
		return this.vuelos.stream().filter(Vuelo::vueloCompleto).mapToDouble(Vuelo::precio).average().orElse(0);
	}
	
	public double sumaPreciosDistintosVuelosCompletos() {
		return this.vuelos.stream().filter(Vuelo::vueloCompleto).mapToDouble(Vuelo::precio).distinct().sum();
	}
	
	public long contarDistintosPasajeros() {
		return this.vuelos.stream().flatMap(v->v.pasajeros().stream()).distinct().count();
	}
}
