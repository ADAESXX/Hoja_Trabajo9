#Nombre: Abigail Escobar
#Fecha: 21/05/2026
#Descripcion: crear un programa que funcione con grafos, especificamente con el algoritmo de Floyd
#             el propósito es encontar la ruta más corta de un lugar a otro.

import networkx as nx

#Se carga el grafo desde guategrafo.txt
def cargar_grafo(archivo):
    #networkX es una biblioteca de Python para crear , manipular y analizar redes complejas y estructuras de datos
    grafo = nx.DiGraph()
    #Se abre el archivo para leerlo
    with open(archivo, "r") as file:

        for linea in file:
            origen, destino, distancia = linea.split()
            # se crean las aristas con su label
            grafo.add_edge(
                origen,
                destino,
                weight=int(distancia)
            )

    return grafo


#Mostrar la ruta más corta

def ruta_mas_corta(grafo, origen, destino):

    try:
        distancia = nx.floyd_warshall(grafo)
        # encuentra el camino más corto
        ruta = nx.shortest_path(
            grafo,
            source=origen,
            target=destino,
            weight='weight'
        )

        print(f"\nDistancia mínima: {distancia[origen][destino]} KM")
        print("Ruta:", " → ".join(ruta))
    
    except:
        print("No existe ruta.")


#Calcular el centro del grafo para representar a la ubicación optima para los almacenes de medicina
def centro_grafo(grafo):
    #obtener las distancias y extricidad
    distancias = dict(nx.floyd_warshall(grafo))
    excentricidades = {}
    for nodo in grafo.nodes():
        max_dist = max(
            d for d in distancias[nodo].values()
            if d != float('inf')
        )
        excentricidades[nodo] = max_dist

    centro = min(
        excentricidades,
        key=excentricidades.get
    )

    return centro


#Mostrar la matriz
def mostrar_matriz(grafo):

    dist = dict(nx.floyd_warshall(grafo))

    print("\nMatriz de distancias:\n")

    ciudades = list(grafo.nodes())

    print(f"{'':15}", end='')

    for ciudad in ciudades:
        print(f"{ciudad:15}", end='')
    print()

    for i in ciudades:
        print(f"{i:15}", end='')
        for j in ciudades:
            valor = dist[i][j]
            if valor == float('inf'):
                print(f"{'INF':15}", end='')
            else:
                print(f"{int(valor):15}", end='')

        print()


#Menu

def main():

    grafo = cargar_grafo("guategrafo.txt")

    while True:

        print("\n===== MENÚ =====")
        print("1. Ruta más corta")
        print("2. Centro del grafo")
        print("3. Eliminar conexión")
        print("4. Agregar conexión")
        print("5. Mostrar matriz")
        print("6. Salir")

        opcion = input("Seleccione opción: ")

        match opcion:

            case "1":

                origen = input("Ciudad origen: ")
                destino = input("Ciudad destino: ")

                ruta_mas_corta(grafo, origen, destino)

            case "2":

                print("\nCentro del grafo:", centro_grafo(grafo))

            case "3":

                origen = input("Origen: ")
                destino = input("Destino: ")

                if grafo.has_edge(origen, destino):

                    grafo.remove_edge(origen, destino)
                    print("Conexión eliminada.")

                else:
                    print("La conexión no existe.")

            case "4":

                origen = input("Origen: ")
                destino = input("Destino: ")
                distancia = int(input("Distancia: "))

                grafo.add_edge(
                    origen,
                    destino,
                    weight=distancia
                )

                print("Conexión agregada.")

            case "5":

                mostrar_matriz(grafo)

            case "6":

                print("Programa finalizado.")
                break

            case _:

                print("Opción inválida.")
main()