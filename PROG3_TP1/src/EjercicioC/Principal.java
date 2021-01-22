/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioC;

import java.util.Date;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Principal {
    public static void main(String[] args) {
        Date d1 = new Date(2020-1900, 3, 10);
        Date d2 = new Date(2023-1900, 7, 10);
        Date d3 = new Date(2025-1900, 9, 10);
        Sector s1 = new Sector(1, "UTN", "Facultad", null);
        Persona p1 = new Persona("Lautaro", "DNI", 37615223, 4978089, "ejemplo@gmail.com", "155071799", s1);
        Persona p2 = new Persona("Raúl", "DNI", 14002192, 4979793, "lu8mpr@gmail.com", "155678904", s1);
        TipoActividad ta1 = new TipoActividad(1122, "Deporte", 20);
        TipoActividad ta2 = new TipoActividad(2233, "Laboratorio", 45);
        Actividad a1 = new Actividad(d1, d2, "Paddle", "4 jugadores", ta1, p1);
        Actividad a2 = new Actividad(d1, d2, "Futbol5", "10 jugadores", ta1, p2);
        Actividad a3 = new Actividad(d1, d3, "Investigacion", "Vacuna", ta2, p1);
        ta1.getActividades().add(a1);
        ta1.getActividades().add(a2);
        p1.getActividades().add(a1);
        p1.getActividades().add(a2);
        p1.getActividades().add(a3);
        s1.getPersonas().add(p1);
        s1.getPersonas().add(p2);
        System.out.println("Los puntos asignados a Persona1 son: " + p1.totalPuntosAsignados());
        System.out.println("Puntos asignados a Persona1 por la actividad 2233: " + p1.totalPuntosAsignados(2233));
        System.out.println("Puntos asignados a Persona1 por la actividad 2233 en el año 2022: " + p1.totalPuntosAsignados(2233, 2022));
        if (s1.obtenerTotalSubSectores() != null) {
            System.out.print("Los subSectores de s1 son: ");
            for (Sector s : s1.obtenerTotalSubSectores()) {
                System.out.println(s.getNumero() + " ");
            }
        }
    }
}
