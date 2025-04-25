package fp.vinos;

import java.util.Collection;
import java.util.Set;

public interface Vinoteca {
	void agregarVino(Vino v);
	void eliminarVino(Vino v);
	int obtenerNumeroVinos();
	boolean contieneVino(Vino v);
	void agregaVinos(Collection<Vino> vinos);
	boolean contieneVinos(Collection<Vino> vinos);
	
	
	int calcularNumeroVinosDePais(String pais);
	Collection<Vino> obtenerVinosRangoPuntos(int inf, int sup);
}
