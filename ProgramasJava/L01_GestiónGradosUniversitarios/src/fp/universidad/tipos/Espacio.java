package fp.universidad.tipos;

import java.util.Objects;

import utiles.Checkers;

public class Espacio implements Comparable<Espacio>{
	private TipoEspacio tipo;
	private String nombre;
	private Integer capacidad;
	private Integer planta;
	
	
	public Espacio(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		chequeaCapacidad(capacidad);
		this.tipo = tipo;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.planta = planta;
	}
	
	public Espacio(String s) {
		String[] tr = s.split(",");
		Checkers.check("La cadena está mal troceada: " + s, s.length() != 4);
		this.tipo = TipoEspacio.valueOf(tr[3].trim().toUpperCase());
		this.nombre = tr[0].trim(); 
		this.capacidad = Integer.valueOf(tr[2].trim());
		this.planta = Integer.valueOf(tr[1].trim());
		chequeaCapacidad(capacidad);
	}
	
	public void chequeaCapacidad(Integer capacidad) {
		Checkers.check("La capacidad debe ser mayor que 0", capacidad > 0);
	}
	

	public TipoEspacio getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoEspacio tipo) {
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(Integer capacidad) {
		chequeaCapacidad(capacidad);
		this.capacidad = capacidad;
	}
	
	public Integer getPlanta() {
		return planta;
	}
	
	public String toString() {
		return nombre + " (planta "+planta+")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, planta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Espacio))
			return false;
		Espacio other = (Espacio) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(planta, other.planta);
	}
	
	
	public int compareTo(Espacio e) {
		int res = this.planta.compareTo(e.planta);
		if (res == 0) {
			res = this.nombre.compareTo(e.nombre);
		}
		return res;
	}
}
