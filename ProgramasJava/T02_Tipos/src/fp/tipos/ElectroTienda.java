package fp.tipos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class ElectroTienda {
	private String nombre;
	private LocalDate fechaApertura;
	private List<Electrodoméstico> artículos;
	
	
	public ElectroTienda(String nombre, LocalDate fechaApertura, Stream<Electrodoméstico> artículos) {
		this.nombre = nombre;
		this.fechaApertura = fechaApertura;
		this.artículos = artículos.toList();
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public LocalDate getFechaApertura() {
		return fechaApertura;
	}
	public List<Electrodoméstico> getArtículos() {
		return List.copyOf(artículos);
	}



	public String toString() {
		return nombre + " (" + fechaApertura + ") - Nº de artículos: " + artículos.size();
	}
	
	
	
	
}
