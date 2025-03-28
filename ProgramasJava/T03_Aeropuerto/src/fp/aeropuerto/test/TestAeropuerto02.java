package fp.aeropuerto.test;

import java.util.List;

import fp.aeropuerto.Aeropuerto;
import fp.aeropuerto.FactoriaAeropuerto;
import fp.aeropuerto.Vuelo;

public class TestAeropuerto02 {

	public static void main(String[] args) {
		
		Aeropuerto aeropuerto = FactoriaAeropuerto.leerAeropuerto("San Pablo", "Sevilla", "data/vuelos.csv");
		System.out.println(aeropuerto);
		for (Vuelo v: aeropuerto.getVuelos()) {
			System.out.println(v.codigo() + " -> " + v.pasajeros());
		}
		

	}

}
