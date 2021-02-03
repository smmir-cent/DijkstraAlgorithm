import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Graph {
    ArrayList<Node> nodesList;
    //id    index
    HashMap<Integer , Integer> index;
    //id    id
    HashMap<Integer, Integer> predecessors;
    MinHeap unvisited ;
    public Graph(int max){
        nodesList = new ArrayList<>();
        index = new HashMap<>();
        predecessors = new HashMap<>();
        unvisited = new MinHeap(max);
    }
    public void addEdge(int srcId , int dstId){
        Node src = nodesList.get(index.get(srcId));
        Node dst = nodesList.get(index.get(dstId));
        Edge edge = new Edge(src,dst);
        src.addEdge(edge);
    }
    public void addNode(Node node){
        nodesList.add(node);
        index.put(node.id, nodesList.size()-1);
        unvisited.insert(node);
    }
    public void graphResetting(){
        for (Node node:nodesList) {
            node.visited = false;
            node.ex = null;
            node.distance = Double.MAX_VALUE;
//            for (Edge edge:node.adjacencyList) {
//                edge.setTrafficToZero();
//            }
        }
    }
    public Answer dijkstra(int srcId , int dstId){
        graphResetting();
        Node src = nodesList.get(index.get(srcId));
        src.distance=0;
        unvisited.update(src);
        Node dst = nodesList.get(index.get(dstId));
        while (!dst.visited){
            Node v =unvisited.deleteMin() ;//next explored node
            v.visited = true;
            for (Edge e :v.getAdjacencyList()) {
                Node m;
                if(v.id == e.src.id){
                    m = e.dst;
                }
                else {
                    m = e.src;
                }
                //m.distance=Math.min(v.distance+e.weight,m.distance);
                if(v.distance+e.weight<m.distance){
                    m.distance= v.distance + e.weight ;
                    m.ex = v;
                    unvisited.update(m);
                    predecessors.put(m.id,v.id);
                }
            }
        }
        ArrayList<Integer> ed = new ArrayList<>();
        int n = dstId;
        while (n!=srcId){
            ed.add(n);
            n=predecessors.get(n);
        }
        ed.add(srcId);
        Collections.reverse(ed);
        return new Answer(ed,dst.distance*120);

    }

}



