package fp.universidad.tipos.test;

import fp.universidad.tipos.Departamento;
import fp.universidad.tipos.Profesor;
import fp.universidad.tipos.TipoAsignatura;
import fp.universidad.tipos.Asignatura;
import fp.universidad.tipos.Categoría;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;

public class TestDepartamento {

    public static void main(String[] args) {
        // Crear profesores
        Profesor profesor1 = new Profesor("12345678A", "Juan", "Pérez", LocalDate.of(1980, 1, 1), "juan.perez@example.com", Categoría.TITULAR);
        Profesor profesor2 = new Profesor("87654321B", "Ana", "García", LocalDate.of(1985, 2, 2), "ana.garcia@example.com", Categoría.ASOCIADO);
        Profesor profesor3 = new Profesor("11223344C", "Luis", "Martínez", LocalDate.of(1990, 3, 3), "luis.martinez@example.com", Categoría.AYUDANTE);

        // Crear asignaturas
        Asignatura asignatura1 = new Asignatura("Matemáticas", "1234567", 6.0, TipoAsignatura.ANUAL, 1);
        Asignatura asignatura2 = new Asignatura("Física", "2345678", 4.5, TipoAsignatura.PRIMER_CUATRIMESTRE, 2);

        // Crear un departamento
        Departamento departamento = new Departamento("Ciencias");

        // Probar el método getNombre
        System.out.println("Nombre del departamento: " + departamento.getNombre());

        // Probar el método getProfesores
        System.out.println("Profesores del departamento: " + departamento.getProfesores());

        // Probar el método getAsignaturas
        System.out.println("Asignaturas del departamento: " + departamento.getAsignaturas());

        // Probar el método nuevoProfesor
        departamento.nuevoProfesor(profesor1);
        departamento.nuevoProfesor(profesor2);
        System.out.println("Profesores después de agregar nuevos:");
        for (Profesor profesor : departamento.getProfesores()) {
            System.out.println(profesor);
        }

        // Probar el método eliminaProfesor
        departamento.eliminaProfesor(profesor1);
        System.out.println("Profesores después de eliminar uno:");
        for (Profesor profesor : departamento.getProfesores()) {
            System.out.println(profesor);
        }

        // Probar el método nuevaAsignatura
        departamento.nuevaAsignatura(asignatura1);
        departamento.nuevaAsignatura(asignatura2);
        System.out.println("Asignaturas después de agregar nuevas:");
        for (Asignatura asignatura : departamento.getAsignaturas()) {
            System.out.println(asignatura);
        }

        // Probar el método eliminaAsignatura
        departamento.eliminaAsignatura(asignatura1);
        System.out.println("Asignaturas después de eliminar una:");
        for (Asignatura asignatura : departamento.getAsignaturas()) {
            System.out.println(asignatura);
        }

        // Probar el método borraTutorias
        profesor2.nuevaTutoria(LocalTime.of(10, 0), Duration.ofMinutes(30), DayOfWeek.MONDAY);
        profesor2.nuevaTutoria(LocalTime.of(11, 0), Duration.ofMinutes(30), DayOfWeek.TUESDAY);
        departamento.borraTutorias();
        System.out.println("Tutorías después de borrar todas:");
        for (Profesor profesor : departamento.getProfesores()) {
            System.out.println(profesor.getTutorías());
        }

        // Probar el método borraTutorias(Categoría)
        profesor3.nuevaTutoria(LocalTime.of(12, 0), Duration.ofMinutes(30), DayOfWeek.WEDNESDAY);
        departamento.borraTutorias(Categoría.AYUDANTE);
        System.out.println("Tutorías después de borrar por categoría AYUDANTE:");
        for (Profesor profesor : departamento.getProfesores()) {
            System.out.println(profesor.getTutorías());
        }
    }
}