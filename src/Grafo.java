/**
 * Nombre del archivo: Grafo.java
 * Autor: Abigail Escobar
 * Fecha: 22/5/2026
 * Descripción: hacer el grafo :)
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {
    //lista de coudades para los nodos del grafo
    private ArrayList<String> ciudades;
    //información relevanete para aristas
    private HashMap<String, Integer> indices;
    //matriz de adyacencia
    private int[][] matriz;

    private final int INF = 999999;

    //Constructor
    public Grafo() {

        ciudades = new ArrayList<>();
        indices = new HashMap<>();
    }

    public void cargarArchivo(String archivo) throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader(archivo)
        );

        String linea;
        //guarda las conexiones de manera temporal
        ArrayList<String[]> conexiones = new ArrayList<>();

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(" ");
            conexiones.add(partes);

            if (!indices.containsKey(partes[0])) {
                indices.put(partes[0], ciudades.size());
                ciudades.add(partes[0]);
            }

            if (!indices.containsKey(partes[1])) {
                indices.put(partes[1], ciudades.size());
                ciudades.add(partes[1]);
            }
        }

        br.close();

        int n = ciudades.size();
        //Inicializa la matriz
        matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Distancia a sí mismo es cero y si no tiene conexión es infinito
                if (i == j)
                    matriz[i][j] = 0;

                else
                    matriz[i][j] = INF;
            }
        }
        //Llenar la matriz con las conexiones
        for (String[] conexion : conexiones) {

            String origen = conexion[0];
            String destino = conexion[1];
            int distancia = Integer.parseInt(conexion[2]);

            int i = indices.get(origen);
            int j = indices.get(destino);

            matriz[i][j] = distancia;
        }
    }

    public void agregarConexion(String origen,String destino,int distancia) {

        matriz[indices.get(origen)][indices.get(destino)] = distancia;
    }

    public void eliminarConexion(String origen,String destino) {

        matriz[indices.get(origen)][indices.get(destino)] = INF;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    public int getINF() {
        return INF;
    }
}