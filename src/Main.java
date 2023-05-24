import grafos.GrafoDirigido;
import servicios.ServicioBFS;
import servicios.ServicioCaminos;
import servicios.ServicioDFS;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        GrafoDirigido<String> grafo = new GrafoDirigido<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);

        grafo.agregarArco(1, 3, "a");
        grafo.agregarArco(4, 1, "a");
        grafo.agregarArco(1, 2, "a");
        grafo.agregarArco(3, 4, "a");
        grafo.agregarArco(3, 1, "a");

//        ServicioDFS dfs = new ServicioDFS(grafo);
//        ServicioBFS bfs = new ServicioBFS(grafo);
//        System.out.println(dfs.dfsForest());
//        System.out.println(bfs.bfsForest());
        ServicioCaminos caminos = new ServicioCaminos(grafo, 1, 1, 3);
        for (List<Integer> camino : caminos.caminos()){
            System.out.println(camino);
        }
    }
}