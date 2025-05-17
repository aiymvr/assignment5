import java.util.*;

public class Main {
    public static void main(String[] args) {

        Vertex<String> pointA = new Vertex<>("A");
        Vertex<String> pointB = new Vertex<>("B");
        Vertex<String> pointC = new Vertex<>("C");
        Vertex<String> pointD = new Vertex<>("D");


        WeightedGraph<String> cityMap = new WeightedGraph<>();
        cityMap.addEdge(pointA, pointB, 1);
        cityMap.addEdge(pointA, pointC, 4);
        cityMap.addEdge(pointB, pointD, 2);
        cityMap.addEdge(pointC, pointD, 1);


        System.out.println("Exploring all paths from A to D (BFS):");
        printPath(new BreadthFirstSearch<>(pointA).pathTo(pointD));


        System.out.println("\nFinding shortest path from A to D (Dijkstra):");
        printPath(new DijkstraSearch<>(pointA).pathTo(pointD));

    }


    private static void printPath(List<Vertex<String>> path) {
        if (path == null) {
            System.out.println("No path exists!");
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getData());
            if (i < path.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println();
    }

}