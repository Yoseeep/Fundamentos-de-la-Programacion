package fp.tipos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.Stream;

import utiles.Checkers;

public class FactoríaElectroTienda {
	
	public static ElectroTienda leerElectroTienda (String nombre, LocalDate fechaApertura, String ruta) {
		ElectroTienda res = null;
		try {
			Stream<Electrodoméstico> se = Files.lines(Path.of(ruta)).skip(1).
					map(FactoríaElectroTienda::parseaElectrodoméstico);
			res = new ElectroTienda(nombre, fechaApertura, se);
		} catch (Exception e) {
			System.out.println("No se ha encontado el fichero " + ruta);
		}
		return res;
	}

	private static Electrodoméstico parseaElectrodoméstico(String lineaCSV) {
		String[] tr = lineaCSV.split(";");
		Checkers.check("La línea está mal troceada: " + lineaCSV, tr.length == 5);
		String númeroSerie  = tr[0].trim();
		String denominación  = tr[1].trim();
		Integer mesesGarantía = Integer.valueOf(tr[2].trim());
		Float importe  = Float.valueOf(tr[3].trim());
		Float iva  = Float.valueOf(tr[4].trim());
		
		return new Electrodoméstico(númeroSerie, denominación, mesesGarantía, importe, iva);
	}
	
}
