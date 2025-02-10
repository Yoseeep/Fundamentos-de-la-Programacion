package geometría;

public class Punto {
	private Double x;
	private Double y;

	public Punto(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Punto() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public Double distancia(Punto p) {
		return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Punto) {
			Punto p = (Punto) o;
			res = this.getX().equals(p.getX()) && this.getY().equals(p.getY());
		}
		return res;
	}
	
	public int hashCode(){
		return 11*this.getX().hashCode() + 57*this.getY().hashCode();
	}

}
