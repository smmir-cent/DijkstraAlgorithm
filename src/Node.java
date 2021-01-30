import java.util.ArrayList;

public class Node {
    int id ;
    double x;
    double y;
    double distance;
    ArrayList<Edge> adjacencyList ;
    boolean visited;
    Node ex ;
    public Node(int id, double y, double x) {
        this.id = id;
        this.x = x;
        this.y = y;
        adjacencyList = new ArrayList<>();
        ex = null;
        distance = Integer.MAX_VALUE;
        visited = false;
    }

}
