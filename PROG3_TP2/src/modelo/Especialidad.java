package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Panella Lautaro
 */
@Entity
public class Especialidad extends EntityBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String denominacion;
    private List<Medico> medicos = new ArrayList<>();

    public Especialidad() {
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "especialidades",
            targetEntity = Medico.class
    )
    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}
