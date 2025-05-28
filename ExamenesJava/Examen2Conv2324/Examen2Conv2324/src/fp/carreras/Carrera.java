package fp.carreras;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class Carrera implements Comparable<Carrera> {
	private String id;
	private String localidad;
	private LocalDateTime fechaHora;
	private Modalidad modalidad;
	private Integer distancia;
	private Integer desnivel;
	private List<Participante> participantes;
	
	public Carrera(String id, String localidad, LocalDateTime fechaHora, 
			Modalidad modalidad, Integer distancia, Integer desnivel) {
		Checkers.check("La distancia mínima de la carrera debe ser de 7km", 
				distancia >= 7000);
		Checkers.check("El desnivel debe ser mayor que 0 y menor o igual a 1km", 
				desnivel > 0 && desnivel <= 1000);
		
		this.id = id;
		this.localidad = localidad;
		this.fechaHora = fechaHora;
		this.modalidad = modalidad;
		this.distancia = distancia;
		this.desnivel = desnivel;
		this.participantes = new ArrayList<Participante>();
	}

	public String getId() {
		return id;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	public Modalidad getModalidad() {
		return modalidad;
	}
	
	public Integer getDesnivel() {
		return desnivel;
	}
	
	public List<Participante> getParticipantes() {
		return List.copyOf(participantes);
	}

	public Integer getDistancia() {
		return distancia;
	}

	public int hashCode() {
		return Objects.hash(fechaHora, id, localidad);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(id, other.id)
				&& Objects.equals(localidad, other.localidad);
	}

	public String toString() {
		return "Carrera [id=" + id + ", localidad=" + localidad + ", fechaHora=" + fechaHora + ", modalidad="
				+ modalidad + ", distancia=" + distancia + ", desnivel=" + desnivel + "]";
	}

	public int compareTo(Carrera o) {
		int res = this.getFechaHora().compareTo(o.getFechaHora());
		if (res == 0) {
			this.getLocalidad().compareTo(o.getLocalidad());
			if (res == 0) {
				this.getId().compareTo(o.getId());
			}
		}
		return res;
	}
	
	public void añadeParticipantes(List<Participante> participantes) {
		for(Participante participante : participantes) {
			if (!this.participantes.contains(participantes)) {
				this.participantes.add(participante);
			}
		}
	}
	
	public Participante buscaParticipante(String nombre, String apellidos) {
		return this.getParticipantes().stream()
				.filter(p -> p.apellidos().equals(apellidos) &&
							  p.nombre().equals(nombre)
						)
				.findAny()
				.orElse(null);
	}
	
	public Double tiempoMedioPorKm() {
		return this.participantes.stream()
				.mapToDouble( p -> (p.duracion().toMinutes() * 1000d) / this.distancia )
				.average()
				.getAsDouble();
	}
	
	public Integer posicionParticipante(String nombre, String apellidos, Sexo sexo) {
		List<Participante> participantesFiltrados = new ArrayList<Participante>();
		for (Participante p : this.getParticipantes()) {
			if (p.sexo() == sexo) {
				participantesFiltrados.add(p);
			}
		}
		Comparator<Participante> cmp = Comparator.comparing(Participante::duracion);
		Collections.sort(participantesFiltrados,cmp);
		Integer puesto = 0;
		for(Participante p : participantesFiltrados) {
			puesto++;
			if(p.nombre().equals(nombre) && p.apellidos().equals(apellidos)) {
				break;
			}
		}
		
		return puesto;
	}
	
	
	
	
}
