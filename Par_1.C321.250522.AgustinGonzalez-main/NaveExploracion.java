/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.principal;

/**
 *
 * @author Agustin Gonzalez
 */

public class NaveExploracion extends Nave {
    private TipoMision tipoMision;

    public NaveExploracion(String nombre, int cap, int anio, TipoMision tipo) {
        super(nombre, cap, anio);
        this.tipoMision = tipo;
    }

    @Override
    public boolean puedeExplorar() {
        return true;
    }

    public String toString() {
        return super.toString() + " | Misión: " + tipoMision;
    }
}
