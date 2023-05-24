package grafos;

import java.util.Iterator;

public class ArcoIterator<T> implements Iterator<Integer> {
    private Iterator<Arco<T>> iterator;

    public ArcoIterator(Iterator<Arco<T>> iterator){
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return iterator.next().getVerticeDestino();
    }
}
