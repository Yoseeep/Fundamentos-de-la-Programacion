package geometría;

public class CircunferenciaImpl4 implements Circunferencia{
	private Punto centro;
	private Punto puntoPerteneciente;
	
	public CircunferenciaImpl4(Punto centro, Double radio) {
		this.centro = centro;
		this.puntoPerteneciente = new Punto(centro.getX(), centro.getY() + radio);
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}

	public Double getRadio() {
		return centro.distancia(puntoPerteneciente);
	}

	public void setRadio(Double radio) {
		puntoPerteneciente.setY(centro.getY() + radio);
		// Otra forma: this.puntoPerteneciente = new Punto(centro.getX(), centro.getY() + radio);
	}
	
	@Override
	public String toString() {
		return centro + " R:" + getRadio();
	}
	
	public Double longitud() {
		return 2 * Math.PI * getRadio();
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
