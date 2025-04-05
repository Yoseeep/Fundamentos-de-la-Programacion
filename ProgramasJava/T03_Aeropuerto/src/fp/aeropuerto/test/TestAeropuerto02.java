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
		System.out.println("¿Hay vuelo a Málaga? " + a.existeVueloADestino("Málaga"));
		System.out.println("¿Hay vuelo a Viena? " + a.existeVueloADestino("Viena"));
		System.out.println("¿Hay vuelo a Madrid? " + a.existeVueloADestino("Madrid"));
		System.out.println("¿Hay vuelo a Barcelona? " + a.existeVueloADestino("Barcelona"));
		System.out.println("¿Hay vuelo a Sevilla? " + a.existeVueloADestino("Sevilla"));
	}
	
	private static void testTodosLosVuelosCuestanMenosQue(Aeropuerto a) {
		System.out.println("¿Todos los vuelos cuestan menos que 1000? " + a.todosLosVueloCuestanMenosQue(1000d));
		System.out.println("¿Todos los vuelos cuestan menos que 50? " + a.todosLosVueloCuestanMenosQue(50d));
	}
	
	private static void testVueloMasDuracion (Aeropuerto a) {
		Vuelo vueloMayorDuracion = a.vueloMasDuracion();
		System.out.println("Vuelo con más duración: " + vueloMayorDuracion);
		System.out.println("Código del Vuelo con más duración: " + vueloMayorDuracion.codigo());
		System.out.println("Duración del Vuelo con más duración: " + Duration.between(vueloMayorDuracion.fechaHoraSalida(), vueloMayorDuracion.fechaHoraLlegada()));
	}
	
	private static void testCuentaVuelosPorDestino (Aeropuerto a) {
		Map<String, Integer> res = a.cuentaVuelosPorDestino();
		for (Entry<String, Integer> par : res.entrySet()) {
			System.out.println(par.getKey() + " ---> " + par.getValue());
		}
	}
	
	private static void testDistintosDestinosPorCompañia(Aeropuerto a) {
		Map< Compañía,Set<Vuelo> > res = a.distintosDestinosPorCompañia();
		for (Entry<Compañía, Set<Vuelo>> par : res.entrySet()) {
			System.out.println(par.getKey() + " ---> " + par.getValue());
		}
	}
	
	private static void testDistintosDestinosVuelosCompletosPorCompañía (Aeropuerto a) {
		Map< Compañía,Set<Vuelo> > res = a.distintosDestinosVuelosCompletosPorCompañia();
		for (Entry<Compañía, Set<Vuelo>> par : res.entrySet()) {
			System.out.println(par.getKey() + " ---> " + par.getValue());
		}
	}
	
	
	
}
