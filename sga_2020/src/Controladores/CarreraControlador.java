
package Controladores;

import Modelos.MenuModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelos.CarreraModelo;
import Vistas.CarreraVista;
import Vistas.MenuVista;

/**
 *
 * @Panella
 */
public class CarreraControlador implements ActionListener, MouseListener {
    private CarreraModelo modelo1;
    private CarreraVista vista1;
    private DefaultTableModel modeloTabla;
    
    public CarreraControlador(CarreraModelo modelo1, CarreraVista vista1) {
        this.modelo1 = modelo1;
        this.vista1 = vista1;
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);
        vista1.setTitle("CARRERAS");
        this.llenarFilas(this.vista1.tablaCarrera);
        botonesEnEscucha();
    }

    public void botonesEnEscucha() { 
        this.vista1.btnAgregarCarrera.addActionListener(this);
        this.vista1.btnModificarCarrera.addActionListener(this);
        this.vista1.btnEliminarCarrera.addActionListener(this);
        this.vista1.btnVolverCarrera.addActionListener(this);
        this.vista1.tablaCarrera.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.vista1.btnAgregarCarrera)) { // Si tocan el botón Agregar.
            if (modelo1.validarIngreso(this.vista1.txtNombreCarrera.getText()) || modelo1.validarIngreso(this.vista1.txtDuracionCarrera.getText())) {
                JOptionPane.showMessageDialog(null, "Debe llenar los campos Nombre y Duración."); // Si faltan datos (*).
            } else {
                this.modelo1.setNombre(this.vista1.txtNombreCarrera.getText());
                this.modelo1.setDuracion(Long.parseLong(this.vista1.txtDuracionCarrera.getText()));
                if (modelo1.carreraUnica(modelo1)) { // Si se cumple que la carrera es única la cargo.
                    if (this.modelo1.agregarDatos(modelo1)) {
                        JOptionPane.showMessageDialog(null, "Carga exitosa!");
                    }
                    this.limpiarTabla(this.vista1.tablaCarrera);
                    llenarFilas(this.vista1.tablaCarrera);
                    limpiarCuadros();
                } else { // Si la carrera ya existe no hago la carga de datos.
                    JOptionPane.showMessageDialog(null, "Carrera existente.");
                }
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnModificarCarrera)) { // Si tocan el botón Modificar.
            this.modelo1.setCodigo(Long.parseLong(this.vista1.txtCodigoCarrera.getText()));
            this.modelo1.setNombre(this.vista1.txtNombreCarrera.getText());
            this.modelo1.setDuracion(Long.parseLong(this.vista1.txtDuracionCarrera.getText()));

            if (this.modelo1.modificarDatos(modelo1)) {
                JOptionPane.showMessageDialog(null, "Modificación exitosa!");
            }
            this.limpiarTabla(this.vista1.tablaCarrera);
            llenarFilas(this.vista1.tablaCarrera);
            limpiarCuadros();
        }
        
        if (ae.getSource().equals(this.vista1.btnEliminarCarrera)) { // Si tocan el botón Eliminar.
            if (this.modelo1.eliminarDatos(this.vista1.tablaCarrera)) {
                limpiarTabla(this.vista1.tablaCarrera);
                llenarFilas(this.vista1.tablaCarrera);
                JOptionPane.showMessageDialog(null, "Eliminación exitosa!");
                limpiarCuadros();
            }
        }
        
        if (ae.getSource().equals(this.vista1.btnVolverCarrera)) { // Si tocan el botón Volver.
            MenuModelo modelo1 = new MenuModelo();
            MenuVista vista1 = new MenuVista();
            MenuControlador menuControlador = new MenuControlador(modelo1, vista1);
            this.vista1.dispose();
        }    
    }

    public String[] nombreColumnas() { // Nombro mis columnas.
        String[] Columna = {"Código", "Nombre", "Duración"};
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
        ArrayList<Modelos.CarreraModelo> carreras;
        carreras = this.modelo1.traerDatos();
        this.limpiarTabla(this.vista1.tablaCarrera);
        Object datos[] = new Object[3];
        if (carreras.size() > 0) {
            for (int i = 0; i < carreras.size(); i++) {
                datos[0] = carreras.get(i).getCodigo();
                datos[1] = carreras.get(i).getNombre();
                datos[2] = carreras.get(i).getDuracion();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        carreras.clear();
    }

    public void limpiarCuadros() { // Limpio cuadros.
        this.vista1.txtCodigoCarrera.setText("");
        this.vista1.txtNombreCarrera.setText("");
        this.vista1.txtDuracionCarrera.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == 1) {
            int fila = this.vista1.tablaCarrera.rowAtPoint(me.getPoint());
            if (fila > -1) {
                this.vista1.txtCodigoCarrera.setText(String.valueOf(this.vista1.tablaCarrera.getValueAt(fila, 0)));
                this.vista1.txtNombreCarrera.setText(String.valueOf(this.vista1.tablaCarrera.getValueAt(fila, 1)));
                this.vista1.txtDuracionCarrera.setText(String.valueOf(this.vista1.tablaCarrera.getValueAt(fila, 2)));
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
