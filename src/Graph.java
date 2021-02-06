import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Graph {
    ArrayList<Node> nodesList;
    //id    index
    HashMap<String , Integer> index;
    //id    id
    HashMap<String, String> predecessors;
    MinHeap unvisited ;
    public Graph(int max){
        nodesList = new ArrayList<>();
        index = new HashMap<>();
        predecessors = new HashMap<>();
        unvisited = new MinHeap(max);
    }
    public void addEdge(String srcId , String dstId){
        Node src = nodesList.get(index.get(srcId));
        Node dst = nodesList.get(index.get(dstId));
        Edge edge = new Edge(src,dst);
        src.addEdge(edge);
        dst.addEdge(edge);
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
            node.distance = Double.MAX_EXPONENT;
//            for (Edge edge:node.adjacencyList) {
//                edge.setTrafficToZero();
//            }
        }
        unvisited.minHeapResetting();
        predecessors = new HashMap<>();
    }
    public Answer dijkstra(double time,String srcId , String dstId){
        //Node g = nodesList.get(index.get(20));
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
                if(v.id.equals(e.src.id)){
                    m = e.dst;
                }
                else {
                    m = e.src;
                }
                if(v.distance+e.weight<m.distance){
                    //System.out.println("*****m.id:"+m.id);
                    m.distance= v.distance + e.weight ;
                    m.ex = v;
                    //System.out.println("V:"+v.id+" dis: "+v.distance+"  ****************M:"+m.id+"***"+m.distance);
                    unvisited.update(m);
                    //System.out.println("V:"+v.id+" dis: "+v.distance+"  ****************M:"+m.id+"***"+m.distance);

                    predecessors.put(m.id,v.id);
                }
            }
        }
        //System.out.println("****************DST:"+dst.id+"***"+dst.ex+"*****"+dst.distance);
        ArrayList<Edge> ed = new ArrayList<>();
        String n = dstId;
        while (!n.equals(srcId)){
            String pre;
            if(!n.equals(srcId)){
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
        //System.out.println(dst.id+" "+dst.distance);
        return new Answer(ed,t*120,times,srcId,dstId);
    }

}


/*
0 10 35
2 15 35
7 15 35

10 15 20 30 35
2.8970562748485933

15 25 30 35
2.943330765278717

15 20 30 35
2.2970562748482863

********************************************************************************************

0 856 835
0.5 856 835
2 862 870
2.5 176 860
3 862 870

856 857 863 861 859 858 832 833 834 835
0.6049797500341486

856 848 847 846 845 844 843 842 841 840 839 533 838 837 836 835
0.6146820167275153

862 861 859 858 831 855 869 870
0.7822822964938757

176 851 852 853 854 855 831 858 859 860
0.7656242972082228

862 861 863 857 856 849 865 867 869 870
0.8637010159689265

*******************************************************************************************************************

0 38817 68030
1 76841 38814

38817 46160 38816 46158 43373 46154 46161 46150 38827 38813 38824 46148 68030
0.6509874311827875

76841 76840 46149 46151 46150 46161 46154 43373 46158 38816 38811 38814
0.7000746425314431

****************************************************************************************************************************
0 25969116 1730748375
0.5 25969116 1730748375
5 25969116 1730748335

25969116 854326015 4629550781 4629550783 6617020837 3069590529 4629551895 4629551893 4629551891 4629551889 4629550785 4629550786 1730748326 1730748327 1730748375
0.961654531617516

25969116 3883010011 3858822491 8204819605 8204819604 25940814 8204819600 1730748365 4618506891 4618506892 4618506896 4618506900 4618506905 5669323718 6263956474 1730748375
1.0993580659250066

25969116 3883010011 6617020816 4578666539 423733376 1923326718 4596783832 2021255734 4596783826 4596783824 26206696 1730748330 1730748335
0.5897247360582549

*/