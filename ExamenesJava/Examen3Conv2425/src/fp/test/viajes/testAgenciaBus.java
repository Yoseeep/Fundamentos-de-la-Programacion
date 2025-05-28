package fp.test.viajes;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import fp.viajes.AgenciaBus;
import fp.viajes.FactoriaViajes;
import fp.viajes.TipoViaje;
import fp.viajes.Viaje;

public class testAgenciaBus {

	public static void main(String[] args) {
		AgenciaBus agenciaBus = FactoriaViajes.leeAgenciaBus("agenciaBus", "data/viajes.csv");

		testLectura();

		testGetMaximaDuracion(agenciaBus);

		testAñadirTiempoDescanso(agenciaBus);

		testGetDuracionMinimaPorDestino(agenciaBus);

		testGetViajesPorParadas(agenciaBus);
		
		testGetPrecioMedioViajesPorParada(agenciaBus);
	}

	private static void testLectura() {
		System.out.println("\nTest lectura ==========================================================================");
		AgenciaBus agenciaBus = FactoriaViajes.leeAgenciaBus("agenciaBus", "data/viajes.csv");
		System.out.println("Se han leido " + agenciaBus.getViajes().size() + " viajes");
		System.out.println("Los 3 primeros son:");
		for (Viaje viaje : agenciaBus.getViajes().subList(0, 3)) {
			System.out.println(viaje);
		}
	}

	private static void testGetMaximaDuracion(AgenciaBus a) {
		System.out.println("\nTest 4.a ==============================================================================");
		Duration resultado = a.getMaximoDuracion();
		System.out.println("La duración máxima es " + resultado);
	}

	private static void testAñadirTiempoDescanso(AgenciaBus a) {
		System.out.println("\nTest 4.b ==============================================================================");
		System.out.println("Se van a añadir 30 minutos a a los viajes con la parada Bilbao, que son");
		List<Viaje> viajes = a.getViajes().stream().filter(v -> v.intermedias().contains("Bilbao")).toList();
		System.out.println(viajes);
		a.añadirTiempoDescanso("Bilbao", 30);
		System.out.println("\nLos viajes tras la modificación son");
		List<Viaje> viajesModificados = a.getViajes().stream().filter(v -> v.intermedias().contains("Bilbao")).toList();
		System.out.println(viajesModificados);
	}

	private static void testGetDuracionMinimaPorDestino(AgenciaBus a) {
		System.out.println("\nTest 4.c ==============================================================================");
		SortedMap<String, Duration> resultado = a.getDuracionMinimaPorDestino(TipoViaje.TRANSBORDO);
		System.out.println("La duración minima por cada destino para viajes de tipo TRANSBORDO ES");
		for (Entry<String, Duration> par : resultado.entrySet()) {
			System.out.println(par.getKey() + "=" + par.getValue());
		}
		SortedMap<String, Duration> resultado1 = a.getDuracionMinimaPorDestino(TipoViaje.DIRECTO);
		System.out.println("La duración minima por cada destino para viajes de tipo DIRECTO ES");
		for (Entry<String, Duration> par : resultado1.entrySet()) {
			System.out.println(par.getKey() + "=" + par.getValue());
		}
	}

	private static void testGetViajesPorParadas(AgenciaBus a) {
		System.out.println("\nTest 4.d ==============================================================================");
		Map<String, Set<Viaje>> resultado = a.getViajesPorParadas(20d);
		System.out.println("Hay " + resultado.size() + " paradas con viajes con precio inferior a 20,00 Euros son");
		for (Entry<String, Set<Viaje>> par : resultado.entrySet()) {
			System.out.println(par.getKey() + "=" + par.getValue());
		}
		Map<String, Set<Viaje>> resultado1 = a.getViajesPorParadas(null);
		System.out.println("Hay " + resultado1.size() + " paradas con viajes con precio inferior a null Euros son");
		for (Entry<String, Set<Viaje>> par : resultado1.entrySet()) {
			System.out.println(par.getKey() + "=" + par.getValue());
		}

	}
	
	private static void testGetPrecioMedioViajesPorParada(AgenciaBus a) {
		System.out.println("\nTest 4.e ==============================================================================");
		Map<String, Double> resultado = a.getPrecioMedioViajesPorParada();
		System.out.println("El precio medio de los viajes por parada es");
		for (Entry<String, Double> par : resultado.entrySet()) {
			System.out.println(par.getKey() + "=" + par.getValue());
		}
		
	}

}
