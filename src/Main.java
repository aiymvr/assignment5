
public class Main {
    public static void main(String[] args) {
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");

        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addEdge(a, b, 1);
        graph.addEdge(a, c, 4);
        graph.addEdge(b, d, 2);
        graph.addEdge(c, d, 1);

        System.out.println("BFS path from A to D:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(a);
        for (Vertex<String> v : bfs.pathTo(d)) {
            System.out.print(v.getData() + " ");
        }

        System.out.println("\nDijkstra path from A to D:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(a);
        for (Vertex<String> v : dijkstra.pathTo(d)) {
            System.out.print(v.getData() + " ");
        }
    }
}