package fp.hamburgueserias;


import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Competicion {
	private SortedSet<Visita> visitas;
	
	public Competicion(Stream<Visita> sv) {
		Comparator<Visita> cmp = Comparator.comparing(Visita::getCodigoPostal)
											.thenComparing(Comparator.naturalOrder());
		this.visitas = sv.collect(Collectors.toCollection( 
				() -> new TreeSet<Visita>(cmp)
				));
	}

	public SortedSet<Visita> getVisitas() {
		Comparator<Visita> cmp = Comparator.comparing(Visita::getCodigoPostal)
				.thenComparing(Comparator.naturalOrder());
		SortedSet<Visita> copia = new TreeSet<Visita>(cmp);
		copia.addAll( this.visitas );
		return copia;
	}

	public String toString() {
		String res = "";
		for (Visita v : this.visitas) {
			res.concat(v + "\n");
		}
		return res.substring(0, res.length()-3); //para quitar el último salto de linea.
	}

	public int hashCode() {
		return Objects.hash(visitas);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competicion other = (Competicion) obj;
		return Objects.equals(visitas, other.visitas);
	}
	
	
	public SortedSet<String> getEmailsOrdenados (Duration d){
		Double temperaturaMedia = this.getVisitas().stream()
				.mapToDouble(Visita::getTemperatura)
				.average().getAsDouble();
		return this.getVisitas().stream()
				.filter( v -> v.getTiempoTrascurrido().compareTo(d) > 0 && 
							  v.getTemperatura() < temperaturaMedia)
				.map(Visita::getEmail)
				.collect(Collectors.toCollection(
						() -> new TreeSet<String>()
						));
	}
	
	public Integer getTotalVisitasComilones() {
		
		Double mediaEvaluaciones = this.getVisitas().stream()
				.mapToDouble( v -> v.getEvaluaciones().size()
						)
				.average().getAsDouble();
				
		
		return (int) this.getVisitas().stream()
				.mapToDouble( v -> v.getEvaluaciones().size() )
				.filter( m -> m > mediaEvaluaciones )
				.count();
	}
	
	public String getPeorHamburgueseriaPorCalidadIngredientes() {
		Comparator<? super Entry<String, List<Evaluacion>>> cmp = 
				Comparator.comparing( par -> 
				((Entry<String, List<Evaluacion>>) par).getValue().stream()
				.mapToDouble( Evaluacion::calidadIngredientes )
				.average().getAsDouble()
				);
		
		return this.getVisitas().stream()
				.flatMap( v -> v.getEvaluaciones().stream() )
				.collect(Collectors.groupingBy(
						Evaluacion::hamburgueseria
						))
				.entrySet().stream()
				.min(cmp).orElse(null).getKey();
	}

	public Map<String, String> getTopComilonPorCPEnDia(LocalDate d){
		return this.getVisitas().stream()
				.filter( v -> v.getEntrada().toLocalDate().equals(d) )
				.collect(Collectors.groupingBy(
						Visita::getCodigoPostal,
						Collectors.collectingAndThen(
								Collectors.mapping(
										lv -> lv,
										Collectors.toList()
										), 
								lv -> obtieneEmailComparado(lv)
								)
						));
	}
	private static String obtieneEmailComparado(List<Visita> vs) {
		Comparator<Visita> cmp1 = Comparator.comparing(
				v -> v.getEvaluaciones().size()
				);
		Comparator<Visita> cmp2 = Comparator.comparing(
				v -> v.getEntrada().toLocalDate()
				);
		Comparator<Visita> cmp = cmp1.thenComparing(cmp2);
		return vs.stream().max(cmp).get().getEmail();
	}
	
	public String getHamburgueseriaGanadora() {
		Map<String,List<Double>> acum = new HashMap<String, List<Double>>();
		for (Visita visita : this.getVisitas()) {
			for (Evaluacion evaluacion : visita.getEvaluaciones()) {
				if ( !acum.containsKey(evaluacion.hamburgueseria()) ) {
					acum.put(evaluacion.hamburgueseria(),new ArrayList<Double>());
				}
				acum.get(evaluacion.hamburgueseria()).add(evaluacion.puntuacionFinal());
			}
		}
		Map<String,Double> res = new HashMap<String, Double>();
		for (Entry<String,List<Double>> par : acum.entrySet()) {
			Double mediaPuntuacion = 0d;
			for (Double punto : par.getValue()) {
				mediaPuntuacion += punto;
			}
			mediaPuntuacion /= par.getValue().size();
			res.put(par.getKey(), mediaPuntuacion);
		}
		Comparator< Entry<String,Double> > cmp = 
				Comparator.comparing(Entry<String,Double>::getValue);
		String ganadora = Collections.max(res.entrySet(),cmp).getKey();
		
		return ganadora;
	}
	
	
	
}
