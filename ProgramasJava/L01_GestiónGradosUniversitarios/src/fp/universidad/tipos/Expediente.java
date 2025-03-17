package fp.universidad.tipos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expediente {
	private List<Nota> notas;

	public Expediente() {
		this.notas = new ArrayList<Nota>();
	}

	public List<Nota> getNotas() {
		return notas;
	}


	
	public Double getNotaMedia() {
		double num = 0;
		double den = 0;
		for (Nota nota:notas) {
			if (nota.valor() >= 5) {
				num += nota.valor();
				den++;
			}
		}
		return (den == 0) ? 0d : num/den;
	}

	@Override
	public String toString() {
		return notas.toString();
	}
	
	public void nuevaNota(Nota n) {
		for (Nota i: notas) {
			if (n.equals(i)) {
				notas.remove(i);
			}
		}
		notas.add(n);
	}

	@Override
	public int hashCode() {
		return Objects.hash(notas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Expediente))
			return false;
		Expediente other = (Expediente) obj;
		return Objects.equals(notas, other.notas);
	}
	
	
	
	
	
}
