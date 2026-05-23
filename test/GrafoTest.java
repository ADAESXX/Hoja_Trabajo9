import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GrafoTest {

    private Grafo grafo;
    private Floyd floyd;

    // Se ejecuta antes de cada prueba
    @Before
    public void setup() throws Exception {

        grafo = new Grafo();

        grafo.cargarArchivo("guategrafo.txt");

        floyd = new Floyd(grafo);

        floyd.ejecutarFloyd();
    }

    // prueba de carga

    @Test
    public void testCargaArchivo() {

        assertNotNull(grafo.getCiudades());

        assertTrue(
                grafo.getCiudades().size() > 0
        );
    }

    // Prueba para agrefar conexión

    @Test
    public void testAgregarConexion() {

        grafo.agregarConexion(
                "Mixco",
                "Antigua",
                50
        );

        int[][] matriz = grafo.getMatriz();

        assertEquals(
                50,
                matriz[1][2]
        );
    }

    // Prueba para eliminar conexión

    @Test
    public void testEliminarConexion() {

        grafo.eliminarConexion(
                "Mixco",
                "Antigua"
        );

        int[][] matriz = grafo.getMatriz();

        assertEquals(
                grafo.getINF(),
                matriz[1][2]
        );
    }

    // prueba para Floyd

    @Test
    public void testFloydDistancias() {

        int[][] dist = floyd.getDistancias();

        // Guatemala -> Antigua

        assertTrue(
                dist[0][2] > 0
        );
    }

    // prueba para el centro del grafo

    @Test
    public void testCentroGrafo() {

        String centro =
                CentroMasa.calcularCentro(
                        floyd
                );

        assertNotNull(centro);

        assertFalse(centro.isEmpty());
    }

    // prueba de caminos
    @Test
    public void testExisteRuta() {

        int[][] dist = floyd.getDistancias();

        int inf = floyd.getINF();

        assertNotEquals(
                inf,
                dist[0][3]
        );
    }

    //Prueba para la matriz
    @Test
    public void testMatrizNoVacia() {

        int[][] matriz = grafo.getMatriz();

        assertNotNull(matriz);

        assertTrue(
                matriz.length > 0
        );
    }
}