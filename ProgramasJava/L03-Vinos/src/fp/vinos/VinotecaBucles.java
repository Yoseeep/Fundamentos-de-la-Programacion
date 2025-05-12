package fp.vinos;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class VinotecaBucles implements Vinoteca {
	private Set<Vino> vinos;

	public VinotecaBucles() {
		this.vinos = new HashSet<Vino>();
	}

	public VinotecaBucles(Collection<Vino> vinos) {
		this.vinos = Set.copyOf(vinos);
	}

	public VinotecaBucles(Stream<Vino> vinos) {
		this.vinos = Set.copyOf(vinos.toList());
	}

	public String toString() {
		return "Total vinos: " + obtenerNumeroVinos();
	}

	public int hashCode() {
		return Objects.hash(vinos);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VinotecaBucles other = (VinotecaBucles) obj;
		return Objects.equals(vinos, other.vinos);
	}

	public void agregarVino(Vino v) {
		if (v != null) {
			this.vinos.add(v);
		}
	}

	public void eliminarVino(Vino v) {
		this.vinos.remove(v);
	}

	public int obtenerNumeroVinos() {
		return this.vinos.size();
	}

	public boolean contieneVino(Vino v) {
		return this.vinos.contains(v);
	}

	public void agregaVinos(Collection<Vino> vinos) {
		this.vinos.addAll(vinos);
	}

	public boolean contieneVinos(Collection<Vino> vinos) {
		this.vinos.removeAll(vinos);
		return false;
	}

	public int calcularNumeroVinosDePais(String pais) {
		int cont = 0;
		for (Vino v : vinos) {
			if (v.pais().equals(pais)) {
				cont++;
			}
		}
		return cont;
	}

	public Collection<Vino> obtenerVinosRangoPuntos(int inf, int sup) {
		Checkers.check("El valor inferior debe ser menor o igual que el valor superior.", inf <= sup);
		List<Vino> res = new ArrayList<Vino>();
		for (Vino v : vinos) {
			if (inf <= v.puntos() && v.puntos() <= sup) {
				res.add(v);
			}
		}
		return res;
	}

	public int calcularNumeroVinosDePaisConPuntuacionSuperior(String pais, Integer umbralPuntuacion) {
		int acum = 0;
		for (Vino vino : vinos) {
			if (vino.pais().equals(pais) && vino.puntos() > umbralPuntuacion) {
				acum++;
			}
		}

		return acum;
	}

	public Set<Vino> obtenerVinosBaratos(Double umbralPrecio) {
		Set<Vino> res = new HashSet<Vino>();
		for (Vino vino : vinos) {
			if (vino.precio() < umbralPrecio) {
				res.add(vino);
			}
		}
		return res;
	}

	public boolean existeVinoDeUvaEnRegion(String uva, String region) {
		boolean res = false;
		for (Vino vino : vinos) {
			if (vino.region().equals(region) && vino.uva().equals(uva)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public Set<String> calcularUvasDeRegion(String region) {
		Set<String> res = new HashSet<String>();
		for (Vino vino : vinos) {
			if (vino.region().equals(region)) {
				res.add(vino.uva());
			}
		}
		return res;
	}

	public int calcularTotalPuntosVinosDeRegion(String region) {
		int res = 0;
		for (Vino vino : vinos) {
			if (vino.region().equals(region)) {
				res++;
			}
		}
		return res;
	}

	public double calcularMediaPuntosVinosDeUva(String uva) {
		double acumPuntos = 0d;
		double cantidad = 0d;
		for (Vino vino : vinos) {
			if (vino.uva().equals(uva)) {
				acumPuntos += vino.puntos();
				cantidad++;
			}
		}
		return cantidad == 0.0 ? 0.0 : acumPuntos / cantidad;
	}

	public Vino obtenerVinoMejorPuntuado() {
		Vino res = null;
		for (Vino vino : vinos) {
			if (res == null || vino.puntos() > res.puntos()) {
				res = vino;
			}
		}
		if (res == null) {
			throw new NoSuchElementException("No hay vinos en la vinoteca");
		}
		return res;
	}

	public Vino obtenerVinoMejorPuntuadoDePais(String pais) {
		Vino res = null;
		for (Vino vino : vinos) {
			if (vino.pais().equals(pais)) {
				if (res == null || vino.puntos() > res.puntos()) {
					res = vino;
				}
			}
		}
		if (res == null) {
			throw new NoSuchElementException("No hay vinos en la vinoteca");
		}
		return res;
	}

	public List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region, int n) {
		List<Vino> res = new ArrayList<Vino>();
		for (Vino vino : vinos) {
			if (vino.region().equals(region)) {
				res.add(vino);
			}
		}
		res.sort(Comparator.comparingDouble(Vino::precio));
		int tamaño = n > res.size() ? res.size() : n;
		return res.subList(0, tamaño);
	}

	public Map<String, List<Vino>> agruparVinosPorPais() {
		Map<String, List<Vino>> res = new HashMap<String, List<Vino>>();
		for (Vino vino : vinos) {
			if (!res.containsKey(vino.pais())) {
				res.put(vino.pais(), new ArrayList<Vino>());
			}
			res.get(vino.pais()).add(vino);
		}
		return res;
	}

	public Map<String, Set<String>> agruparUvasPorPais() {
		Map<String, Set<String>> res = new HashMap<String, Set<String>>();
		for (Vino vino : vinos) {
			if (!res.containsKey(vino.pais())) {
				res.put(vino.pais(), new HashSet<String>());
			}
			res.get(vino.pais()).add(vino.uva());
		}
		return res;
	}

	public Map<String, Long> calcularCalidadPrecioPorRegionMayorDe(Double umbralCalidadPrecio) {
		Map<String, Long> res = new HashMap<String, Long>();
		for (Vino vino : vinos) {
			if (!res.containsKey(vino.region())) {
				res.put(vino.region(), 0L);
			}
			if (vino.calidadPrecio() > umbralCalidadPrecio){
				res.put(vino.region(), res.get(vino.region()) + 1);
			}
		}
		return res;
	}

	public Map<String, Vino> calcularVinoMasCaroPorPais() {
		Map<String, Vino> res = new HashMap<String, Vino>();
		for (Vino vino : vinos) {
			if (!res.containsKey(vino.pais())) {
				res.put(vino.pais(), vino);
			} else if (vino.precio() > res.get(vino.pais()).precio()) {
				res.put(vino.pais(), vino);
			}
		}
		return res;
	}

	public SortedMap<String, List<Vino>> calcularNMejoresVinosPorPais(int n) {
		SortedMap<String, List<Vino>> res = new TreeMap<String, List<Vino>>();
		for (Vino vino : vinos) {
			if (!res.containsKey(vino.pais())) {
				res.put(vino.pais(), new ArrayList<Vino>());
			}
			res.get(vino.pais()).add(vino);
		}
		for (String pais : res.keySet()) {
			res.get(pais).sort(Comparator.comparingDouble(Vino::puntos).reversed());
			int tamaño = n > res.get(pais).size() ? res.get(pais).size() : n;
			res.put(pais, res.get(pais).subList(0, tamaño));
		}
		return res;
	}

	public String calcularRegionConMejoresVinos(Double umbralCalidadPrecio) {
		Map<String,Integer> aux = new HashMap<String, Integer>();
		for (Vino vino : vinos) {
			if (vino.calidadPrecio() > umbralCalidadPrecio) {
				if(! aux.containsKey(vino.region())) {
					aux.put(vino.region(), 0);
				}
				aux.put(vino.region(), aux.get(vino.region()) + 1 );
			}
		}
		String region = null;
		Integer cantidad = null;
		for (Entry<String, Integer> t : aux.entrySet()) {
			if (cantidad == null || t.getValue()>cantidad) {
				region = t.getKey();
				cantidad = t.getValue();
			}
		}
		return region;
	}
	
	
	

}
