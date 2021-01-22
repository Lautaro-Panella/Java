/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioA;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Principal {
    public static void main(String[] args) {
        Expediente e1 = new Expediente(112233,"L",1234,"Construcción","C","Municipio",null);
        Control c1 = new Control(1122,"Estructural",true, null);
        EstadoControl ec1 = new EstadoControl(1111,true);
        e1.getControles().add(c1);
        c1.setEstadoControl(ec1);
        System.out.println("La carátula del expediente es: " + e1.getCaratulaExpediente());
        System.out.println("Los controles obligatorios son: " + e1.getControlesObligatorios());
        if (e1.getEstadoControles()) {
            System.out.println("Todos los controles obligatorios están aprobados.");
        }
        else {
            System.out.println("No todos los controles obligatorios están aprobados.");
        }   
        if (e1.listaExpedientes() != null) {
            System.out.print("Los expedientes asociados a e1 son: ");
            for (Expediente e : e1.listaExpedientes()) {
                System.out.println(e.getCaratulaExpediente() + " ");
            }
        }
    }
}
