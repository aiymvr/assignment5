import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Map<V, Double> distances;
    private final Map<V, Vertex<V>> previousVertices;
    private final PriorityQueue<VertexDistance<V>> priorityQueue;

    public DijkstraSearch(WeightedGraph<V> graph, V startKey) {
        super(graph, startKey);
        this.distances = new HashMap<>();
        this.previousVertices = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(VertexDistance::getDistance));


        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex.getData(), Double.POSITIVE_INFINITY);
        }
        distances.put(startKey, 0.0);


        priorityQueue.add(new VertexDistance<>(start, 0.0));

        dijkstra(graph);
    }

    private void dijkstra(WeightedGraph<V> graph) {
        while (!priorityQueue.isEmpty()) {
            VertexDistance<V> current = priorityQueue.poll();
            V currentData = current.getVertex().getData();


            if (current.getDistance() > distances.get(currentData)) {
                continue;
            }


            for (Map.Entry<Vertex<V>, Double> neighborEntry :
                    current.getVertex().getAdjacentVertices().entrySet()) {

                Vertex<V> neighbor = neighborEntry.getKey();
                V neighborData = neighbor.getData();
                double edgeWeight = neighborEntry.getValue();
                double newDist = distances.get(currentData) + edgeWeight;

                // Если нашли более короткий путь
                if (newDist < distances.get(neighborData)) {
                    distances.put(neighborData, newDist);
                    previousVertices.put(neighborData, current.getVertex());
                    priorityQueue.add(new VertexDistance<>(neighbor, newDist));
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return distances.getOrDefault(vertex, Double.POSITIVE_INFINITY) < Double.POSITIVE_INFINITY;
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) {
            return Collections.emptyList();
        }

        LinkedList<V> path = new LinkedList<>();
        V current = vertex;

        // Восстанавливаем путь от конца к началу
        while (current != null && !current.equals(start.getData())) {
            path.addFirst(current);
            Vertex<V> prevVertex = previousVertices.get(current);
            current = (prevVertex != null) ? prevVertex.getData() : null;
        }
        path.addFirst(start.getData());

        return path;
    }


    private static class VertexDistance<V> {
        private final Vertex<V> vertex;
        private final double distance;

        public VertexDistance(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex<V> getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }
}