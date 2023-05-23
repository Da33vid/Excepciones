package com.mycompany.main;

import com.mycompany.Exception.ParkingException;
import com.mycompany.parking.Parking;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking("Parking Avenida", 10);
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        while (opcion != 4) {
            mostrarMenu();
            opcion = leerEntero(scanner);

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Cual es tu matricula: ");
                        String matricula = scanner.nextLine();
                        System.out.print("Cual es la plaza: ");
                        int plaza = leerEntero(scanner);
                        parking.entrada(matricula, plaza);
                        System.out.println("Has aparcado.");
                        mostrarEstadoParking(parking);
                        break;
                    case 2:
                        System.out.print("Cual es la matricula al salir: ");
                        String matriculaSalida = scanner.nextLine();
                        int plazaLiberada = parking.salida(matriculaSalida);
                        System.out.println("Te has ido. Plaza liberada: " + plazaLiberada);
                        mostrarEstadoParking(parking);
                        break;
                    case 3:
                        System.out.println(parking.toString());
                        break;
                    case 4:
                        System.out.println("Salir");
                        break;
                    default:
                        System.out.println("Opción inválida. Introduce un valor entre 1 y 4.");
                }
            } catch (ParkingException e) {
                System.out.println("Error: " + e.getMensaje() + " Matrícula: " + e.getMatricula());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("----- Menú -----");
        System.out.println("1) Entrada de coche");
        System.out.println("2) Salida de coche");
        System.out.println("3) Mostrar parking");
        System.out.println("4) Salir del programa");
        System.out.print("Elige una opción: ");
    }

    private static int leerEntero(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Opción inválida. Introduce un valor numérico.");
            scanner.nextLine(); // Limpiar entrada inválida
            return leerEntero(scanner);
        } finally {
            scanner.nextLine(); // Consumir el carácter de nueva línea pendiente
        }
    }

    private static void mostrarEstadoParking(Parking parking) {
        System.out.println("Plazas totales: " + parking.getPlazasTotales());
        System.out.println("Plazas ocupadas: " + parking.getPlazasOcupadas());
        System.out.println("Plazas libres: " + parking.getPlazasLibres());
    }
}