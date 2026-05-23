/**
 * Nombre del archivo: Floy.java
 * Autor: Abigail Escobar
 * Fecha: 22/5/2026
 * Descripción: utilizar el algoritmo de floyd para encontrar la ruta más corta para llegar de un punto a otro
 */

import java.util.ArrayList;

public class Floyd {
    //matriz de distnacias mínimas
    private int[][] dist;
    //Guarda los nodos intermedios
    private String[][] caminos;
    private ArrayList<String> ciudades;
    private int INF;

    //Constructor
    public Floyd(Grafo grafo) {

        ciudades = grafo.getCiudades();
        INF = grafo.getINF();
        int n = ciudades.size();

        dist = new int[n][n];
        caminos = new String[n][n];

        int[][] matriz = grafo.getMatriz();
        //copia a la matriz original
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                dist[i][j] = matriz[i][j];
                caminos[i][j] = "";
            }
        }
    }

    public void ejecutarFloyd() {

        int n = dist.length;

        //Caracteristico del algoritmo de Floyd (3 ciclos anidados)
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //Verifica si existe un camino más corto
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                        //guarda los caminos
                        caminos[i][j] = ciudades.get(k);
                    }
                }
            }
        }
    }

    //muestra ka ruta más corta
    public void mostrarRuta(String origen,String destino) {

        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        if (dist[i][j] == INF) {

            System.out.println("No existe ruta.");
            return;
        }

        System.out.println(
                "\nDistancia mínima: "
                        + dist[i][j]
                        + " KM"
        );

        System.out.print("Ruta: ");
        System.out.print(origen + " ");
        imprimirIntermedios(i, j);
        System.out.println(destino);
    }

    private void imprimirIntermedios(int i, int j) {
        //caso base de la recursion
        if (caminos[i][j].equals("")) {
            return;
        }

        String intermedio = caminos[i][j];
        int k = ciudades.indexOf(intermedio);
        imprimirIntermedios(i, k);
        System.out.print(intermedio + " ");
        imprimirIntermedios(k, j);
    }

    public int[][] getDistancias() {
        return dist;
    }

    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    public int getINF() {
        return INF;
    }
}