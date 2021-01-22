
package Controladores;

import Modelos.MenuModelo;
import Vistas.MenuVista;

public class Principal {
    public static void main(String[] args) {
        MenuModelo m1 = new MenuModelo();
        MenuVista v1 = new MenuVista();
        MenuControlador c1 = new MenuControlador(m1, v1);
    }  
}
