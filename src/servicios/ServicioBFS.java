package servicios;

import grafos.Grafo;

import java.util.*;

public class ServicioBFS {
    private Grafo<?> grafo;
    private Queue<Integer> fila;
    private HashMap<Integer, Boolean> verticeVisitado;
    private ArrayList<Integer> resultadoBFS;


    public ServicioBFS(Grafo<?> grafo){
        this.grafo = grafo;
        this.fila = new LinkedList<>();
        this.verticeVisitado = new HashMap<>();
        this.resultadoBFS = new ArrayList<>();
    }

    public List<Integer> bfsForest(){
        this.fila.clear();
        Iterator<Integer> vertices = this.grafo.obtenerVertices();
        while (vertices.hasNext()){
            int vertice = vertices.next();
            this.verticeVisitado.put(vertice, false);
        }

        vertices = this.grafo.obtenerVertices();
        while (vertices.hasNext()){
            int vertice = vertices.next();
            if (!this.verticeVisitado.get(vertice)){
                bfsForest(vertice);
            }
        }
        return new ArrayList<>(this.resultadoBFS);
    }

    private void bfsForest(int verticeId){
        this.verticeVisitado.put(verticeId, true);
        this.resultadoBFS.add(verticeId);
        this.fila.add(verticeId);
        while (!this.fila.isEmpty()){
            int vertice = this.fila.remove();
            Iterator<Integer> verticesAdyacentes = this.grafo.obtenerAdyacentes(vertice);
            while (verticesAdyacentes.hasNext()){
                int verticeAdyacente = verticesAdyacentes.next();
                if (!this.verticeVisitado.get(verticeAdyacente)){
                    this.verticeVisitado.put(verticeAdyacente, true);
                    this.resultadoBFS.add(verticeAdyacente);
                    this.fila.add(verticeAdyacente);
                }
            }
        }
    }
}
