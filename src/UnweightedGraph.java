import java.util.*;

public class UnweightedGraph<V> {
    private Set<Vertex<V>> vertices = new HashSet<>();
    private final boolean directed;

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public Vertex<V> getVertex(V data) {
        return vertices.stream()
                .filter(v -> v.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    public void addEdge(V source, V destination) {
        Vertex<V> src = getOrCreateVertex(source);
        Vertex<V> dest = getOrCreateVertex(destination);
        src.addAdjacentVertex(dest, 1.0);
        if (!directed) {
            dest.addAdjacentVertex(src, 1.0);
        }
    }

    private Vertex<V> getOrCreateVertex(V data) {
        Vertex<V> v = getVertex(data);
        if (v == null) {
            v = new Vertex<>(data);
            vertices.add(v);
        }
        return v;
    }
}