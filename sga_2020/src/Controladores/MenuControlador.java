
package Controladores;

import Modelos.*;
import Vistas.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @Panella
 */
public class MenuControlador implements ActionListener {
    private MenuModelo menuModelo;
    private MenuVista menuVista;
    

    public MenuControlador(MenuModelo menuModelo, MenuVista menuVista) {
        this.menuModelo = menuModelo;
        this.menuVista = menuVista;
        menuVista.setTitle("MENÚ UTN FRM");
        menuVista.setLocationRelativeTo(null);
        menuVista.setVisible(true);
        botonesEnEscucha();
    }

    public void botonesEnEscucha() {
        this.menuVista.btnCarrera.addActionListener(this);
        this.menuVista.btnInscripcion.addActionListener(this);
        this.menuVista.btnAlumno.addActionListener(this);
        this.menuVista.btnCursado.addActionListener(this);
        this.menuVista.btnMateria.addActionListener(this);
        this.menuVista.btnProfesor.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.menuVista.btnCarrera)) {
            CarreraModelo carreraModelo = new CarreraModelo();
            CarreraVista carreraVista = new CarreraVista();
            CarreraControlador carreraControlador = new CarreraControlador(carreraModelo, carreraVista);
            this.menuVista.dispose();
        }
        
        if (evento.getSource().equals(this.menuVista.btnInscripcion)) {
            InscripcionModelo inscripcionModelo = new InscripcionModelo();
            InscripcionVista inscripcionVista = new InscripcionVista();
            InscripcionControlador inscripcionControlador = new InscripcionControlador(inscripcionModelo, inscripcionVista);
            this.menuVista.dispose();
        }
        
        if (evento.getSource().equals(this.menuVista.btnAlumno)) {
            AlumnoModelo alumnoModelo = new AlumnoModelo();
            AlumnoVista alumnoVista = new AlumnoVista();
            AlumnoControlador alumnoControlador = new AlumnoControlador(alumnoModelo, alumnoVista);
            this.menuVista.dispose();
        }
        
        if (evento.getSource().equals(this.menuVista.btnCursado)) {
            CursadoModelo cursadoModelo = new CursadoModelo();
            CursadoVista cursadoVista = new CursadoVista();
            CursadoControlador cursadoControlador = new CursadoControlador(cursadoModelo, cursadoVista);
            this.menuVista.dispose();
        }
        
        if (evento.getSource().equals(this.menuVista.btnMateria)) {
            MateriaModelo materiaModelo = new MateriaModelo();
            MateriaVista materiaVista = new MateriaVista();
            MateriaControlador materiaControlador = new MateriaControlador(materiaModelo, materiaVista);
            this.menuVista.dispose();
        }
        
        if (evento.getSource().equals(this.menuVista.btnProfesor)) {
            ProfesorModelo profesorModelo = new ProfesorModelo();
            ProfesorVista profesorVista = new ProfesorVista();
            ProfesorControlador profesorControlador = new ProfesorControlador(profesorModelo, profesorVista);
            this.menuVista.dispose();
        }
    }    
}
