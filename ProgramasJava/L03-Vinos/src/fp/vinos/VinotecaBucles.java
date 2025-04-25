package fp.vinos;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class VinotecaBucles implements Vinoteca{
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
	
	public Collection<Vino> obtenerVinosRangoPuntos(int inf, int sup){
		Checkers.check("El valor inferior debe ser menor o igual que el valor superior.", inf <= sup);
		List<Vino> res = new ArrayList<Vino>();
		for (Vino v : vinos) {
			if (inf <= v.puntos() && v.puntos() <= sup) {
				res.add(v);
			}
		}
		return res;
	}

}
