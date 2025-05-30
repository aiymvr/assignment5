import java.util.*;
public class BreadthFirstSearch<V> extends Search<V> {
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private Set<Vertex<V>> marked = new HashSet<>();

    public BreadthFirstSearch(UnweightedGraph<V> graph, V startKey) {
        super(graph, startKey);
        bfs(start);
    }

    private void bfs(Vertex<V> source) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        marked.add(source);
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
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