package fp.hamburgueserias;

import fp.utiles.Checkers;

public record Evaluacion(String hamburgueseria, Integer presentacion, 
		Integer puntoCarne, Integer calidadIngredientes, Integer calidadPan) {
	
	public Evaluacion{
		Checkers.check("El valor de valoración de la presentación debe estar comprendido entre 0 y 10.", 
				0 <= presentacion && presentacion <= 10);
		Checkers.check("El valor de valoración del punto de la carne debe estar comprendido entre 0 y 10.", 
				0 <= puntoCarne && puntoCarne <= 10);
		Checkers.check("El valor de valoración de la calidad de los ingredientes debe estar comprendido entre 0 y 10.", 
				0 <= calidadIngredientes && calidadIngredientes <= 10);
		Checkers.check("El valor de valoración de la calidad del pan debe estar comprendido entre 0 y 10.", 
				0 <= calidadPan && calidadPan <= 10);
	}
	
	public Double puntuacionFinal() {
		return (this.presentacion + this.puntoCarne 
				+ this.calidadIngredientes + calidadPan) 
				/ 4d;
	}
	
	
}
