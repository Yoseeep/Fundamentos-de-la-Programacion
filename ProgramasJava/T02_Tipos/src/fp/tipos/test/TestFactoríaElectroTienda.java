package fp.tipos.test;

import java.time.LocalDate;

import fp.tipos.ElectroTienda;
import fp.tipos.FactoríaElectroTienda;

public class TestFactoríaElectroTienda {

	public static void main(String[] args) {
		ElectroTienda et = FactoríaElectroTienda.leerElectroTienda("Tienda la Esquina", LocalDate.of(2023, 7, 13), "data/artículos.csv");
		System.out.println(et);
		System.out.println(et.getArtículos());

	}

}
