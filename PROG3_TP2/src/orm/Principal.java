package orm;

import controlador.Gestor;
import java.util.Date;
import modelo.*;

/**
 *
 * @author Panella Lautaro
 */
public class Principal {

    public static void main(String[] args) {

        try {

            // Creo objetos.
            Gestor gestor = new Gestor();
            
            Date fecha1 = new Date(2020, 1, 17);
            Date fecha2 = new Date(2020, 2, 18);
            Date fecha3 = new Date(2020, 3, 19);
            Date fecha4 = new Date(2020, 4, 20);
            Date fecha5 = new Date(2020, 5, 21);
            Date fecha6 = new Date(2020, 6, 22);
            
            Domicilio dom1 = new Domicilio();
            Domicilio dom2 = new Domicilio();
            Especialidad esp1 = new Especialidad();
            Especialidad esp2 = new Especialidad();
            Empleado emp1 = new Empleado();
            Medico med1 = new Medico();
            Medico med2 = new Medico();
            Paciente pac1 = new Paciente();
            Paciente pac2 = new Paciente();
            Turno tur1 = new Turno();
            Turno tur2 = new Turno();
            HistoriaClinica hc1 = new HistoriaClinica();
            HistoriaClinica hc2 = new HistoriaClinica();
            DetalleHistoriaClinica dhc1 = new DetalleHistoriaClinica();
            DetalleHistoriaClinica dhc2 = new DetalleHistoriaClinica();

            // Seteo especialidades:
            esp1.setDenominacion("Clínico");
            esp2.setDenominacion("Obstetra");

            // Seteo médico 1:
            med1.setNombre("Jorge");
            med1.setApellido("Rodriguez");
            med1.setDni(14002192);
            med1.setMatricula(1234);
            med1.getEspecialidades().add(esp1);
            med1.getTurnos().add(tur1);

            // Seteo domicilio del médico 1:
            dom1.setCalle("JJ Castelli");
            dom1.setLocalidad("Maipú");
            dom1.setNumero(3183);
            dom1.setPersona(med1);
            
            // Seteo médico 2:
            med2.setNombre("Victor");
            med2.setApellido("Meli");
            med2.setDni(16059341);
            med2.setMatricula(2345);
            med2.getEspecialidades().add(esp2);
            med2.getTurnos().add(tur2);
            
            // Seteo domicilio del médico 2:
            dom1.setCalle("Lencinas");
            dom1.setLocalidad("Godoy Cruz");
            dom1.setNumero(261);
            dom1.setPersona(med2);

            // Seteo empleado 1:
            emp1.setNroLegajo(987);
            emp1.setNombre("Santino");
            emp1.setApellido("López");
            emp1.setDni(37615231);
            emp1.setSueldo(65700);

            // Seteo domicilio del empleado 1:
            dom2.setCalle("Belgrano");
            dom2.setLocalidad("Capital");
            dom2.setNumero(2320);
            dom2.setPersona(emp1);

            // Seteo paciente 1:
            pac1.setNombre("Maximiliano");
            pac1.setApellido("Vejar");
            pac1.setDni(32880456);
            pac1.setNroSocio(99000);
            
            // Seteo paciente 2:
            pac1.setNombre("Mirta");
            pac1.setApellido("Aguilar");
            pac1.setDni(29456324);
            pac1.setNroSocio(88000);

            // Seteo historia clínica 1:
            hc1.setFechaAlta(fecha1);
            hc1.setPaciente(pac1); // Corresponde al paciente 1.
            hc1.setNumero(66001);
            hc1.getDetalles().add(dhc1);
            
            // Seteo historia clínica 2:
            hc2.setFechaAlta(fecha2);
            hc2.setPaciente(pac2); // Corresponde al paciente 2.
            hc2.setNumero(45002);
            hc2.getDetalles().add(dhc2);

            // Seteo detalle de la historia clínica 1:
            dhc1.setDiagnostico("Covid 19");
            dhc1.setSintomas("Fiebre");
            dhc1.setObservaciones("Dificultad respiratoria");
            dhc1.setFechaAtencion(fecha3); 
            dhc1.setHistoriaClinica(hc1); 
            
            // Seteo detalle de la historia clínica 2:
            dhc2.setDiagnostico("Control Pre-parto");
            dhc2.setSintomas("Sangrado");
            dhc2.setObservaciones("Dolor abdominal");
            dhc2.setFechaAtencion(fecha4); 
            dhc2.setHistoriaClinica(hc2); 

            // Seteo turno 1:
            tur1.setFecha(fecha5);
            tur1.setHora(11);
            tur1.setMinutos(30);
            tur1.setMedico(med1); // Corresponde a médico 1.
            tur1.setPaciente(pac1); // Corresponde a paciente 1.
            
            // Seteo turno 2:
            tur2.setFecha(fecha6);
            tur2.setHora(9);
            tur2.setMinutos(30);
            tur2.setMedico(med2); // Corresponde a médico 2.
            tur2.setPaciente(pac2); // Corresponde a paciente 2.

            gestor.guardar(tur1);
            gestor.guardar(emp1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
