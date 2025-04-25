package fp.aeropuerto.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import fp.aeropuerto.Aeropuerto;
import fp.aeropuerto.Compañía;
import fp.aeropuerto.FactoriaAeropuerto;
import fp.aeropuerto.Persona;
import fp.aeropuerto.Vuelo;

public class TestAeropuerto02 {

	public static void main(String[] args) {
		
		Aeropuerto aeropuerto = FactoriaAeropuerto.leerAeropuerto("San Pablo", "Sevilla", "data/vuelos.csv");
		
		visualizaAeropuerto(aeropuerto);
		
		testExisteVueloADestino(aeropuerto);
		
		testTodosLosVuelosCuestanMenosQue(aeropuerto);
		
		testVueloMasDuracion(aeropuerto);
		
		testCuentaVuelosPorDestino(aeropuerto);
		
		testDistintosDestinosPorCompañia(aeropuerto);
		
		testDistintosDestinosVuelosCompletosPorCompañía(aeropuerto);
		
		testVuelosPorOrdenNatural(aeropuerto);
		
		testMáximoVueloPorOrdenNatural(aeropuerto);
		
		testVuelosPorInversoAlOrdenNatural(aeropuerto);
		
		testVuelosPorPrecioYHoraSalida(aeropuerto);
		
		testVuelosPorDuraciónYMayorNroPasajeros(aeropuerto);
		
		testPasajerosDePrimerVueloPorNombreYDni(aeropuerto);
		
		testDiferentesNombresDePasajerosPorOrdenAlfabéticoInverso(aeropuerto);
		
		testPasajerosDeTodosLosVuelosPorApellidosYNombre(aeropuerto);
		
		testPasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre(aeropuerto);
	}
	
	private static void visualizaAeropuerto(Aeropuerto aeropuerto) {
		try {
			System.out.println(aeropuerto);
			for (Vuelo v: aeropuerto.getVuelos()) {
				System.out.println(v.codigo() + " -> " + v.pasajeros());
			}
		} catch (Exception e) {
			System.out.println("Error al visualizar el aeropuerto: " + e.getMessage());
		}
		
	}
	
	private static void testExisteVueloADestino(Aeropuerto a) {
		System.out.println("\ntestExisteVueloADestino");
		System.out.println("¿Hay vuelo a Málaga? " + a.existeVueloADestino("Málaga"));
		System.out.println("¿Hay vuelo a Viena? " + a.existeVueloADestino("Viena"));
		System.out.println("¿Hay vuelo a Madrid? " + a.existeVueloADestino("Madrid"));
		System.out.println("¿Hay vuelo a Barcelona? " + a.existeVueloADestino("Barcelona"));
		System.out.println("¿Hay vuelo a Sevilla? " + a.existeVueloADestino("Sevilla"));
	}
	
	private static void testTodosLosVuelosCuestanMenosQue(Aeropuerto a) {
		System.out.println("\ntestTodosLosVuelosCuestanMenosQue");
		System.out.println("¿Todos los vuelos cuestan menos que 1000? " + a.todosLosVueloCuestanMenosQue(1000d));
		System.out.println("¿Todos los vuelos cuestan menos que 50? " + a.todosLosVueloCuestanMenosQue(50d));
	}
	
	private static void testVueloMasDuracion (Aeropuerto a) {
		System.out.println("\ntestVueloMasDuracion");
		Vuelo vueloMayorDuracion = a.vueloMasDuracion();
		System.out.println("Vuelo con más duración: " + vueloMayorDuracion);
		System.out.println("Código del Vuelo con más duración: " + vueloMayorDuracion.codigo());
		System.out.println("Duración del Vuelo con más duración: " + Duration.between(vueloMayorDuracion.fechaHoraSalida(), vueloMayorDuracion.fechaHoraLlegada()));
	}
	
	private static void testCuentaVuelosPorDestino (Aeropuerto a) {
		System.out.println("\ntestCuentaVuelosPorDestino");
		Map<String, Integer> res = a.cuentaVuelosPorDestino();
		for (Entry<String, Integer> par : res.entrySet()) {
			System.out.println(par.getKey() + " ---> " + par.getValue());
		}
	}
	
	private static void testDistintosDestinosPorCompañia(Aeropuerto a) {
		System.out.println("\ntestDistintosDestinosPorCompañia");
		Map< Compañía,Set<Vuelo> > res = a.distintosDestinosPorCompañia();
		for (Entry<Compañía, Set<Vuelo>> par : res.entrySet()) {
			System.out.println(par.getKey() + " ---> " + par.getValue());
		}
	}
	
	private static void testDistintosDestinosVuelosCompletosPorCompañía (Aeropuerto a) {
		System.out.println("\ntestDistintosDestinosVuelosCompletosPorCompañía");
		Map< Compañía,Set<Vuelo> > res = a.distintosDestinosVuelosCompletosPorCompañia();
		for (Entry<Compañía, Set<Vuelo>> par : res.entrySet()) {
			System.out.println(par.getKey() + " ---> " + par.getValue());
		}
	}
	
	
	private static void testVuelosPorOrdenNatural(Aeropuerto a) {
		try {
			System.out.println("\ntestVuelosPorOrdenNatural");
			for (Vuelo v: a.vuelosPorOrdenNatural()) {
				System.out.println(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMáximoVueloPorOrdenNatural(Aeropuerto a) {
		try {
			System.out.println("\ntestmáximoVueloPorOrdenNatural");
			System.out.println(a.máximoVueloPorOrdenNatural());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testVuelosPorInversoAlOrdenNatural(Aeropuerto a) {
		try {
			System.out.println("\ntestVuelosPorInversoAlOrdenNatural");
			for (Vuelo v: a.vuelosPorInversoAlOrdenNatural()) {
				System.out.println(v);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testVuelosPorPrecioYHoraSalida(Aeropuerto a) {
		try {
			System.out.println("\ntestVuelosPorPrecioYHoraSalida");
			for (Vuelo v: a.vuelosPorPrecioYHoraSalida()) {
				System.out.println("Precio=" + v.precio() + ",HoraSalida=" + v.fechaHoraSalida().toLocalTime() + " --> " + v);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testVuelosPorDuraciónYMayorNroPasajeros(Aeropuerto a) {
		try {
			System.out.println("\ntestVuelosPorDuraciónYMayorNroPasajeros");
			for (Vuelo v: a.vuelosPorDuraciónYMayorNroPasajeros()) {
				System.out.println("Duración=" + v.duracion() + ",NroPasajero=" + v.numeroPasajeros() + " --> " + v);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPasajerosDePrimerVueloPorNombreYDni(Aeropuerto a) {
		try {
			System.out.println("\ntestPasajerosDePrimerVueloPorNombreYDni");
			for (Persona p: a.pasajerosDePrimerVueloPorNombreYDni()) {
				System.out.println("Nombre=" + p.nombre() + ",DNI=" + p.dni() + " --> " + p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void testDiferentesNombresDePasajerosPorOrdenAlfabéticoInverso(Aeropuerto a) {
		try {
			System.out.println("\ntestDiferentesNombresDePasajerosPorOrdenAlfabéticoInverso");
			for (String s: a.diferentesNombresDePasajerosPorOrdenAlfabéticoInverso()) {
				System.out.println(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPasajerosDeTodosLosVuelosPorApellidosYNombre(Aeropuerto a) {
		try {
			System.out.println("\ntestPasajerosDeTodosLosVuelosPorApellidosYNombre");
			for (Persona p: a.pasajerosDeTodosLosVuelosPorApellidosYNombre()) {
				System.out.println("Apellido=" + p.apellidos() + ",Nombre=" + p.nombre() + " --> " + p);
			}
			System.out.println("Nros: "+a.pasajerosDeTodosLosVuelosPorApellidosYNombre().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre(Aeropuerto a) {
		try {
			System.out.println("\ntestPasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre");
			for (Persona p: a.pasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre()) {
				System.out.println("Apellido=" + p.apellidos() + ",Nombre=" + p.nombre() + " --> " + p);
			}
			System.out.println("Nros: "+a.pasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
