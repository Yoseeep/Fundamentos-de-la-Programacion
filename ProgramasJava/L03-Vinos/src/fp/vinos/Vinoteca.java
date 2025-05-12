package fp.vinos;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public interface Vinoteca {
	void agregarVino(Vino v);

	void eliminarVino(Vino v);

	int obtenerNumeroVinos();

	boolean contieneVino(Vino v);

	void agregaVinos(Collection<Vino> vinos);

	boolean contieneVinos(Collection<Vino> vinos);

	int calcularNumeroVinosDePais(String pais);

	Collection<Vino> obtenerVinosRangoPuntos(int inf, int sup);

	int calcularNumeroVinosDePaisConPuntuacionSuperior(String pais, Integer umbralPuntuacion);

	Set<Vino> obtenerVinosBaratos(Double umbraPrecio);

	boolean existeVinoDeUvaEnRegion(String region, String uva);

	Set<String> calcularUvasDeRegion(String region);

	int calcularTotalPuntosVinosDeRegion(String region);

	double calcularMediaPuntosVinosDeUva(String uva);

	Vino obtenerVinoMejorPuntuado();

	Vino obtenerVinoMejorPuntuadoDePais(String pais);

	List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region, int n);

	Map<String, List<Vino>> agruparVinosPorPais();

	Map<String,Set<String>> agruparUvasPorPais();

	Map<String,Long> calcularCalidadPrecioPorRegionMayorDe(Double umbralCalidadPrecio);

	Map<String,Vino> calcularVinoMasCaroPorPais();

	SortedMap<String,List<Vino>> calcularNMejoresVinosPorPais(int n);

	String calcularRegionConMejoresVinos(Double umbralCalidadPrecio);
}
