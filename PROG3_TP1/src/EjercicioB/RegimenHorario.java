/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioB;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class RegimenHorario {
    private long id;
    private int horaIngreso;
    private int minutoIngreso;
    private int horaEgreso;
    private int minutoEgreso;
    private Empleado empleado;

    public RegimenHorario() {
    }

    public RegimenHorario(long id, int horaIngreso, int minutoIngreso, int horaEgreso, int minutoEgreso, Empleado empleado) {
        this.id = id;
        this.horaIngreso = horaIngreso;
        this.minutoIngreso = minutoIngreso;
        this.horaEgreso = horaEgreso;
        this.minutoEgreso = minutoEgreso;
        this.empleado = empleado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(int horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public int getMinutoIngreso() {
        return minutoIngreso;
    }

    public void setMinutoIngreso(int minutoIngreso) {
        this.minutoIngreso = minutoIngreso;
    }

    public int getHoraEgreso() {
        return horaEgreso;
    }

    public void setHoraEgreso(int horaEgreso) {
        this.horaEgreso = horaEgreso;
    }

    public int getMinutoEgreso() {
        return minutoEgreso;
    }

    public void setMinutoEgreso(int minutoEgreso) {
        this.minutoEgreso = minutoEgreso;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    } 
}
