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
public class Barrio {
    private long id;
    private String nombre;
    private String empresaConstructora;
    private List<Vivienda> viviendas = new ArrayList<>();

    public Barrio() {
    }

    public Barrio(long id, String nombre, String empresaConstructora) {
        this.id = id;
        this.nombre = nombre;
        this.empresaConstructora = empresaConstructora;
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

    public String getEmpresaConstructora() {
        return empresaConstructora;
    }

    public void setEmpresaConstructora(String empresaConstructora) {
        this.empresaConstructora = empresaConstructora;
    }

    public List<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }
    
    public double getSuperficieTotalTerreno() {
        double totalMetrosTerreno = 0;
        if (getViviendas() != null) {
            for (Vivienda v : viviendas) {
                totalMetrosTerreno += v.getSuperficieTerreno();
            }
        }
        return totalMetrosTerreno;
    }
    
    public double getSuperficieTotalCubierta() throws Exception {
        double totalMetrosCubiertos = 0;
        if (getViviendas() != null) {
            for (Vivienda v : viviendas) {
                totalMetrosCubiertos += v.getMetrosCuadradosCubiertos();
            }
        }
        return totalMetrosCubiertos;
    }
}
