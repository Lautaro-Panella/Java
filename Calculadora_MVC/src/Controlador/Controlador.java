
package Controlador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Modelo.Modelo;
import Vista.InterfazCaculadora1;

/**
 *
 * @Panella Lautaro, TSP-TT.
 */

public class Controlador implements ActionListener {
    private Modelo m;
    private InterfazCaculadora1 ic;
    
    public Controlador(Modelo m, InterfazCaculadora1 ic) {
        this.m = m;
        this.ic = ic;
        this.ic.jBsumar.addActionListener(this);
        this.ic.jBrestar.addActionListener(this);
        this.ic.jBmultiplicar.addActionListener(this);
        this.ic.jBdividir.addActionListener(this);
        this.ic.jBborrar.addActionListener(this);
        ic.setVisible(true);
    }            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ic.jBsumar)) {
            ic.txtR.setText(m.operaciones("sumar", ic.txtE1.getText(), ic.txtE2.getText())); 
        }
        if (e.getSource().equals(ic.jBrestar)) {
            ic.txtR.setText(m.operaciones("restar", ic.txtE1.getText(), ic.txtE2.getText()));
        }
        if (e.getSource().equals(ic.jBmultiplicar)) {
            ic.txtR.setText(m.operaciones("multiplicar", ic.txtE1.getText(), ic.txtE2.getText()));
        }
        if (e.getSource().equals(ic.jBdividir)) {
            ic.txtR.setText(m.operaciones("dividir", ic.txtE1.getText(), ic.txtE2.getText())); 
        }
        if (e.getSource().equals(ic.jBborrar)) {
            ic.txtR.setText(""); 
            ic.txtE1.setText("");
            ic.txtE2.setText("");
        }
    }
}
