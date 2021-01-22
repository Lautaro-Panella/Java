/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioB;

import java.util.Date;

/**
 *
 * Panella Lautaro, TSP - TT.
 */
public class Principal {
    public static void main(String[] args) {
        Date fecha1 = new Date(2020-1900, 3, 10);
        Date fecha2 = new Date(2020-1900, 3, 11);
        Empleado e1 = new Empleado("Lautaro", 37615223, "Castelli 3183", "lautaro.x@hotmail.com", null);
        Asistencia a1 = new Asistencia(1122, "E", fecha1, 15, 50, e1);
        Asistencia a2 = new Asistencia(2233, "F", fecha2, 14, 30, e1);
        RegimenHorario rh = new RegimenHorario(112233, 14, 30, 19, 30, e1);
        e1.setRegimen(rh);
        e1.getAsistencias().add(a1);
        e1.getAsistencias().add(a2);
        a1.setEmpleado(e1);
        a2.setEmpleado(e1);
        for (Asistencia a : e1.getAsistenciaXMesXAnio(3, 2020)) {
            System.out.println("Asistió el día: " + a.getFecha());
        }
        for (Tardanza t : e1.getDiasConTardanza(3, 2020)) {
            System.out.println("Llego tarde el día: " + t.getFecha());
        }
    }
}
