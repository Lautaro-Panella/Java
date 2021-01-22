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
public class Control {
    private int id;
    private String denominacion;
    private boolean esObligatorio;
    private EstadoControl estadoControl;
    private List<Expediente> expedientes = new ArrayList<>();

    public Control() {
    }

    public Control(int id, String denominacion, boolean esObligatorio, EstadoControl estadoControl) {
        this.id = id;
        this.denominacion = denominacion;
        this.esObligatorio = esObligatorio;
        this.estadoControl = estadoControl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public boolean isEsObligatorio() {
        return esObligatorio;
    }

    public void setEsObligatorio(boolean esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

    public EstadoControl getEstadoControl() {
        return estadoControl;
    }

    public void setEstadoControl(EstadoControl estadoControl) {
        this.estadoControl = estadoControl;
    }

    public List<Expediente> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(List<Expediente> expedientes) {
        this.expedientes = expedientes;
    }
}
