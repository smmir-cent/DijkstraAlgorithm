import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Node {
    String id ;
    double x;
    double y;
    double distance;
    private ArrayList<Edge> adjacencyList ;
    boolean visited;
    Node ex ;
    //adjID   index
    HashMap<String , Integer> index ;
    public Node(String id, double y, double x) {
        this.id = id;
        this.x = x;
        this.y = y;
        adjacencyList = new ArrayList<>();
        ex = null;
        distance = Double.MAX_EXPONENT;
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
    public Edge adjEdge(String id){
        return adjacencyList.get(index.get(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
