package calcularAreaTrapecio;

public class AreaTrapecio {

	public static void main(String[] args) {
		double b1 = 3; // Base 1
		double b2 = 5.3; // Base 2
		double h = 8.9; // Altura

		double a = ((b1 + b2) * h) / 2.0; // Área
		System.out.println("b1 = " + b1 + ", b2 = " + b2 + ", h = " + h);
		System.out.println("Área del trapecio: " + a);
	}

}
