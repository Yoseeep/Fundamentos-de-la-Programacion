package fp.aeropuerto;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
		
}
