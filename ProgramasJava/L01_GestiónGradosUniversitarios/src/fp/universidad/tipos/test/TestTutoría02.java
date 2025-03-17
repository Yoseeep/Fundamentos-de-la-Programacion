package fp.universidad.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.universidad.tipos.Tutoria;

public class TestTutoría02 {

	public static void main(String[] args) {
		testConstructor1();

	}
	
	private static void testConstructor1() {
		System.out.println("\ntestConstructorCorrecto");
		try {
			Tutoria t=new Tutoria(DayOfWeek.WEDNESDAY, LocalTime.of(10,30), LocalTime.of(12,30));
			System.out.println(t);
			System.out.println("Dia de la semana: "+t.diaSemana());
			System.out.println("Hora comienzo: "+t.horaComienzo());
			System.out.println("Hora fin: "+t.horaFin());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructorIncorrecto");
		try {
			Tutoria t=new Tutoria(DayOfWeek.SATURDAY, LocalTime.of(10,30), LocalTime.of(12,30));
			System.out.println(t);
			System.out.println("Dia de la semana: "+t.diaSemana());
			System.out.println("Hora comienzo: "+t.horaComienzo());
			System.out.println("Hora fin: "+t.horaFin());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("\ntestConstructorIncorrecto");
		try {
			Tutoria t=new Tutoria(DayOfWeek.WEDNESDAY, LocalTime.of(10,30), LocalTime.of(10,35));
			System.out.println(t);
			System.out.println("Dia de la semana: "+t.diaSemana());
			System.out.println("Hora comienzo: "+t.horaComienzo());
			System.out.println("Hora fin: "+t.horaFin());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
