import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> distTo = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private PriorityQueue<VertexDist<V>> pq = new PriorityQueue<>();

    private class VertexDist<T> implements Comparable<VertexDist<T>> {
        Vertex<T> vertex;
        double distance;

        VertexDist(Vertex<T> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int compareTo(VertexDist<T> other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public DijkstraSearch(Vertex<V> start) {
        super(start);
        distTo.put(start, 0.0);
        pq.add(new VertexDist<>(start, 0.0));

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll().vertex;
            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distTo.getOrDefault(current, Double.POSITIVE_INFINITY) + weight;
                if (newDist < distTo.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.add(new VertexDist<>(neighbor, newDist));
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex<V> vertex) {
        return distTo.containsKey(vertex);
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