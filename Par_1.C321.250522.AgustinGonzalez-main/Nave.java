/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.principal;

/**
 *
 * @author Agustin Gonzalez
 */

public abstract class Nave {
    protected String nombre;
    protected int capacidadTripulacion;
    protected int anioLanzamiento;

    public Nave(String nombre, int capacidadTripulacion, int anioLanzamiento) {
        this.nombre = nombre;
        this.capacidadTripulacion = capacidadTripulacion;
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public int getCapacidadTripulacion() {
        return capacidadTripulacion;
    }

    public boolean equals(Nave otra) {
        return this.nombre.equalsIgnoreCase(otra.nombre) &&
               this.anioLanzamiento == otra.anioLanzamiento;
    }

    public String toString() {
        return "Nombre: " + nombre + " | Tripulación: " + capacidadTripulacion +
               " | Año: " + anioLanzamiento;
    }

    public abstract boolean puedeExplorar();
}
