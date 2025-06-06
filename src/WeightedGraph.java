import java.util.*;

public class WeightedGraph<V> {
    private Set<Vertex<V>> vertices = new HashSet<>();
    private final boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public Vertex<V> getVertex(V data) {
        for (Vertex<V> v : vertices) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }

    public void addEdge(V source, V destination, double weight) {
        Vertex<V> src = getOrCreateVertex(source);
        Vertex<V> dest = getOrCreateVertex(destination);

        src.addAdjacentVertex(dest, weight);
        if (!directed) {
            dest.addAdjacentVertex(src, weight);
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

    public Set<Vertex<V>> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }
}