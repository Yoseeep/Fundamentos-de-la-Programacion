package fp.universidad.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import utiles.Checkers;

public class Persona implements Comparable<Persona>{
	private String DNI; 
	private String nombre; 
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String email;
	
	
	public Persona(String DNI, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		chequeaDNI(DNI);
		chequeaEmail(email);
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}
	
	public Persona(String DNI, String nombre, String apellidos, LocalDate fechaNacimiento) {
		chequeaDNI(DNI);
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = "";
	}
	
	private void chequeaDNI(String DNI) {
		boolean res = true;
		char[] dniChar = DNI.toCharArray();
		if (DNI.length() != 9) {
			res = false;
		} else 
			if (!Character.isAlphabetic(dniChar[dniChar.length-1])) {
			res = false;
			} else
				for (int i = 0; i < dniChar.length-1; i++) {
					if (!Character.isDigit(dniChar[i])) {
						res = false;
						break;
					}
				}
		Checkers.check("El DNI tiene un formato incorrecto", res);
		
	}
	
	private void chequeaEmail(String email) {
		Checkers.check("El correo tiene un error con el @", 
				email.equals("") || 
				(email.contains("@") && email.indexOf("@") == email.lastIndexOf("@"))
				);
	}

	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		chequeaDNI(DNI);
		this.DNI = DNI;
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
		chequeaEmail(email);
		this.email = email;
	}

	public int getEdad() {
		return this.fechaNacimiento.until(LocalDate.now()).getYears();
	}
	
	public String toString() {
		return this.DNI + " - " + this.apellidos + ", " + this.nombre 
				+ " - " + this.fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
	}


	public int hashCode() {
		return Objects.hash(DNI, apellidos, nombre);
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Persona))
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(DNI, other.DNI) && Objects.equals(apellidos, other.apellidos)
				&& Objects.equals(nombre, other.nombre);
	}


	public int compareTo(Persona o) {
		int res = this.apellidos.compareTo(o.apellidos);
		if (res == 0) {
			res = this.nombre.compareTo(o.nombre);
			if (res == 0) {
				res = this.DNI.compareTo(o.DNI);
			}
		}
		return res;
	}
	
	
}
