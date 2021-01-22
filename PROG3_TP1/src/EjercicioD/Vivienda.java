/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioD;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Vivienda {
    private long id;
    private String calle;
    private int numeroCalle;
    private double superficieTerreno;
    private Barrio barrio;
    private List<Habitacion> habitaciones = new ArrayList<>();

    public Vivienda() {
    }

    public Vivienda(long id, String calle, int numeroCalle, double superficieTerreno, Barrio barrio) {
        this.id = id;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.superficieTerreno = superficieTerreno;
        this.barrio = barrio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public double getSuperficieTerreno() {
        return superficieTerreno;
    }

    public void setSuperficieTerreno(double superficieTerreno) {
        this.superficieTerreno = superficieTerreno;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    public double getMetrosCuadradosCubiertos() throws Exception {
        double metrosHab = 0;
        if (getHabitaciones() != null) {
            for (Habitacion h : habitaciones) {
                metrosHab += h.getMetrosCuadrados();
            }
        }
        if (metrosHab > this.superficieTerreno) {
            throw new Exception("La superficie cubierta no puede ser mayor a la superficie del terreno.");
        }
        return metrosHab;
    }
}
