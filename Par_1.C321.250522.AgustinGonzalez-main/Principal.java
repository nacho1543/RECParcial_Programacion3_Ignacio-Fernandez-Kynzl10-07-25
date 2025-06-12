/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.principal;
import java.util.Scanner;
/**
 *
 * @author Agustin Gonzalez
 */
public class Principal {
    public static void main(String[] args) {
        AgenciaEspacial agencia = new AgenciaEspacial();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ EXPEDICIONES ---");
            System.out.println("1. Agregar nave");
            System.out.println("2. Mostrar naves");
            System.out.println("3. Iniciar exploración");
            System.out.println("4. Ordenar por nombre");
            System.out.println("5. Ordenar por año");
            System.out.println("6. Ordenar por tripulación");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Tipo de nave (1:Exploración, 2:Carguero, 3:Crucero): ");
                    int tipo = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Tripulación: ");
                    int trip = sc.nextInt();
                    System.out.print("Año de lanzamiento: ");
                    int anio = sc.nextInt();

                    if (tipo == 1) {
                        System.out.print("Misión (1:CARTOGRAFIA, 2:INVESTIGACION, 3:CONTACTO): ");
                        int m = sc.nextInt();
                        TipoMision tipoMision = switch (m) {
                            case 1 -> TipoMision.CARTOGRAFIA;
                            case 2 -> TipoMision.INVESTIGACION;
                            case 3 -> TipoMision.CONTACTO;
                            default -> null;
                        };
                        if (tipoMision != null) {
                            agencia.agregarNave(new NaveExploracion(nombre, trip, anio, tipoMision));
                        } else {
                            System.out.println("Tipo de misión inválido.");
                        }
                    } else if (tipo == 2) {
                        System.out.print("Capacidad de carga (100-500): ");
                        int carga = sc.nextInt();
                        agencia.agregarNave(new Carguero(nombre, trip, anio, carga));
                    } else if (tipo == 3) {
                        System.out.print("Cantidad de pasajeros: ");
                        int pas = sc.nextInt();
                        agencia.agregarNave(new CruceroEstelar(nombre, trip, anio, pas));
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                }
                case 2 -> agencia.mostrarNaves();
                case 3 -> agencia.iniciarExploracion();
                case 4 -> agencia.ordenarPorNombre();
                case 5 -> agencia.ordenarPorAnio();
                case 6 -> agencia.ordenarPorTripulacion();
                case 7 -> System.out.println("Sistema finalizado.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }
}