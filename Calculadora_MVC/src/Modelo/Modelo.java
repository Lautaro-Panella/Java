
package Modelo;

/**
 *
 * @Panella Lautaro, TSP-TT.
 */

public class Modelo {

    public Modelo() {
    }
    
    public String operaciones(String op, String txtE1, String txtE2) {
        
        String txtR = "";
        
        try {
            if (txtE1.trim().length() == 0 || txtE1.isEmpty() || txtE1 == null) {
                txtR = "Datos incorrectos para entrada 1.";
            }
            else if (txtE2.trim().length() == 0 || txtE2.isEmpty() || txtE2 == null) {
                txtR = "Datos incorrectos para entrada 2.";
            }
            else {
                double numero1, numero2, resultado = 0;
                numero1 = Double.parseDouble(txtE1);
                numero2 = Double.parseDouble(txtE2);
                switch (op) {
                    case "sumar":
                        resultado = numero1 + numero2;
                        break;
                    case "restar":
                        resultado = numero1 - numero2;
                        break;
                    case "multiplicar":
                        resultado = numero1 * numero2;
                        break;
                    case "dividir":
                        resultado = numero1 / numero2;
                        break;
                    default:
                        break;
                }
                if (numero2 == 0 && op.equals("dividir")) {
                    txtR = "No se puede dividir por 0.";
                }
                else {
                    txtR = (String.valueOf(resultado));
                }
            }   
        } catch (Exception  e) {
            txtR = "Solo pueden ingresarse números.";
        }
        return txtR;
    }
}
