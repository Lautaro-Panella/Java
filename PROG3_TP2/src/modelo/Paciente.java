package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Panella Lautaro
 */
@Entity
public class Paciente extends Persona implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int nroSocio;
    private HistoriaClinica historiaClinica;
    private List<Turno> turnos = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(int nroSocio, HistoriaClinica historiaClinica, String nombre, String apellido, long dni, Domicilio domicilio) {
        super(nombre, apellido, dni, domicilio);
        this.nroSocio = nroSocio;
        this.historiaClinica = historiaClinica;
    }

    public int getNroSocio() {
        return nroSocio;
    }

    public void setNroSocio(int nroSocio) {
        this.nroSocio = nroSocio;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idHistoriaClinica")
    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
