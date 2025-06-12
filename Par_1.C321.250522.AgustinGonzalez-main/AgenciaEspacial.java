/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.principal;
import java.util.ArrayList;
/**
 *
 * @author Agustin Gonzalez
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AgenciaEspacial {
    private ArrayList<Nave> naves;

    public AgenciaEspacial() {
        naves = new ArrayList<>();
    }

    public void agregarNave(Nave nueva) {
        for (Nave n : naves) {
            if (n.equals(nueva)) {
                System.out.println("Ya existe una nave con ese nombre y año.");
                return;
            }
        }
        naves.add(nueva);
        System.out.println("Nave agregada correctamente.");
    }

    public void mostrarNaves() {
        if (naves.isEmpty()) {
            System.out.println("No hay naves registradas.");
            return;
        }
        for (Nave n : naves) {
            System.out.println(n);
        }
    }

    public void iniciarExploracion() {
        for (Nave n : naves) {
            if (n.puedeExplorar()) {
                System.out.println(n.getNombre() + ": Exploración iniciada.");
            } else {
                System.out.println(n.getNombre() + ": No participa en exploración.");
            }
        }
    }

    public void ordenarPorNombre() {
        Collections.sort(naves, new Comparator<Nave>() {
            public int compare(Nave a, Nave b) {
                return a.getNombre().compareToIgnoreCase(b.getNombre());
            }
        });
        mostrarNaves();
    }

    public void ordenarPorAnio() {
        Collections.sort(naves, new Comparator<Nave>() {
            public int compare(Nave a, Nave b) {
                return b.getAnioLanzamiento() - a.getAnioLanzamiento();
            }
        });
        mostrarNaves();
    }

    public void ordenarPorTripulacion() {
        Collections.sort(naves, new Comparator<Nave>() {
            public int compare(Nave a, Nave b) {
                return b.getCapacidadTripulacion() - a.getCapacidadTripulacion();
            }
        });
        mostrarNaves();
    }
}
