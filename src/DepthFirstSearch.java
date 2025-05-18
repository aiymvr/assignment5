import java.util.*;

public class DepthFirstSearch<V> extends Search<V> {
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private Set<Vertex<V>> marked = new HashSet<>();

    public DepthFirstSearch(UnweightedGraph<V> graph, V startKey) {
        super(graph, startKey);
        dfs(start);
    }

    private void dfs(Vertex<V> current) {
        marked.add(current);
        for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
            if (!marked.contains(neighbor)) {
                edgeTo.put(neighbor, current);
                dfs(neighbor);
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return marked.contains(new Vertex<>(vertex));
    }

    @Override
    public List<V> pathTo(V vertexKey) {
        Vertex<V> vertex = new Vertex<>(vertexKey);
        if (!hasPathTo(vertexKey)) return null;
        List<V> path = new ArrayList<>();
        for (Vertex<V> x = vertex; x != start; x = edgeTo.get(x)) {
            path.add(0, x.getData());
        }
        path.add(0, start.getData());
        return path;
    }
}