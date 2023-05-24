package servicios;

import grafos.Arco;
import grafos.Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;
    private List<List<Integer>> caminos;
    private List<Arco<?>> arcosVisitados;

    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim){
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
        this.caminos = new ArrayList<>();
        this.arcosVisitados = new ArrayList<>();
    }

    public List<List<Integer>> caminos(){
        List<Integer> caminoActual = new ArrayList<>();
        if (this.grafo != null){
            if (this.grafo.contieneVertice(this.origen)){
                caminoActual.add(this.origen);
                buscarCaminos(caminoActual, this.origen);
                revisarCaminos();
            }
        }
        return new ArrayList<>(this.caminos);
    }

    private void revisarCaminos(){
        if (this.origen == this.destino){
            List<Integer> caminoActual = new ArrayList<>();
            Iterator<Integer> verticesAdyacentes = this.grafo.obtenerAdyacentes(this.origen);
            int verticeAdyacente = verticesAdyacentes.next();
            caminoActual.add(this.origen);
            caminoActual.add(verticeAdyacente);
            buscarCaminos(caminoActual, verticeAdyacente);
        }
    }

    private void buscarCaminos(List<Integer> caminoActual, int verticeActual){
        if (verticeActual == this.destino){
            ArrayList<Integer> caminosAux = new ArrayList<>(caminoActual);
            this.caminos.add(caminosAux);
            return;
        }
        if(caminoActual.size() > this.lim){
            return;
        }

        Iterator<Integer> verticesAdyacentes = this.grafo.obtenerAdyacentes(verticeActual);
        while (verticesAdyacentes.hasNext()){
            int verticeAdyacente = verticesAdyacentes.next();
            Arco<?> arco = this.grafo.obtenerArco(verticeActual, verticeAdyacente);
            if(!this.arcosVisitados.contains(arco)){
                this.arcosVisitados.add(arco);
                caminoActual.add(verticeAdyacente);
                buscarCaminos(caminoActual, verticeAdyacente);
                caminoActual.remove(caminoActual.size() - 1);
                this.arcosVisitados.remove(this.arcosVisitados.size() - 1);
            }
        }
    }
}
