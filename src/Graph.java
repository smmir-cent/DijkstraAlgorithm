import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    ArrayList<Node> nodesList;
    //id    index
    HashMap<Integer , Integer> index;
    HashMap<Integer, Integer> predecessors;
    public Graph(){
        nodesList = new ArrayList<>();
        index = new HashMap<>();
        predecessors = new HashMap<>();
    }
    public void addEdge(int srcId , int dstId){
        Node src = nodesList.get(index.get(srcId));
        Node dst = nodesList.get(index.get(dstId));
        Edge edge = new Edge(src,dst);
        src.adjacencyList.add(edge);
        dst.adjacencyList.add(edge);
    }
    public void addNode(Node node){
        nodesList.add(node);
        index.put(node.id, nodesList.size()-1);
    }
}