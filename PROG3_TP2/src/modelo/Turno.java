package modelo;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Panella Lautaro
 */
@Entity
public class Turno extends EntityBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Date fecha;
    private int hora;
    private int minutos;
    private Paciente paciente;
    private Medico medico;

    public Turno() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPaciente")
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMedico")
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
