/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioD;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Habitacion {
    private int id;
    private String nombre;
    private long metrosCuadrados;
    private Vivienda vivienda;

    public Habitacion() {
    }

    public Habitacion(int id, String nombre, long metrosCuadrados, Vivienda vivienda) {
        this.id = id;
        this.nombre = nombre;
        this.metrosCuadrados = metrosCuadrados;
        this.vivienda = vivienda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(long metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }
}
