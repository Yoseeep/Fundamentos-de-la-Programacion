package fp.aeropuerto.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Map.Entry;
import java.util.Optional;

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
		
		testNúmeroVuelosADestino(aeropuerto);
		
		testNúmeroPasajerosADestino(aeropuerto);
		
		testVueloMenorRecaudaciónVuelosCompletos(aeropuerto);
		
		testCódigoDeAlgúnVueloADestinoConPlazasLibres(aeropuerto);
		
		testExisteVueloPrecioMenorQue(aeropuerto);
		
		testPromedioPreciosVuelosCompletos(aeropuerto);
		
		testSumaPreciosDistintosVuelosCompletos(aeropuerto);
		
		testContarDistintosPasajeros(aeropuerto);
		
		testMapListaVuelosPorDestinos(aeropuerto);
		
		testMapSetVuelosPorFechaLlegada(aeropuerto);
		
		testMapSetOrdenadoVuelosPorFechaSalida(aeropuerto);
		
		testMapNumVuelosCompletosPorCompañia(aeropuerto);
		
		testMapPreciosDiferentesOrdenadosPorDestino(aeropuerto);
		
		testMapPrecioMedioPorCompañia(aeropuerto);
		
		testMapPrecioVuelosConEscalasMásBaratoPorDestino(aeropuerto);
		
		testMapSumaPlazasLibresPorHoraDeLlegada(aeropuerto);
		
		testDestinoMayorNúmeroDeplazasLibres(aeropuerto);
		
		testPromediosDePasajerosPorFechasDeSalida(aeropuerto);
		
		testPromediosDePasajerosPorFechasDeSalida2(aeropuerto);
		
		testMapDestinosPorDuración(aeropuerto);
		
		testMapPorcentajeVuelosPorDestino(aeropuerto);
		
		testCompañíaConMayorSumaDePlazasLibres(aeropuerto);
		
		testSegundoDíaConMenosVuelos(aeropuerto);
		
		testMapPreciosSuperioresPorFechas(aeropuerto);
		
		testDestinoConMayorDiferenciaDePrecio(aeropuerto);
		
		testFechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos(aeropuerto);
		
		testTopNMediaPreciosPorDestino(aeropuerto);
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
	
	private static void testNúmeroVuelosADestino(Aeropuerto a) {
		try {
			System.out.println("\ntestNúmeroVuelosADestino");
			System.out.println("Nº de  vuelos con destino Barcelona: "
								+ a.númeroVuelosADestino("Barcelona"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testNúmeroPasajerosADestino(Aeropuerto a) {
		try {
			System.out.println("\ntestNúmeroPasajerosADestino");
			System.out.println("Nº de pasajeros con vuelos destino Barcelona: "
								+ a.númeroPasajerosADestino("Barcelona"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testVueloMenorRecaudaciónVuelosCompletos(Aeropuerto a) {
		try {
			System.out.println("\ntestVueloMenorRecaudaciónVuelosCompletos");
			System.out.println("" + a.vueloMenorRecaudaciónVuelosCompletos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testCódigoDeAlgúnVueloADestinoConPlazasLibres(Aeropuerto a) {
		try {
			System.out.println("\ntestVueloMenorRecaudaciónVuelosCompletos");
			System.out.println("Málaga:" + a.códigoDeAlgúnVueloADestinoConPlazasLibres("Málaga"));
			System.out.println("Cuenca:" + a.códigoDeAlgúnVueloADestinoConPlazasLibres("Cuenca"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testExisteVueloPrecioMenorQue(Aeropuerto a) {
		try {
			System.out.println("\ntestExisteVueloPrecioMenorQue");
			System.out.println("100:" + a.existeVueloPrecioMenorQue(100.0));
			System.out.println("3:" + a.existeVueloPrecioMenorQue(3.0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPromedioPreciosVuelosCompletos(Aeropuerto a) {
		try {
			System.out.println("\ntestPromedioPreciosVuelosCompletos");
			System.out.println("" + a.promedioPreciosVuelosCompletos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testSumaPreciosDistintosVuelosCompletos(Aeropuerto a) {
		try {
			System.out.println("\ntestSumaPreciosDistintosVuelosCompletos");
			System.out.println("" + a.sumaPreciosDistintosVuelosCompletos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testContarDistintosPasajeros(Aeropuerto a) {
		try {
			System.out.println("\ntestContarDistintosPasajeros");
			System.out.println("" + a.contarDistintosPasajeros());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapListaVuelosPorDestinos(Aeropuerto a) {
		try {
			System.out.println("\ntestMapListaVuelosPorDestinos");
			Map<String,List<Vuelo>> resultado = a.mapListaVuelosPorDestinos(); 
			for(Entry<String, List<Vuelo>> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapSetVuelosPorFechaLlegada(Aeropuerto a) {
		try {
			System.out.println("\ntestMapSetVuelosPorFechaLlegada");
			Map<LocalDate,Set<Vuelo>> resultado = a.mapSetVuelosPorFechaLlegada(); 
			for(Entry<LocalDate, Set<Vuelo>> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapSetOrdenadoVuelosPorFechaSalida(Aeropuerto a) {
		try {
			System.out.println("\ntestMapSetOrdenadoVuelosPorFechaSalida");
			Map<LocalDate,SortedSet<Vuelo>> resultado = a.mapSetOrdenadoVuelosPorFechaSalida(); 
			for(Entry<LocalDate, SortedSet<Vuelo>> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapNumVuelosCompletosPorCompañia(Aeropuerto a) {
		try {
			System.out.println("\ntestMapNumVuelosCompletosPorCompañia");
			Map<Compañía,Integer> resultado = a.mapNumVuelosCompletosPorCompañia(); 
			for(Entry<Compañía, Integer> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapPreciosDiferentesOrdenadosPorDestino(Aeropuerto a) {
		try {
			System.out.println("\ntestMapPreciosDiferentesOrdenadosPorDestino");
			Map<String, SortedSet<Double>> resultado = a.mapPreciosDiferentesOrdenadosPorDestino(); 
			for(Entry<String, SortedSet<Double>> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapPrecioMedioPorCompañia(Aeropuerto a) {
		try {
			System.out.println("\ntestMapPrecioMedioPorCompañia");
			Map<Compañía, Double> resultado = a.mapPrecioMedioPorCompañia(); 
			for(Entry<Compañía, Double> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void testMapPrecioVuelosConEscalasMásBaratoPorDestino(Aeropuerto a) {
		try {
			System.out.println("\ntestMapPrecioVuelosConEscalasMásBaratoPorDestino");
			Map<String, Double> resultado = a.mapPrecioVuelosConEscalasMásBaratoPorDestino(); 
			for(Entry<String, Double> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapSumaPlazasLibresPorHoraDeLlegada(Aeropuerto a) {
		try {
			System.out.println("\ntestMapSumaPlazasLibresPorHoraDeLlegada");
			Map<LocalTime, Integer> resultado = a.mapSumaPlazasLibresPorHoraDeLlegada(Compañía.IBE); 
			for(Entry<LocalTime, Integer> i: resultado.entrySet()) {
				System.out.println("Clave: " + i.getKey() + ", Valor: " + i.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testDestinoMayorNúmeroDeplazasLibres(Aeropuerto a) {
		try {
			System.out.println("\ntestDestinoMayorNúmeroDeplazasLibres");
			String resultado = a.destinoMayorNúmeroDeplazasLibres(); 
			System.out.println(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPromediosDePasajerosPorFechasDeSalida(Aeropuerto a) {
		try {
			System.out.println("\ntestPromediosDePasajerosPorFechasDeSalida");
			List<Double> resultado = a.promediosDePasajerosPorFechasDeSalida(); 
			System.out.println(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPromediosDePasajerosPorFechasDeSalida2(Aeropuerto a) {
		try {
			System.out.println("\ntestPromediosDePasajerosPorFechasDeSalida2");
			List<Entry<LocalDate, Double>> resultado = a.promediosDePasajerosPorFechasDeSalida2(); 
			for (Entry<LocalDate,Double> par : resultado) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapDestinosPorDuración(Aeropuerto a) {
		try {
			System.out.println("\ntestMapDestinosPorDuración");
			Map<Duration, SortedSet<String>> resultado = a.mapDestinosPorDuración(); 
			for (Entry<Duration, SortedSet<String>> par : resultado.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapPorcentajeVuelosPorDestino(Aeropuerto a) {
		try {
			System.out.println("\ntestMapPorcentajeVuelosPorDestino");
			System.out.println("-Para un precio de 60:");
			Map<String, Double> resultado = a.mapPorcentajeVuelosPorDestino(60d); 
			for (Entry<String, Double> par : resultado.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue() + "%");
			}
			System.out.println("-Para un precio de 1000:");
			Map<String, Double> resultado1 = a.mapPorcentajeVuelosPorDestino(1000d); 
			for (Entry<String, Double> par : resultado1.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue() + "%");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testCompañíaConMayorSumaDePlazasLibres(Aeropuerto a) {
		try {
			System.out.println("\ntestCompañíaConMayorSumaDePlazasLibres");
			Compañía resultado = a.compañíaConMayorSumaDePlazasLibres(); 
			System.out.println(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testSegundoDíaConMenosVuelos(Aeropuerto a) {
		try {
			System.out.println("\ntestSegundoDíaConMenosVuelos");
			LocalDate resultado = a.segundoDíaConMenosVuelos(); 
			System.out.println(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testMapPreciosSuperioresPorFechas(Aeropuerto a) {
		try {
			System.out.println("\ntestMapPreciosSuperioresPorFechas");
			Map<LocalDate, Set<Double>> resultado = a.mapPreciosSuperioresPorFechas(); 
			for (Entry<LocalDate, Set<Double>> par : resultado.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testDestinoConMayorDiferenciaDePrecio(Aeropuerto a) {
		try {
			System.out.println("\ntestDestinoConMayorDiferenciaDePrecio");
			String resultado = a.destinoConMayorDiferenciaDePrecio(); 
			System.out.println(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testFechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos(Aeropuerto a) {
		try {
			System.out.println("\ntestFechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos");
			Set<String> destinos = new HashSet<String>();
			destinos.add("Madrid");
			destinos.add("Madrid");
			LocalDate resultado = a.fechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos(destinos); 
			System.out.println(resultado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testTopNMediaPreciosPorDestino(Aeropuerto a) {
		try {
			System.out.println("\ntestTopNMediaPreciosPorDestino");
			SortedMap<String, Double> resultado = a.topNMediaPreciosPorDestino(4); 
			for (Entry<String, Double> par : resultado.entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
