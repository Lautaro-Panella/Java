/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioC;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Persona {
    private String nombre;
    private String tipoDocumento;
    private long nroDocumento;
    private int telefono;
    private String email;
    private String celular;
    private Sector sector;
    private List<Actividad> actividades = new ArrayList<>();

    public Persona() {
    }

    public Persona(String nombre, String tipoDocumento, long nroDocumento, int telefono, String email, String celular, Sector sector) {
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.celular = celular;
        this.sector = sector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }
    
    public double totalPuntosAsignados() {
        double total = 0;
        if(getActividades() != null) {
            for (Actividad a : actividades) {
                total += a.getTipoActividad().getPuntosAsignados();
            }
        }
        return total;
    }
    
    public double totalPuntosAsignados(int codigo) {
        double puntosAct = 0;
        if(getActividades() != null) {
            for (Actividad a : actividades) {
                if (a.getTipoActividad().getCodigo() == codigo) {
                    puntosAct += a.getTipoActividad().getPuntosAsignados();
                }
            }
        }
        return puntosAct;
    }
    
    public double totalPuntosAsignados(int codigo, int anio) {
        double puntosAct = 0;
        if(getActividades() != null) {
            for (Actividad a : actividades) {
                if (a.getTipoActividad().getCodigo() == codigo && a.getFechaInicio().getYear()+1900 <= anio && a.getFechaFin().getYear()+1900 >= anio) {
                    puntosAct += a.getTipoActividad().getPuntosAsignados();
                }
            }
        }
        return puntosAct;
    }    
}
