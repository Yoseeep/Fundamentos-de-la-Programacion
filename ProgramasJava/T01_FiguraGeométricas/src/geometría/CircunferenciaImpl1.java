package geometría;

public class CircunferenciaImpl1 implements Circunferencia {
	private Punto centro;
	private Double radio;
	
	public CircunferenciaImpl1(Punto centro, Double radio) {
		if (radio<=0) {
			throw new IllegalArgumentException
			("El radio debe ser positivo");
			}
		
		this.centro = centro;
		this.radio = radio;
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}

	public Double getRadio() {
		return radio;
	}

	public void setRadio(Double radio) {
		if (radio<=0) {
			throw new IllegalArgumentException
			("El radio debe ser positivo");
			}
		
		this.radio = radio;
	}

	@Override
	public String toString() {
		return centro + " R:" + radio;
	}
	
	public Double longitud() {
		return 2 * Math.PI * radio;
	}
	
	public Double área() {
		return Math.PI * Math.pow(radio, 2);
	}
	
	
	public boolean equals(Object o) {
		boolean res = false;
		
		if (o instanceof Circunferencia) {
			Circunferencia c = (Circunferencia) o;
			res =  c.getCentro().equals(this.getCentro()) && 
					c.getRadio().equals(this.getRadio());
		} 
		
		return res;
	}
	
	
	public int hashCode() {
		return 11*this.getCentro().hashCode() + 51*this.getRadio().hashCode();
		
	}

	
	public int compareTo(Circunferencia circunferencia) {
		return this.getRadio().compareTo(circunferencia.getRadio());
	}

}
