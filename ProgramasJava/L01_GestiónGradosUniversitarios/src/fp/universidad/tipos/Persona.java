package fp.universidad.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Persona {
	private String DNI; 
	private String nombre; 
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String email;
	
	
	public Persona(String dNI, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}
	
	public Persona(String dNI, String nombre, String apellidos, LocalDate fechaNacimiento) {
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = "";
	}

	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return this.fechaNacimiento.until(LocalDate.now()).getYears();
	}
	
	public String toString() {
		return this.DNI + " - " + this.apellidos + ", " + this.nombre 
				+ " - " + this.fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/mm/yyyy")); 
	}
}
