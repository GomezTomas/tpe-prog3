package grafos;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    private Map<Integer, List<Arco<T>>> vertices;

    public GrafoDirigido(){
        this.vertices = new HashMap<>();
    }

    /**
     * Complejidad: O(1) debido a que solo realiza
     * acceso a memoria para agregar el valor,
     * entonces es constante.
     */
    @Override
    public void agregarVertice(int verticeId) {
        if(!contieneVertice(verticeId)){
            this.vertices.put(verticeId, new LinkedList<>());
        }
    }

    /**
     * Complejidad: O(V*A) donde V es la cantidad de vertices y
     * A es la cantidad de Arcos. En el peor de los casos
     * recorre todos los vertices y luego cada arco del vertice
     * para verificar si debe borrar o no el arco.
     */
    @Override
    public void borrarVertice(int verticeId) {
        this.vertices.remove(verticeId);
        Iterator<Integer> iteradorVertices = obtenerVertices();
        while(iteradorVertices.hasNext()){
            int aux = iteradorVertices.next();
            borrarArco(aux, verticeId);
        }
    }

    /**
     * Complejidad: O(A) donde A es la cantidad de arcos del vertice 1,
     * ya que es la complejidad del metodo existeArco, que recorre todos los arcos
     * del vertice 1 hasta encontrar como destino el vertice 2, y en el peor caso
     * no lo encuentra.
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(contieneVertice(verticeId1) && contieneVertice(verticeId2) && !existeArco(verticeId1, verticeId2)){
            Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
            this.vertices.get(verticeId1).add(arco);
        }
    }

    /**
     * Complejidad: O(A) donde A es la cantidad de arcos.
     * en el peor de los casos debe recorrer toda la lista de arcos
     * para encontrar el ultimo y borrarlo.
     */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        boolean borrado = false;
        Iterator<Arco<T>> iteradorArcos = obtenerArcos(verticeId1);
        while (!borrado && iteradorArcos.hasNext()){
            Arco<T> arco = iteradorArcos.next();
            if(arco.getVerticeDestino() == verticeId2){
                iteradorArcos.remove();
                borrado = true;
            }
        }
    }

    /**
     * Complejidad: O(1) ya que el metodo containsKey()
     * tiene una complejidad constante.
     */
    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    /**
     * Complejidad: O(A) donde A es la cantidad de arcos que posee
     * el vertice 1, debido a que recorre todos los arcos
     * del vertice 1 hasta encontrar como destino el vertice 2, y en el peor caso
     * no lo encuentra.
     */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if(contieneVertice(verticeId1) && contieneVertice(verticeId2)){
            return obtenerArco(verticeId1, verticeId2) != null;
        }
        return false;
    }

    /**
     * Complejidad: O(A) donde A es la cantidad de arcos
     * asociados al vertice 1. En el peor caso, recorre todos
     * los arcos del vertice hasta encontrar en el ultimo lugar
     * el vertice de destino o no lo encuentra.
     */
    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        Iterator<Arco<T>> iteradorArcos = obtenerArcos(verticeId1);
        while(iteradorArcos.hasNext()){
            Arco<T> arco = iteradorArcos.next();
            if(arco.getVerticeDestino() == verticeId2){
                return arco;
            }
        }
        return null;
    }

    /**
     * Complejidad: O(1), ya que el metodo size()
     * accede a un valor en memoria.
     */
    public int cantidadVertices(){
        return this.vertices.size();
    }

    /**
     * Complejidad: O(V) donde V es es la cantidad de vertices, debido a que debe
     * se deben recorrer todos los vertices para consultar la cantidad
     * de elementos que tiene cada lista de arcos.
     */
    @Override
    public int cantidadArcos() {
        int cantidad = 0;
        for (int verticeId : this.vertices.keySet()) {
            cantidad = cantidad + this.vertices.get(verticeId).size();
        }
        return cantidad;
    }

    /**
     * Complejidad: O(1) ya que el metodo keySet() y el metodo
     * iterator() tienen una complejidad constante.
     */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return this.vertices.keySet().iterator();
    }

    /**
     * Complejidad: O(1) porque es la misma complejidad que el metodo
     * obtenerArcos().
     */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        Iterator<Arco<T>> iteradorArcos = obtenerArcos(verticeId);
        return new ArcoIterator<T>(iteradorArcos);
    }

    /**
     * Complejidad: O(V*A) donde V es la cantidad de vertices
     * y A la cantidad de arcos, debido a que se recorren todos los
     * vertices, y por cada vertice se le recorren todos los arcos.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        Iterator<Integer> iteradorVertices = obtenerVertices();
        ArrayList<Arco<T>> arcos = new ArrayList<>();
        Iterator<Arco<T>> iteradorArcos = null;
        while (iteradorVertices.hasNext()){
            iteradorArcos = obtenerArcos(iteradorVertices.next());
            while (iteradorArcos.hasNext()){
                arcos.add(iteradorArcos.next());
            }
        }
        return arcos.iterator();
    }

    /**
     * Complejidad: O(1) ya que el metodo get() y el metodo
     * iterator() tienen complejidad constante.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        return this.vertices.get(verticeId).iterator();
    }
}
