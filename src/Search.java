import java.util.*;

public abstract class Search<V> {
    protected Vertex<V> start;

    public Search(Vertex<V> start) {
        this.start = start;
    }

    public abstract boolean hasPathTo(Vertex<V> vertex);
    public abstract List<Vertex<V>> pathTo(Vertex<V> vertex);
}