package fp.vinos;

import fp.utiles.Checkers;

public record Vino(String pais, String region, Integer puntos, Double precio, String uva) {
	
	public Vino{
		Checkers.check("Los puntos deben estar entre 0 y 100", 0 <= puntos && puntos <= 100);
		Checkers.check("El precio debe ser mayor que 0", 0 <= precio);
	}
	
	
	public Double calidadPrecio() {
		return this.puntos / this.precio;
	}
	
	
	
}
