package geometría;

public class CircunferenciaImpl3 implements Circunferencia{
	private Punto centro;
	private Double longitud;
	
	public CircunferenciaImpl3(Punto centro, Double radio) {
		this.centro = centro;
		this.longitud = 2 * Math.PI * radio;
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}

	public Double getRadio() {
		return longitud / (2 * Math.PI);
	}

	public void setRadio(Double radio) {
		this.longitud = 2 * Math.PI * radio;
	}
	
	@Override
	public String toString() {
		return centro + " R:" + getRadio();
	}
	
	public Double longitud() {
		return longitud;
	}
	
	public Double área() {
		return Math.PI * Math.pow(getRadio(), 2);
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		
		if (o instanceof Circunferencia) {
			Circunferencia c = (Circunferencia) o;
			res =  c.getCentro().equals( this.getCentro()) && 
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
