/**
 * Nombre del archivo: CentroMasa.java
 * Autor: Abigail Escobar
 * Fecha: 22/5/2026
 * Descripción: calcular el centro del grafo, el cual ayudará a obtener la mejor posición para colocar los almacenamientos de medicamentos
 */
import java.util.ArrayList;

public class CentroMasa {

    public static String calcularCentro(Floyd floyd) {
        //obtener la matriz de adyacencia de las distancias
        int[][] dist = floyd.getDistancias();
        ArrayList<String> ciudades = floyd.getCiudades();
        int INF = floyd.getINF();
        int mejor = INF;
        int centro = -1;

        for (int i = 0; i < dist.length; i++) {
            int excentricidad = 0;

            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] > excentricidad && dist[i][j] != INF) {
                    excentricidad = dist[i][j];
                }
            }

            if (excentricidad < mejor) {
                mejor = excentricidad;
                centro = i;
            }
        }

        return ciudades.get(centro);
    }
}