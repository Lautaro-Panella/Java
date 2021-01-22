/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioD;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Principal {
    public static void main(String[] args) throws Exception {
        Barrio b1 = new Barrio(1234, "Bancario", "Impsa");
        Vivienda v1 = new Vivienda(3181, "Castelli", 1, 200, b1);
        Vivienda v2 = new Vivienda(3187, "Lencinas", 2, 300, b1);
        Habitacion h1 = new Habitacion(1, "Cocina", 20, v1);
        Habitacion h2 = new Habitacion(2, "Comedor", 30, v2);
        b1.getViviendas().add(v1);
        b1.getViviendas().add(v2);
        v1.getHabitaciones().add(h1);
        v2.getHabitaciones().add(h2);
        System.out.println("La superficie total del barrio en m2 es de: " + b1.getSuperficieTotalTerreno());
        System.out.println("Los m2 cubiertos de la vivienda 1 son: " + v1.getMetrosCuadradosCubiertos());
        System.out.println("Los m2 cubiertos de la vivienda 2 son: " + v2.getMetrosCuadradosCubiertos());
        System.out.println("La superficie cubierta del barrio en m2 es de: " + b1.getSuperficieTotalCubierta());
    }
}
