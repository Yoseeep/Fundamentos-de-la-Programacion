package geometría;

public interface Circunferencia extends Comparable<Circunferencia>{
	Punto getCentro();
	Double getRadio();
	void setCentro(Punto centro);
	void setRadio(Double radio);
	Double longitud();
	Double área();
	int compareTo(Circunferencia circunferencia);
	
	
}
