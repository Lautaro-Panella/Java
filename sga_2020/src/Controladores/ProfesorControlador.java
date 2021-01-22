
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Vistas.ProfesorVista;
import Modelos.ProfesorModelo;

/**
 *
 * @Panella
 */
public class ProfesorControlador implements ActionListener, MouseListener {
    private ProfesorModelo modelo1;
    private ProfesorVista vista1;
    private DefaultTableModel modeloTabla;

    public ProfesorControlador(ProfesorModelo modelo1, ProfesorVista vista1) {
        this.modelo1 = modelo1;
        this.vista1 = vista1;
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);
        vista1.setTitle("PROFESORES");
        this.llenarFilas(this.vista1.tablaProfesor);
        botonesEnEscucha();       
    }

    public void botonesEnEscucha() {
        this.vista1.btnAgregarProfesor.addActionListener(this);
        this.vista1.btnModificarProfesor.addActionListener(this);
        this.vista1.btnEliminarProfesor.addActionListener(this);
        this.vista1.btnVolverProfesor.addActionListener(this);
        this.vista1.tablaProfesor.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista1.btnAgregarProfesor)) { // Si tocan el botón Agregar.  
            if (modelo1.validarIngreso(this.vista1.txtNombreProfesor.getText()) || 
                    modelo1.validarIngreso(this.vista1.txtApellidoProfesor.getText()) || 
                    modelo1.validarIngreso(this.vista1.txtDniProfesor.getText()) || 
                    modelo1.validarIngreso((this.vista1.dateChProfesor.getDateFormatString()))) { // Si faltan datos (*).  
                JOptionPane.showMessageDialog(null, "Debe llenar los campos Nombre, Apellido, Fecha de nacimiento y DNI.");
            } else if (modelo1.validarIngresoDNI(this.vista1.txtDniProfesor.getText())) {   
                JOptionPane.showMessageDialog(null, "El DNI no es válido");
            } else {
                this.modelo1.setDni(Long.parseLong(this.vista1.txtDniProfesor.getText()));
                this.modelo1.setNombre(this.vista1.txtNombreProfesor.getText());
                this.modelo1.setApellido(this.vista1.txtApellidoProfesor.getText());
                java.util.Date date = this.vista1.dateChProfesor.getDate();  
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.modelo1.setFechaNac(sqlDate);
                this.modelo1.setDomicilio(this.vista1.txtDomicilioProfesor.getText());
                this.modelo1.setTelefono(this.vista1.txtTelefonoProfesor.getText());               
                if (modelo1.dniUnico(modelo1)) {
                    if (this.modelo1.agregarDatos(modelo1)) {   
                        JOptionPane.showMessageDialog(null, "Carga exitosa!");
                    }
                    this.limpiarTabla(this.vista1.tablaProfesor);
                    llenarFilas(this.vista1.tablaProfesor);
                    limpiaCuadros();
                } else {
                    JOptionPane.showMessageDialog(null, "Profesor existente.");    
                }
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnModificarProfesor)) { // Si tocan el botón Modificar.
            this.modelo1.setDni(Long.parseLong(this.vista1.txtDniProfesor.getText()));
            this.modelo1.setNombre(this.vista1.txtNombreProfesor.getText());
            this.modelo1.setApellido(this.vista1.txtApellidoProfesor.getText());
            java.util.Date date = this.vista1.dateChProfesor.getDate();  
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            this.modelo1.setFechaNac(sqlDate);
            this.modelo1.setDomicilio(this.vista1.txtDomicilioProfesor.getText());
            this.modelo1.setTelefono(this.vista1.txtTelefonoProfesor.getText());

            if (this.modelo1.modifica(modelo1)) {
                JOptionPane.showMessageDialog(null, "Modificación exitosa!");
            }
            this.limpiarTabla(this.vista1.tablaProfesor);
            llenarFilas(this.vista1.tablaProfesor);
            limpiaCuadros();
            this.vista1.txtDniProfesor.setEditable(true);
        }
        
        if (ae.getSource().equals(this.vista1.btnEliminarProfesor)) { // Si tocan el botón Eliminar.  
            if (this.modelo1.eliminarDatos(this.vista1.tablaProfesor)) {
                limpiarTabla(this.vista1.tablaProfesor);
                llenarFilas(this.vista1.tablaProfesor);
                JOptionPane.showMessageDialog(null, "Eliminación exitosa!");
                limpiaCuadros();
                this.vista1.txtDniProfesor.setEditable(true);
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnVolverProfesor)) { // Si tocan el botón Volver. 
            Vistas.MenuVista vis = new Vistas.MenuVista();
            Modelos.MenuModelo mod = new Modelos.MenuModelo();
            MenuControlador menuControlador = new MenuControlador(mod, vis);
            this.vista1.dispose();
        }  
    }

    public String[] nombreColumnas() { // Nombro mis columnas.
        String[] Columna = {"DNI", "Nombre", "Apellido", "Fecha de Nacimiento", "Domicilio", "Telefono"};
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
        ArrayList<Modelos.ProfesorModelo> profesores;
        profesores = this.modelo1.traerDatos();
        this.limpiarTabla(this.vista1.tablaProfesor);
        Object datos[] = new Object[6];
        if (profesores.size() > 0) {
            for (int i = 0; i < profesores.size(); i++) {
                datos[0] = profesores.get(i).getDni();
                datos[1] = profesores.get(i).getNombre();
                datos[2] = profesores.get(i).getApellido();
                datos[3] = profesores.get(i).getFechaNac();
                datos[4] = profesores.get(i).getDomicilio();
                datos[5] = profesores.get(i).getTelefono();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        profesores.clear();
    }

    public void limpiaCuadros() { // Limpio cuadros. 
        this.vista1.txtDniProfesor.setText("");
        this.vista1.txtNombreProfesor.setText("");
        this.vista1.txtApellidoProfesor.setText("");
        this.vista1.dateChProfesor.setDate(null);
        this.vista1.txtDomicilioProfesor.setText("");
        this.vista1.txtTelefonoProfesor.setText("");
    }
    

    @Override
    public void mouseClicked(MouseEvent me) { 
        if (me.getButton() == 1) {
            int fila = this.vista1.tablaProfesor.rowAtPoint(me.getPoint());
            if (fila > -1) {
                this.vista1.txtDniProfesor.setText(String.valueOf(this.vista1.tablaProfesor.getValueAt(fila, 0)));
                this.vista1.txtDniProfesor.setEditable(false);
                this.vista1.txtNombreProfesor.setText(String.valueOf(this.vista1.tablaProfesor.getValueAt(fila, 1)));
                this.vista1.txtApellidoProfesor.setText(String.valueOf(this.vista1.tablaProfesor.getValueAt(fila, 2)));
                String date = String.valueOf(this.vista1.tablaProfesor.getValueAt(fila, 3));
                try {
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    this.vista1.dateChProfesor.setDate(date2);
                } catch (ParseException ex) {
                    Logger.getLogger(AlumnoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.vista1.txtDomicilioProfesor.setText(String.valueOf(this.vista1.tablaProfesor.getValueAt(fila, 4)));
                this.vista1.txtTelefonoProfesor.setText(String.valueOf(this.vista1.tablaProfesor.getValueAt(fila, 5)));
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
