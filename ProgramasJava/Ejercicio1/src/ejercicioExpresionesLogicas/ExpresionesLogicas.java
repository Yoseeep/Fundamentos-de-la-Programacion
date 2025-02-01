package ejercicioExpresionesLogicas;

import java.util.Scanner;

public class ExpresionesLogicas {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Apartado a)
		System.out.print("Apartado a)\nIntroduce un número: ");
		int numA = scanner.nextInt();
		boolean apartadoA = (1 <= numA && numA <= 5) || (numA == 9);
		System.out.println("Resultado apartado a): " + apartadoA);

		// Apartado b)
		System.out.print("Apartado b)\nIntroduce un número: ");
		int numB = scanner.nextInt();
		boolean apartadoB = numB % 3 == 0 && (20 <= numB && numB <= 30);
		System.out.println("Resultado apartado b): " + apartadoB);

		// Apartado c)
		System.out.print("Apartado c)\nIntroduce un número: ");
		System.out.print("Introduce un año: ");
		int año = scanner.nextInt();
		boolean apartadoC = año % 400 == 0 || (año % 4 == 0 && año % 100 != 0);
		System.out.println("Resultado apartado c): " + apartadoC);
		
		
		scanner.close();
	}

}
