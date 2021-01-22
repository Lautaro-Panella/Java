
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Vistas.MateriaVista;
import Modelos.MateriaModelo;
import Modelos.MenuModelo;
import Vistas.MenuVista;


/**
 *
 * @Panella
 */
public class MateriaControlador implements ActionListener, MouseListener{
    private MateriaModelo modelo1;
    private MateriaVista vista1;
    private DefaultTableModel modeloTabla;

    public MateriaControlador(MateriaModelo modelo1, MateriaVista vista1) {
        this.vista1 = vista1;
        this.modelo1 = modelo1;
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);
        vista1.setTitle("MATERIAS");        
        this.llenarFilas(this.vista1.tablaMateria);
        botonesEnEscucha();
        llenarComboBox();
    }

    public void botonesEnEscucha() {
        this.vista1.btnAgregarMateria.addActionListener(this);
        this.vista1.btnModificarMateria.addActionListener(this);
        this.vista1.btnEliminarMateria.addActionListener(this);
        this.vista1.btnVolverMateria.addActionListener(this);
        this.vista1.tablaMateria.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista1.btnAgregarMateria)) { // Si tocan el botón Agregar.  
            if (modelo1.validarIngreso(this.vista1.txtNombreMateria.getText()) || modelo1.validarIngreso(String.valueOf(this.vista1.cboxDniMateria.getSelectedItem()))) {   
                JOptionPane.showMessageDialog(null, "Debe llenar los campos Nombre y DNI profesor."); // Si faltan datos (*).
            } else {                
                this.modelo1.setNombre(this.vista1.txtNombreMateria.getText());
                this.modelo1.setDniProf(Long.parseLong(this.vista1.cboxDniMateria.getSelectedItem().toString()));                              
                if (this.modelo1.agregarDatos(modelo1)) { 
                    JOptionPane.showMessageDialog(null, "Carga exitosa!");
                }
                this.limpiarTabla(this.vista1.tablaMateria);
                llenarFilas(this.vista1.tablaMateria);
                limpiarCuadros();               
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnModificarMateria)) { // Si tocan el botón Modificar.
            this.modelo1.setCodigo(Long.parseLong(this.vista1.txtCodigoMateria.getText()));
            this.modelo1.setNombre(this.vista1.txtNombreMateria.getText());
            this.modelo1.setDniProf(Long.parseLong(this.vista1.cboxDniMateria.getSelectedItem().toString()));
            if (this.modelo1.modificarDatos(modelo1)) {
                JOptionPane.showMessageDialog(null, "Modificación exitosa!");
            }
            this.limpiarTabla(this.vista1.tablaMateria);
            llenarFilas(this.vista1.tablaMateria);
            limpiarCuadros();           
        }
        
        if (ae.getSource().equals(this.vista1.btnEliminarMateria)) { // Si tocan el botón Eliminar.
            if (this.modelo1.eliminarDatos(this.vista1.tablaMateria)) {
                limpiarTabla(this.vista1.tablaMateria);
                llenarFilas(this.vista1.tablaMateria);
                JOptionPane.showMessageDialog(null, "Eliminación exitosa!");
                limpiarCuadros();
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnVolverMateria)) { // Si tocan el botón Volver.   
            MenuModelo modelo1 = new MenuModelo();
            MenuVista vista1 = new MenuVista();
            MenuControlador menuControlador = new MenuControlador(modelo1, vista1);
            this.vista1.dispose();
        }              
    }

    public String[] nombreColumnas() { // Nombro mis columnas.
        String[] Columna = {"Código", "Nombre", "DNI Profesor"};
        return Columna;
    }

    public void limpiarTabla(JTable tabla) { // Limpio tabla.
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    public void llenarFilas(JTable tabla) { // Lleno filas.
        modeloTabla = new DefaultTableModel(null, nombreColumnas());
        ArrayList<Modelos.MateriaModelo> materias;
        materias = this.modelo1.traerDatos();
        this.limpiarTabla(this.vista1.tablaMateria);
        Object datos[] = new Object[3];
        if (materias.size() > 0) {
            for (int i = 0; i < materias.size(); i++) {
                datos[0] = materias.get(i).getCodigo();
                datos[1] = materias.get(i).getNombre();
                datos[2] = materias.get(i).getDniProf();

                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        materias.clear();
    }

    public void limpiarCuadros() { // Limpio cuadros.  
        this.vista1.txtCodigoMateria.setText("");
        this.vista1.txtNombreMateria.setText("");
        this.vista1.cboxDniMateria.setSelectedIndex(0);
    }
    
    public void llenarComboBox(){ // Lleno comboBox.  
        Set<String> dniProf = this.modelo1.traerDNIProf();
        Iterator<String> dniIterator = dniProf.iterator();
        while(dniIterator.hasNext()){
            this.vista1.cboxDniMateria.addItem(dniIterator.next());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {   
        if (me.getButton() == 1) {
            int fila = this.vista1.tablaMateria.rowAtPoint(me.getPoint());
            if (fila > -1) {
                this.vista1.txtCodigoMateria.setText(String.valueOf(this.vista1.tablaMateria.getValueAt(fila, 0)));
                this.vista1.txtNombreMateria.setText(String.valueOf(this.vista1.tablaMateria.getValueAt(fila, 1)));
                this.vista1.cboxDniMateria.setSelectedItem(String.valueOf(this.vista1.tablaMateria.getValueAt(fila, 2)));
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }
           
}
