/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @ Panella Lautaro
 */
public class Componente {
    private long id;
    private String nombre;
    private String nroSerie;
    private Computadora computadora;

    public Componente() {
    }

    public Componente(long id, String nombre, String nroSerie, Computadora computadora) {
        this.id = id;
        this.nombre = nombre;
        this.nroSerie = nroSerie;
        this.computadora = computadora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }
}
