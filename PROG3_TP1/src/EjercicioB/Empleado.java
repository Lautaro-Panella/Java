/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioB;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Empleado {
    private String nombre;
    private long cuit;
    private String domicilio;
    private String email;
    private RegimenHorario regimenHorario;
    private List<Tardanza> tardanzas = new ArrayList<>();
    private List<Asistencia> asistencias = new ArrayList<>();

    public Empleado() {
    }

    public Empleado(String nombre, long cuit, String domicilio, String email, RegimenHorario regimen) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.email = email;
        this.regimenHorario = regimen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegimenHorario getRegimen() {
        return regimenHorario;
    }

    public void setRegimen(RegimenHorario regimen) {
        this.regimenHorario = regimen;
    }

    public List<Tardanza> getTardanzas() {
        return tardanzas;
    }

    public void setTardanzas(List<Tardanza> tardanzas) {
        this.tardanzas = tardanzas;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }
    
    public List<Asistencia> getAsistenciaXMesXAnio(int mes, int anio) {
        List<Asistencia> asistenciaMesAnio = new ArrayList<>();
        if (getAsistencias() != null) {
            for (Asistencia a : this.asistencias) {
                if (a.getFecha().getMonth() == mes && a.getFecha().getYear()+1900 == anio) {
                    asistenciaMesAnio.add(a);
                }
            }
        }
        return asistenciaMesAnio;
    }
    public List<Tardanza> getDiasConTardanza(int mes, int anio) {
        List<Tardanza> tardanzaMesAnio = new ArrayList<>();
        if (getAsistenciaXMesXAnio(mes, anio) != null) {
            for (Asistencia a : getAsistenciaXMesXAnio(mes, anio)) {
                if (a.getMinuto() > this.regimenHorario.getMinutoIngreso() + 15 && a.getHora() >= this.regimenHorario.getHoraIngreso()) {
                    Tardanza t = new Tardanza(a.getId(), a.getTipo(), a.getFecha(), a.getHora(), a.getMinuto(), a.getEmpleado());
                    tardanzaMesAnio.add(t);
                }
            }
        }
        return tardanzaMesAnio;
    }
}
