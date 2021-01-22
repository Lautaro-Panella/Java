
package Controladores;

import Modelos.MenuModelo;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Vistas.CursadoVista;
import Modelos.CursadoModelo;
import Vistas.MenuVista;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 *
 * @Panella
 */
public class CursadoControlador implements ActionListener, MouseListener {
    private CursadoVista vista1;
    private CursadoModelo modelo1;
    private DefaultTableModel modeloTabla;

    public CursadoControlador(CursadoModelo modelo1, CursadoVista vista1) {
        this.modelo1 = modelo1;
        this.vista1 = vista1;
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);
        vista1.setTitle("CURSADO");
        this.llenarFilas(this.vista1.tablaCursado);
        botonesEnEscucha();
        llenarComboBoxDNIAlu();
        llenarComboBoxCodMat();
    }

    public void botonesEnEscucha() {
        this.vista1.btnAgregarCursado.addActionListener(this);
        this.vista1.btnModificarCursado.addActionListener(this);
        this.vista1.btnEliminarCursado.addActionListener(this);
        this.vista1.tablaCursado.addMouseListener(this);
        this.vista1.btnVolverCursado.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista1.btnAgregarCursado)) { // Si tocan el botón Agregar.
            if (modelo1.validarIngreso(this.vista1.txtNotaCursado.getText()) || modelo1.validarIngreso(String.valueOf(this.vista1.cboxCodMat.getSelectedItem())) ||
                    modelo1.validarIngreso(String.valueOf(this.vista1.cboxDniAlumno.getSelectedItem()))) { // Si faltan datos (*).  
                JOptionPane.showMessageDialog(null, "Debe llenar los campos DNI alumno, Código materia y Nota.");
            } else {                
                this.modelo1.setNota(Long.parseLong(this.vista1.txtNotaCursado.getText()));
                this.modelo1.setDniAlumno(Long.parseLong(this.vista1.cboxDniAlumno.getSelectedItem().toString()));
                this.modelo1.setCodigoMateria(Long.parseLong(this.vista1.cboxCodMat.getSelectedItem().toString()));                          
                if (this.modelo1.agregarDatos(modelo1)) {
                    JOptionPane.showMessageDialog(null, "Carga exitosa!");
                }
                this.limpiarTabla(this.vista1.tablaCursado);
                llenarFilas(this.vista1.tablaCursado);
                limpiarCuadros();             
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnModificarCursado)) { // Si tocan el botón Modificar. 
            this.modelo1.setNota(Long.parseLong(this.vista1.txtNotaCursado.getText()));
            this.modelo1.setDniAlumno(Long.parseLong(this.vista1.cboxDniAlumno.getSelectedItem().toString()));
            this.modelo1.setCodigoMateria(Long.parseLong(this.vista1.cboxCodMat.getSelectedItem().toString()));
            if (this.modelo1.modificarDatos(modelo1)) {
                JOptionPane.showMessageDialog(null, "Modificación exitosa!");
            }
            this.limpiarTabla(this.vista1.tablaCursado);
            llenarFilas(this.vista1.tablaCursado);
            limpiarCuadros();            
        }
        
        if (ae.getSource().equals(this.vista1.btnEliminarCursado)) { // Si tocan el botón Eliminar.
            if (this.modelo1.eliminarDatos(this.vista1.tablaCursado)) {
                limpiarTabla(this.vista1.tablaCursado);
                llenarFilas(this.vista1.tablaCursado);
                JOptionPane.showMessageDialog(null, "Eliminación exitosa!");
                limpiarCuadros();
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnVolverCursado)) { // Si tocan el botón Volver.
            MenuModelo modelo1 = new MenuModelo();
            MenuVista vista1 = new MenuVista();
            MenuControlador menuControlador = new MenuControlador(modelo1, vista1);
            this.vista1.dispose();
        }  
    }

    public String[] nombreColumnas() { // Nombro mis columnas.
        String[] Columna = {"DNI Alumno", "Código Materia", "Nota"};
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
        ArrayList<Modelos.CursadoModelo> cursados;
        cursados = this.modelo1.traerDatos();
        this.limpiarTabla(this.vista1.tablaCursado);
        Object datos[] = new Object[3];
        if (cursados.size() > 0) {
            for (int i = 0; i < cursados.size(); i++) {
                datos[0] = cursados.get(i).getDniAlumno();
                datos[1] = cursados.get(i).getCodigoMateria();
                datos[2] = cursados.get(i).getNota();

                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        cursados.clear();
    }

    public void limpiarCuadros() { // Limpio cuadros.
        this.vista1.txtNotaCursado.setText("");
        this.vista1.cboxDniAlumno.setSelectedIndex(0);
        this.vista1.cboxCodMat.setSelectedIndex(0);
    }
    
    public void llenarComboBoxDNIAlu(){ // Lleno el comboBox de DNI.
        Set<String> dniAlu = this.modelo1.traerDNIalumno();
        Iterator<String> dniIterator = dniAlu.iterator();
        while(dniIterator.hasNext()){
            this.vista1.cboxDniAlumno.addItem(dniIterator.next());
        }
    }
    
    public void llenarComboBoxCodMat(){ // Lleno el comboBox de Código materia.
        Set<String> codMat = this.modelo1.traerCodigoMateria();
        Iterator<String> dniIterator = codMat.iterator();
        while(dniIterator.hasNext()){
            this.vista1.cboxCodMat.addItem(dniIterator.next());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == 1) {
            int fila = this.vista1.tablaCursado.rowAtPoint(me.getPoint());
            if (fila > -1) {
                this.vista1.cboxDniAlumno.setSelectedItem(String.valueOf(this.vista1.tablaCursado.getValueAt(fila, 0)));
                this.vista1.cboxCodMat.setSelectedItem(String.valueOf(this.vista1.tablaCursado.getValueAt(fila, 1)));
                this.vista1.txtNotaCursado.setText(String.valueOf(this.vista1.tablaCursado.getValueAt(fila, 2)));
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
