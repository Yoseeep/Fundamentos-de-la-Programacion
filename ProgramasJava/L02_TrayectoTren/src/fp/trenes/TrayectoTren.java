package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public interface TrayectoTren extends Comparable<TrayectoTren>{
	String getCodigoTren();
	String getNombreTrayecto();
	TipoTren getTipo();
	List<String> getEstaciones();
	List<LocalTime> getHorasLlegada();
	List<LocalTime> getHorasSalida();
	LocalTime horaSalida();
	LocalTime horaLlegada();
	Duration duracionTrayecto();
	LocalTime getHoraSalida(String estacion);
	LocalTime getHoraLlegada(String estacion);
	void añadirEstacionIntermedia(int posicion, 
								  String estacion, 
								  LocalTime horaLlegada, 
								  LocalTime horaSalida);
	void eliminarEstacionIntermedia(String estacion);
	
}
