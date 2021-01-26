import java.util.ArrayList;

public class Node {
    int id ;
    double x;
    double y;
    int distance = Integer.MAX_VALUE;
    ArrayList<Edge> adjacencyList ;
    boolean visited;
    public Node(int id, double y, double x) {
        this.id = id;
        this.x = x;
        this.y = y;
        adjacencyList = new ArrayList<>();
    }

}
