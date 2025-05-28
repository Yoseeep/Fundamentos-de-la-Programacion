package fp.examenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalendarioExamenes {
	private List<Examen> examenes;

	public List<Examen> getExamenes() {
		return examenes;
	}
	
	public Integer getNumeroExamenes() {
		return this.examenes.size();
	}

	public CalendarioExamenes(Stream<Examen> examenes) {
		this.examenes = examenes.toList();
	}
	
	public String toString() {
		String res = "";
		for( Examen examen : this.examenes.subList(0, this.examenes.size()-1) ) {
			res.concat(examen + "\n");
		}
		res.concat(this.examenes.getLast().toString());
		return res;
	}
	
	
	public Map<String, Set<Examen>> getExamenesPorAula(){
		Map<String,Set<Examen>> res = new HashMap<String, Set<Examen>>();
		for (Examen examen : this.examenes) {
			for (Aula aula : examen.getAulas()) {
				if( !res.containsKey(aula.nombre()) ) {
					res.put(aula.nombre(), new HashSet<Examen>());
				}
				res.get(aula.nombre()).add(examen);
			}
		}
		return res;
	}
	
	public Examen getExamenMayorPorcentajeAsistentes(LocalTime t, String nombreAula) {
		Comparator<Examen> cmp = Comparator.comparing(Examen::getPorcentajeAsistentes);
		return (Examen) this.examenes.stream()
				.filter( e -> 
					e.getFechaHora().toLocalTime().isAfter(t) && 
					e.usaAula(nombreAula)
						)
				.max(cmp)
				.orElse(null);
	}
	
	public SortedSet<String> getAulasExamenesTipo(TipoExamen tipo){
		Comparator<String> cmp = Comparator.naturalOrder();
		return this.examenes.stream()
				.filter( e -> e.getTipo().equals(tipo) )
				.flatMap( e -> e.getAulas().stream() )
				.map( t -> t.nombre() )
				.collect( Collectors.toCollection( () -> new TreeSet<String>(cmp) ) );
	}
	
	public String getAulaMasOcupada(LocalDate fecha){
		Comparator<Entry<String, Set<Examen>>> cmp = Comparator.comparing(
				par ->  obtieneOcupacionFecha( par,fecha )
				);
		return this.getExamenesPorAula().entrySet().stream()
				.max(cmp)
				.orElseThrow( () -> new NoSuchElementException() )
				.getKey();
	}
	private static long obtieneOcupacionFecha(Entry<String,Set<Examen>> par, LocalDate fecha){
		return par.getValue().stream()
				.filter( ex -> ex.getFechaHora().toLocalDate().equals(fecha) )
				.mapToInt( ex -> ex.getAsistentes() )
				.sum();
	}
	
	public List<LocalDate> getFechasConMasAulasDe(Integer umbral){
		return this.examenes.stream()
				.collect(Collectors.groupingBy(
						ex -> ex.getFechaHora().toLocalDate(),
						Collectors.collectingAndThen(
								Collectors.flatMapping(
										ex -> ex.getAulas().stream().map(Aula::nombre), 
										Collectors.toList()
										), 
								se -> se.size()
								)
						))
				.entrySet().stream()
				.filter(par -> par.getValue() > umbral)
				.map(par -> par.getKey())
				.toList();
	}
}
