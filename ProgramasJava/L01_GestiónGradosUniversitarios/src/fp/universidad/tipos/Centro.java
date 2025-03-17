package fp.universidad.tipos;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import utiles.Checkers;

public class Centro implements Comparable<Centro>{
	private String nombre;
	private String direccion;
	private Integer plantas;
	private Integer sotanos;
	private Set<Espacio> espacios;
	
	
	public Centro(String nombre, String direccion, Integer plantas, Integer sotanos, Set<Espacio> espacios) {
		Checkers.check("Un centro debe tener al menos una planta", plantas >= 1);
		Checkers.check("Un centro puede tener cero o más sótanos", sotanos >= 0);
		this.nombre = nombre;
		this.direccion = direccion;
		this.plantas = plantas;
		this.sotanos = sotanos;
		this.espacios = new HashSet<Espacio>();
	}
	
	
	
	public String getNombre() {
		return nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public Integer getPlantas() {
		return plantas;
	}
	
	public Integer getSotanos() {
		return sotanos;
	}


	public Set<Espacio> getEspacios() {
		return espacios;
	}
	
	public void nuevoEspacio(Espacio e) {
		Checkers.check("Planta no valida", 
				-this.sotanos <= e.getPlanta() && e.getPlanta() <= this.plantas-1);
		espacios.add(e);
	}
	
	public void eliminaEspacio(Espacio e) {
		espacios.remove(e);
	}


	@Override
	public String toString() {
		return nombre;
	}



	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Centro))
			return false;
		Centro other = (Centro) obj;
		return Objects.equals(nombre, other.nombre);
	}



	@Override
	public int compareTo(Centro o) {
		return this.nombre.compareTo(o.nombre);
	}
	
	
	//TODO
	public Integer[] getConteosEspacios(){
		return null;
	}
	
	//TODO
	public Set<Object> getDespachos(){ //REALMENTE DEVUELVE UN TIPO DESPACHO, PERO AÚN NO ESTÁ DEFINIDO
		return null;
	}
	
	//TODO
	public Set<Object> getDespachos(Object d){ //REALMENTE RECIBE UN TIPO DESPACHO, PERO AÚN NO ESTÁ DEFINIDO
		return null;
	}
	
	//TODO
	public Set<Object> getProfesores(){ //REALMENTE RECIBE UN TIPO PROFESOR, PERO AÚN NO ESTÁ DEFINIDO
		return null;
	}
	
	//TODO
	public Set<Object> getProfesores(Object d){ //REALMENTE RECIBE UN TIPO PROFESOR, PERO AÚN NO ESTÁ DEFINIDO
		return null;
	}
	
	
	public Espacio getEspacioMayorCapacidad(){
		Espacio[] arrayEspacios = (Espacio[]) espacios.toArray();
		Espacio res = arrayEspacios[0];
		for (Espacio e: arrayEspacios){
			if (e.getCapacidad() > res.getCapacidad()) {
				res = e;
			}
		}
		return res;
	}
		
	
	
	
}
