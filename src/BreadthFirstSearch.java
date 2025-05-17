import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private Set<Vertex<V>> marked = new HashSet<>();

    public BreadthFirstSearch(Vertex<V> start) {
        super(start);
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
    public boolean hasPathTo(Vertex<V> vertex) {
        return marked.contains(vertex);
    }

    @Override
    public List<Vertex<V>> pathTo(Vertex<V> vertex) {
        if (!hasPathTo(vertex)) return null;
        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> x = vertex; x != start; x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, start);
        return path;
    }
}