import java.util.*;
public abstract class Search<V> {
    protected Vertex<V> start;

    public Search(WeightedGraph<V> graph, V startKey) {
        this.start = graph.getVertex(startKey);
        if (this.start == null) {
            throw new IllegalArgumentException("Vertex not found in graph: " + startKey);
        }
    }

    public Search(UnweightedGraph<V> graph, V startKey) {
        this.start = graph.getVertex(startKey);
        if (this.start == null) {
            throw new IllegalArgumentException("Vertex not found in graph: " + startKey);
        }
    }

    public Search(Vertex<V> start) {
        this.start = start;
    }

    public abstract boolean hasPathTo(V vertex);
    public abstract List<V> pathTo(V vertex);
}