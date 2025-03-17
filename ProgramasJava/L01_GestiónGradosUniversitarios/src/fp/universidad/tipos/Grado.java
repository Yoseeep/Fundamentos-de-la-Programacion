package fp.universidad.tipos;

import java.util.Objects;
import java.util.Set;

import utiles.Checkers;

public class Grado implements Comparable<Grado>{
	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double minCreditosOptativas;
	
	
	
	public Grado(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas,
			Double minCreditosOptativas) {
		this.nombre = nombre;
		this.asignaturasObligatorias = Set.copyOf(asignaturasObligatorias);
		this.asignaturasOptativas = Set.copyOf(asignaturasOptativas);
		this.minCreditosOptativas = minCreditosOptativas;
		chequeaIgualCreditosOptativas(asignaturasOptativas);
		chequeaNumMinCreditosOptativos(asignaturasOptativas, minCreditosOptativas);
	}
	
	private void chequeaIgualCreditosOptativas(Set<Asignatura> asignaturasOptativas) {
		boolean res = true;
		Asignatura[] arrayOptativas = (Asignatura[]) asignaturasOptativas.toArray();
		double creditosIguales = arrayOptativas[0].creditos();
		for (Asignatura a: arrayOptativas) {
			if (a.creditos() != creditosIguales) {
				res = false;
			}
		}
		Checkers.check("Todas las asignaturas optativas del grado deben tener el mismo número de créditos", res);
	}
	
	private void chequeaNumMinCreditosOptativos(Set<Asignatura> asignaturasOptativas, double minCreditosOptativos) {
		double totalCreditosOptativosGrado = 0;
		for(Asignatura a: asignaturasOptativas) {
			totalCreditosOptativosGrado += a.creditos();
		}
		Checkers.check(nombre, 0 <= minCreditosOptativos && minCreditosOptativos <= totalCreditosOptativosGrado);
	}


	public Double getTotalCreditos() {
		double res = minCreditosOptativas;
		for(Asignatura a: asignaturasObligatorias) {
			res += a.creditos();
		}
		return res;
	}


	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Grado))
			return false;
		Grado other = (Grado) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public int compareTo(Grado o) {
		return this.nombre.compareTo(o.nombre);
	}
	
	
	
	//TODO
	public Set<Asignatura> getAsignaturas(Integer curso){
		return null;
	}
	
	//TODO
	public Set<Asignatura> getAsignaturas(String codigo){
		return null;
	}


	
	
}