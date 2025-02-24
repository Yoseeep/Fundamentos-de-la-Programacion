package geometría;

public class CircunferenciaImpl2 implements Circunferencia{
	private Punto centro;
	private Double área;
	
	public CircunferenciaImpl2(Punto centro, Double radio) {
		if (radio<=0) {
			throw new IllegalArgumentException
			("El radio debe ser positivo");
			}
		
		this.centro = centro;
		this.área = Math.PI * Math.pow(radio, 2);
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		this.centro = centro;
	}

	public Double getRadio() {
		return Math.sqrt( área / Math.PI );
	}

	public void setRadio(Double radio) {
		if (radio<=0) {
			throw new IllegalArgumentException
			("El radio debe ser positivo");
			}
		
		this.área = Math.PI * Math.pow(radio, 2);
	}
	
	@Override
	public String toString() {
		return centro + " R:" + getRadio();
	}
	
	public Double longitud() {
		return 2 * Math.PI * getRadio();
	}
	
	public Double área() {
		return área;
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
