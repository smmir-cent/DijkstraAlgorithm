import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    int id ;
    double x;
    double y;
    double distance;
    private ArrayList<Edge> adjacencyList ;
    boolean visited;
    Node ex ;
    //adj   id
    HashMap<Integer , Integer> index ;
    public Node(int id, double y, double x) {
        this.id = id;
        this.x = x;
        this.y = y;
        adjacencyList = new ArrayList<>();
        ex = null;
        distance = Integer.MAX_VALUE;
        visited = false;
        index = new HashMap<>();
    }
    public void addEdge(Edge edge){
        if(edge.dst.id == id){
            int temp = adjacencyList.size();
            adjacencyList.add(edge);
            index.put(edge.src.id,temp);
        }
        else if(edge.src.id == id){
            int temp = adjacencyList.size();
            adjacencyList.add(edge);
            index.put(edge.dst.id,temp);
        }
    }

    public ArrayList<Edge> getAdjacencyList() {
        return adjacencyList;
    }
}
