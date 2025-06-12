/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.principal;

/**
 *
 * @author Agustin Gonzalez
 */

public class CruceroEstelar extends Nave {
    private int cantidadPasajeros;

    public CruceroEstelar(String nombre, int cap, int anio, int pasajeros) {
        super(nombre, cap, anio);
        this.cantidadPasajeros = pasajeros;
    }

    @Override
    public boolean puedeExplorar() {
        return false;
    }

    public String toString() {
        return super.toString() + " | Pasajeros: " + cantidadPasajeros;
    }
}
