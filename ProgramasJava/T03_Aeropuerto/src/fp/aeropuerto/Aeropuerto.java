package fp.aeropuerto;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

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
	
	
}
