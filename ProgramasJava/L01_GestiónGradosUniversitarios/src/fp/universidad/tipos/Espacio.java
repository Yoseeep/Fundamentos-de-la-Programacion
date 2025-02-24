package fp.universidad.tipos;

public class Espacio {
	TipoEspacio tipo;
	String nombre;
	Integer capacidad;
	Integer planta;
	
	
	public Espacio(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.planta = planta;
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
		this.capacidad = capacidad;
	}
	
	public Integer getPlanta() {
		return planta;
	}
	
	public String toString() {
		return nombre + " (planta "+planta+")";
	}
}
