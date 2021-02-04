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
        unvisited.minHeapResetting();
    }
    public Answer dijkstra(int time,int srcId , int dstId){
        Node g = nodesList.get(index.get(20));
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
                if(v.distance+e.weight<m.distance){
                    System.out.println("*****m.id:"+m.id);
                    m.distance= v.distance + e.weight ;
                    m.ex = v;
                    System.out.println("V:"+v.id+" dis: "+v.distance+"  ****************M:"+m.id+"***"+m.distance);
                    unvisited.update(m);
                    System.out.println("V:"+v.id+" dis: "+v.distance+"  ****************M:"+m.id+"***"+m.distance);

                    predecessors.put(m.id,v.id);
                }
            }
        }
        System.out.println("****************DST:"+dst.id+"***"+dst.ex+"*****"+dst.distance);
        ArrayList<Edge> ed = new ArrayList<>();
        int n = dstId;
        while (n!=srcId){
            int pre;
            if(n!=srcId){
                pre =predecessors.get(n);
                ed.add(nodesList.get(index.get(pre)).adjEdge(n));

            }
            else{
                pre = srcId;
                //ed.add(nodesList.get(index.get(n)).adjEdge(pre));

            }
            n=pre;
        }
        Collections.reverse(ed);
        ArrayList<Double> times = new ArrayList<>();
        double t = 0;
        for(Edge edge: ed){
            t+=edge.weight;
            times.add(time+t*120);
        }
        System.out.println(dst.id+" "+dst.distance);
        return new Answer(ed,t*120,times);
    }

}


/*
        6 6
        10 35.9853606 50.731763
        15 35.9903606 50.731763
        20 35.9953606 50.736763
        25 35.9953606 50.724263
        30 36.0003606 50.731763
        35 36.0053606 50.731763
        10 15
        15 20
        15 25
        20 30
        25 30
        30 35

*/