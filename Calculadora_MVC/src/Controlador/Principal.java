
package Controlador;
import Modelo.Modelo;
import Vista.InterfazCaculadora1;

/**
 *
 * @Panella Lautaro, TSP-TT.
 */

public class Principal {
    public static void main(String[] args) {
        Modelo m = new Modelo();
        InterfazCaculadora1 ic = new InterfazCaculadora1();
        ic.setTitle("Calculadora MVC");
        ic.setLocationRelativeTo(ic);
        Controlador c = new Controlador(m, ic);
    } 
}
