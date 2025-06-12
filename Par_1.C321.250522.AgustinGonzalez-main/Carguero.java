/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.principal;

/**
 *
 * @author Agustin Gonzalez
 */

public class Carguero extends Nave {
    private int capacidadCarga;

    public Carguero(String nombre, int cap, int anio, int carga) {
        super(nombre, cap, anio);
        if (carga < 100) {
            this.capacidadCarga = 100;
        } else if (carga > 500) {
            this.capacidadCarga = 500;
        } else {
            this.capacidadCarga = carga;
        }
    }

    @Override
    public boolean puedeExplorar() {
        return true;
    }

    public String toString() {
        return super.toString() + " | Carga: " + capacidadCarga + " toneladas";
    }
}
