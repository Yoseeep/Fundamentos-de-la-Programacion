package fp.vinos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class FactoriaVinos {
	public static Vinoteca leerVinoteca(String ruta) {
		Vinoteca res = null;
		try {
			// A partir de flujo:
			// Stream<Vino> sv = Files.lines(Path.of(ruta)).skip(1).map(FactoriaVinos::parsearVino);
			
			// A partir de una colección:
			Collection<Vino> cv = Files.lines(Path.of(ruta)).skip(1).map(FactoriaVinos::parsearVino).toList();
			
			res = new VinotecaBucles(cv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static Vino parsearVino(String linea) {
		String[] res = linea.split(",");
		String pais = res[0].trim();
		String region = res[1].trim();
		Integer puntos = Integer.valueOf(res[2].trim());
		Double precio = Double.valueOf(res[3].trim());
		String uva = res[4].trim();
		
		return new Vino(pais, region, puntos, precio, uva);
	}
}
