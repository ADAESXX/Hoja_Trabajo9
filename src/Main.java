/**
 * Nombre del archivo: Main.java
 * Autor: Abigail Escobar
 * Fecha: 22/5/2026
 * Descripción: uprograma principal y menu 
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            Grafo grafo = new Grafo();
            grafo.cargarArchivo("guategrafo.txt");
            Floyd floyd = new Floyd(grafo);
            floyd.ejecutarFloyd();
            int opcion;
            do {

                System.out.println("\n===== MENÚ =====");
                System.out.println("1. Ruta más corta");
                System.out.println("2. Centro del grafo");
                System.out.println("3. Eliminar conexión");
                System.out.println("4. Agregar conexión");
                System.out.println("5. Salir");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 1:

                        System.out.print("Ciudad origen: ");
                        String origen =sc.nextLine();
                        System.out.print("Ciudad destino: ");
                        String destino = sc.nextLine();

                        floyd.mostrarRuta(origen,destino);
                        break;

                    case 2:

                        System.out.println("\nCentro del grafo: "+ CentroMasa.calcularCentro(floyd));
                        break;

                    case 3:

                        System.out.print("Origen: ");
                        String o = sc.nextLine();
                        System.out.print("Destino: ");
                        String d = sc.nextLine();

                        grafo.eliminarConexion(o, d);
                        floyd = new Floyd(grafo);
                        floyd.ejecutarFloyd();

                        System.out.println("Conexión eliminada.");
                        break;

                    case 4:

                        System.out.print("Origen: ");
                        String ori = sc.nextLine();
                        System.out.print("Destino: ");
                        String des = sc.nextLine();
                        System.out.print("Distancia: ");

                        int km = sc.nextInt();
                        grafo.agregarConexion(ori,des,km);
                        floyd = new Floyd(grafo);
                        floyd.ejecutarFloyd();

                        System.out.println("Conexión agregada.");
                        break;

                    case 5:

                        System.out.println("Programa finalizado.");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } while (opcion != 5);

        }

        catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}