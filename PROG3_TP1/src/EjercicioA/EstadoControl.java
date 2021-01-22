/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioA;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class EstadoControl {
    private long id;
    private boolean aprobado;
    private List<Control> controles = new ArrayList<>();

    public EstadoControl() {
    }

    public EstadoControl(long id, boolean aprobado) {
        this.id = id;
        this.aprobado = aprobado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public List<Control> getControles() {
        return controles;
    }

    public void setControles(List<Control> controles) {
        this.controles = controles;
    }
}
