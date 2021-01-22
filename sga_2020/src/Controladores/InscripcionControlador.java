
package Controladores;

import Modelos.MenuModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Vistas.InscripcionVista;
import Vistas.MenuVista;
import Modelos.InscripcionModelo;

/**
 *
 * @Panella
 */
public class InscripcionControlador implements ActionListener, MouseListener {
    private InscripcionModelo modelo1;
    private InscripcionVista vista1;
    private DefaultTableModel modeloTabla;

    public InscripcionControlador(InscripcionModelo modelo1, InscripcionVista vista1) {
        this.modelo1 = modelo1;
        this.vista1 = vista1;
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);
        vista1.setTitle("INSCRIPCIONES");
        this.llenarFilas(this.vista1.tablaInscripcion);
        botonesEnEscucha();
        llenarComboBox();
    }

    public void botonesEnEscucha() {
        this.vista1.btnAgregarInscripcion.addActionListener(this);
        this.vista1.btnModificarInscripcion.addActionListener(this);
        this.vista1.btnEliminarInscripcion.addActionListener(this);
        this.vista1.btnVolverInscripcion.addActionListener(this);
        this.vista1.tablaInscripcion.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista1.btnAgregarInscripcion)) { // Si tocan el botón Agregar.
            if (modelo1.validarIngreso(this.vista1.txtNombreInscripcion.getText()) || modelo1.validarIngreso((this.vista1.jdateFechaInsc.getDateFormatString()))) {  
                JOptionPane.showMessageDialog(null, "Debe llenar los campos Nombre, Fecha y Código de carrera."); // Si faltan datos (*).
            } else {
                this.modelo1.setNombre(this.vista1.txtNombreInscripcion.getText());
                java.util.Date date = this.vista1.jdateFechaInsc.getDate(); 
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.modelo1.setFecha(sqlDate);
                this.modelo1.setCodigoCarrera(Long.parseLong(this.vista1.cboxCodCar.getSelectedItem().toString()));
                if (this.modelo1.agregarDatos(modelo1)) {   
                    JOptionPane.showMessageDialog(null, "Carga exitosa!");
                }
                this.limpiarTabla(this.vista1.tablaInscripcion);
                llenarFilas(this.vista1.tablaInscripcion);
                limpiarCuadros();
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnModificarInscripcion)) { // Si tocan el botón Modificar.
            this.modelo1.setCodigo(Long.parseLong(this.vista1.txtCodigoInscripcion.getText()));
            this.modelo1.setNombre(this.vista1.txtNombreInscripcion.getText());
            java.util.Date date = this.vista1.jdateFechaInsc.getDate();   
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            this.modelo1.setFecha(sqlDate);
            this.modelo1.setCodigoCarrera(Long.parseLong(this.vista1.cboxCodCar.getSelectedItem().toString()));
            if (this.modelo1.modificarDatos(modelo1)) {
                JOptionPane.showMessageDialog(null, "Modificación exitosa!");
            }
            this.limpiarTabla(this.vista1.tablaInscripcion);
            llenarFilas(this.vista1.tablaInscripcion);
            limpiarCuadros();
        }
        
        if (ae.getSource().equals(this.vista1.btnEliminarInscripcion)) { // Si tocan el botón Eliminar.
            if (this.modelo1.eliminarDatos(this.vista1.tablaInscripcion)) {
                limpiarTabla(this.vista1.tablaInscripcion);
                llenarFilas(this.vista1.tablaInscripcion);
                JOptionPane.showMessageDialog(null, "Eliminación exitosa!");
                limpiarCuadros();
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnVolverInscripcion)) { // Si tocan el botón Volver.
            MenuModelo modelo1 = new MenuModelo();
            MenuVista vista1 = new MenuVista();
            MenuControlador menuControlador = new MenuControlador(modelo1, vista1);
            this.vista1.dispose();
        }
    }

    public String[] nombreColumnas() { // Nombro mis columnas.
        String[] Columna = {"Código", "Nombre", "Fecha", "Código de Carrera"};
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
        ArrayList<Modelos.InscripcionModelo> inscripciones;
        inscripciones = this.modelo1.traerDatos();
        this.limpiarTabla(this.vista1.tablaInscripcion);
        Object datos[] = new Object[4];
        if (inscripciones.size() > 0) {
            for (int i = 0; i < inscripciones.size(); i++) {
                datos[0] = inscripciones.get(i).getCodigo();
                datos[1] = inscripciones.get(i).getNombre();
                datos[2] = inscripciones.get(i).getFecha();
                datos[3] = inscripciones.get(i).getCodigoCarrera();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        inscripciones.clear();
    }

    public void limpiarCuadros() { // Limpio cuadros. 
        this.vista1.txtCodigoInscripcion.setText("");
        this.vista1.txtNombreInscripcion.setText("");
        this.vista1.jdateFechaInsc.setDate(null);
        this.vista1.cboxCodCar.setSelectedIndex(0);
    }
    
    public void llenarComboBox(){  // Lleno items del comboBox.
        Set<String> codigoCarrera = this.modelo1.traerCodigoCarrera();
        Iterator<String> dniIterator = codigoCarrera.iterator();
        while(dniIterator.hasNext()){
            this.vista1.cboxCodCar.addItem(dniIterator.next());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {  
        if (me.getButton() == 1) {
            int fila = this.vista1.tablaInscripcion.rowAtPoint(me.getPoint());
            if (fila > -1) {
                this.vista1.txtCodigoInscripcion.setText(String.valueOf(this.vista1.tablaInscripcion.getValueAt(fila, 0)));
                this.vista1.txtNombreInscripcion.setText(String.valueOf(this.vista1.tablaInscripcion.getValueAt(fila, 1)));
                String date = String.valueOf(this.vista1.tablaInscripcion.getValueAt(fila, 2));
                try {
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    this.vista1.jdateFechaInsc.setDate(date2);
                } catch (ParseException ex) {
                    Logger.getLogger(AlumnoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.vista1.cboxCodCar.setSelectedItem(String.valueOf(this.vista1.tablaInscripcion.getValueAt(fila, 3)));
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
