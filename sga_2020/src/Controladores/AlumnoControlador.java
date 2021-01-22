
package Controladores;

import Modelos.MenuModelo;
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
import Modelos.AlumnoModelo;
import Vistas.AlumnoVista;
import Vistas.MenuVista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @Panella
 */
public class AlumnoControlador implements ActionListener, MouseListener{
    private AlumnoVista vista1;
    private AlumnoModelo modelo1;
    private DefaultTableModel modeloTabla;

    public AlumnoControlador(AlumnoModelo modelo1, AlumnoVista vista1) {
        this.modelo1 = modelo1;
        this.vista1 = vista1;
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);
        vista1.setTitle("ALUMNOS");
        this.llenarFilas(this.vista1.tablaAlumno);
        botonesEnEscucha();
        llenarComboBox();
    }

    public void botonesEnEscucha() {
        this.vista1.btnAgregarAlumno.addActionListener(this);
        this.vista1.btnModificarAlumno.addActionListener(this);
        this.vista1.btnEliminarAlumno.addActionListener(this);
        this.vista1.btnVolverAlumno.addActionListener(this);
        this.vista1.tablaAlumno.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista1.btnAgregarAlumno)) { // Si tocan el botón Agregar.
            if (modelo1.validarIngreso(this.vista1.txtNombreAlumno.getText()) || modelo1.validarIngreso(this.vista1.txtApellidoAlumno.getText()) || 
                    modelo1.validarIngreso(this.vista1.txtDNIalumno.getText()) || modelo1.validarIngreso((this.vista1.jdateFechaNac.getDateFormatString()))) {   
                JOptionPane.showMessageDialog(null, "Debe llenar los campos Nombre, Apellido, Fecha de nacimiento y DNI.");
            } else if (modelo1.validarIngresoDNI(this.vista1.txtDNIalumno.getText())) {    
                JOptionPane.showMessageDialog(null, "DNI incorrecto.");
            } else {
                this.modelo1.setDni(Long.parseLong(this.vista1.txtDNIalumno.getText()));
                this.modelo1.setNombre(this.vista1.txtNombreAlumno.getText());
                this.modelo1.setApellido(this.vista1.txtApellidoAlumno.getText());
                java.util.Date date = this.vista1.jdateFechaNac.getDate();   
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.modelo1.setFechaNac(sqlDate);
                this.modelo1.setDomicilio(this.vista1.txtDomicilioAlumno.getText());
                this.modelo1.setTelefono(this.vista1.txtTelefonoAlumno.getText());
                this.modelo1.setCodInsc(Long.parseLong(this.vista1.cboxInsCod.getSelectedItem().toString())); 
                if (modelo1.dniUnico(modelo1)) {
                    if (this.modelo1.agregarDatos(modelo1)) {   
                        JOptionPane.showMessageDialog(null, "Carga exitosa!");
                    }
                    this.limpiarTabla(this.vista1.tablaAlumno);
                    llenarFilas(this.vista1.tablaAlumno);
                    limpiarCuadros();
                } else {
                    JOptionPane.showMessageDialog(null, "Alumno existente.");     
                }
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnModificarAlumno)) { // Si tocan el botón Modificar.
            this.modelo1.setDni(Long.parseLong(this.vista1.txtDNIalumno.getText()));
            this.modelo1.setNombre(this.vista1.txtNombreAlumno.getText());
            this.modelo1.setApellido(this.vista1.txtApellidoAlumno.getText());
            java.util.Date date = this.vista1.jdateFechaNac.getDate();  
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            this.modelo1.setFechaNac(sqlDate);
            this.modelo1.setDomicilio(this.vista1.txtDomicilioAlumno.getText());
            this.modelo1.setTelefono(this.vista1.txtTelefonoAlumno.getText());
            this.modelo1.setCodInsc(Long.parseLong(this.vista1.cboxInsCod.getSelectedItem().toString()));
            if (this.modelo1.modificarDatos(modelo1)) {
                JOptionPane.showMessageDialog(null, "Modificación exitosa!");
            }
            this.limpiarTabla(this.vista1.tablaAlumno);
            llenarFilas(this.vista1.tablaAlumno);
            limpiarCuadros();
            this.vista1.txtDNIalumno.setEditable(true);
        }
        
        if (ae.getSource().equals(this.vista1.btnEliminarAlumno)) { // Si tocan el botón Eliminar.
            if (this.modelo1.eliminarDatos(this.vista1.tablaAlumno)) {
                limpiarTabla(this.vista1.tablaAlumno);
                llenarFilas(this.vista1.tablaAlumno);
                JOptionPane.showMessageDialog(null, "Eliminación exitosa!");
                limpiarCuadros();
                this.vista1.txtDNIalumno.setEditable(true);
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnVolverAlumno)) { // Si tocan el botón Volver.
            MenuModelo modelo1 = new MenuModelo();
            MenuVista vista1 = new MenuVista();
            MenuControlador menuControlador = new MenuControlador(modelo1, vista1);
            this.vista1.dispose();
        }
    }

    public String[] nombreColumnas() { // Nombro mis columnas.
        String[] Columna = {"DNI", "Nombre", "Apellido", "Fecha de Nacimiento", "Domicilio", "Telefono", "Codigo de Inscripcion"};
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
        ArrayList<Modelos.AlumnoModelo> alumnos;
        alumnos = this.modelo1.traerDatos();
        this.limpiarTabla(this.vista1.tablaAlumno);
        Object datos[] = new Object[7];
        if (alumnos.size() > 0) {
            for (int i = 0; i < alumnos.size(); i++) {
                datos[0] = alumnos.get(i).getDni();
                datos[1] = alumnos.get(i).getNombre();
                datos[2] = alumnos.get(i).getApellido();
                datos[3] = alumnos.get(i).getFechaNac();
                datos[4] = alumnos.get(i).getDomicilio();
                datos[5] = alumnos.get(i).getTelefono();
                datos[6] = alumnos.get(i).getCodInsc();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        alumnos.clear();
    }

    public void limpiarCuadros() { // Limpio cuadros.
        this.vista1.txtDNIalumno.setText("");
        this.vista1.txtNombreAlumno.setText("");
        this.vista1.txtApellidoAlumno.setText("");
        this.vista1.jdateFechaNac.setDate(null);
        this.vista1.txtDomicilioAlumno.setText("");
        this.vista1.txtTelefonoAlumno.setText("");
        this.vista1.cboxInsCod.setSelectedIndex(0);
    }
    
    public void llenarComboBox(){ // Lleno el comboBox.
        Set<String> dni = this.modelo1.traerCodInsc();
        Iterator<String> dniIterator = dni.iterator();
        while(dniIterator.hasNext()){
            this.vista1.cboxInsCod.addItem(dniIterator.next());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) { 
        if (me.getButton() == 1) {
            int fila = this.vista1.tablaAlumno.rowAtPoint(me.getPoint());
            if (fila > -1) {
                this.vista1.txtDNIalumno.setText(String.valueOf(this.vista1.tablaAlumno.getValueAt(fila, 0)));
                this.vista1.txtDNIalumno.setEditable(false);
                this.vista1.txtNombreAlumno.setText(String.valueOf(this.vista1.tablaAlumno.getValueAt(fila, 1)));
                this.vista1.txtApellidoAlumno.setText(String.valueOf(this.vista1.tablaAlumno.getValueAt(fila, 2)));
                String date = String.valueOf(this.vista1.tablaAlumno.getValueAt(fila, 3));
                try {
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    this.vista1.jdateFechaNac.setDate(date2);
                } catch (ParseException ex) {
                    Logger.getLogger(AlumnoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.vista1.txtDomicilioAlumno.setText(String.valueOf(this.vista1.tablaAlumno.getValueAt(fila, 4)));
                this.vista1.txtTelefonoAlumno.setText(String.valueOf(this.vista1.tablaAlumno.getValueAt(fila, 5)));
                this.vista1.cboxInsCod.setSelectedItem(String.valueOf(this.vista1.tablaAlumno.getValueAt(fila, 6)));
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
