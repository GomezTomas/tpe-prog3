package servicios;

import grafos.Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

    private Grafo<?> grafo;
    private HashMap<Integer, String> colorVertice;
//    private int tiempo;
    private ArrayList<Integer> bfsResult;
    public ServicioDFS(Grafo<?> grafo){
        this.grafo = grafo;
        this.bfsResult = new ArrayList<>();
        this.colorVertice = new HashMap<>();
    }

    public List<Integer> dfsForest(){
        Iterator<Integer> vertices = this.grafo.obtenerVertices();
        while (vertices.hasNext()){
            int vertice = vertices.next();
            this.colorVertice.put(vertice, "BLANCO");
        }
        vertices = this.grafo.obtenerVertices();
        while (vertices.hasNext()){
            int vertice = vertices.next();
            if (this.colorVertice.get(vertice).equals("BLANCO")){
                dfsVisit(vertice);
            }
        }
        return new ArrayList<>(this.bfsResult);
    }

    private void dfsVisit(int verticeId){
        this.colorVertice.put(verticeId, "AMARILLO");
        this.bfsResult.add(verticeId);
        Iterator<Integer> verticesAdyacentes = this.grafo.obtenerAdyacentes(verticeId);
        while (verticesAdyacentes.hasNext()){
            int verticeAdyacente = verticesAdyacentes.next();
            if (this.colorVertice.get(verticeAdyacente).equals("BLANCO")){
                dfsVisit(verticeAdyacente);
            }
        }
        colorVertice.put(verticeId, "NEGRO");
    }
}
