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
    public Answer dijkstra(double time,int srcId , int dstId){
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
                if(v.id == e.src.id){
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

44 46
831 35.9853606 50.731763
832 35.984751 50.7331003
833 35.9845528 50.7333641
834 35.9843547 50.7336089
835 35.9841413 50.7337596
836 35.9839279 50.7338161
837 35.9835926 50.733835
838 35.9832268 50.7337973
839 35.9829677 50.7336089
840 35.9828231 50.7333384
841 35.9826933 50.7328932
842 35.9825116 50.7325459
843 35.9821903 50.7322151
844 35.9818703 50.7318383
845 35.9817331 50.7315935
846 35.9817026 50.7312921
847 35.9817636 50.7309719
848 35.9818855 50.7307835
849 35.9834707 50.729465
850 35.9840346 50.7284102
851 35.9858462 50.7297286
852 35.985955 50.7299359
853 35.9860008 50.7302938
854 35.985955 50.7304821
855 35.9856936 50.7310455
856 35.9823624 50.7303869
857 35.9826019 50.7307458
858 35.9850517 50.7324406
859 35.9840965 50.7317798
860 35.9836231 50.732799
861 35.9834467 50.7313303
862 35.9829829 50.7323092
863 35.982846 50.7309147
864 35.982358 50.731876
865 35.9838395 50.7297272
866 35.983364 50.7307458
867 35.9844671 50.7301735
868 35.9839432 50.7312356
869 35.9850978 50.7306219
870 35.9845833 50.7317253
176 35.9855929 50.7295014
178 35.9849919 50.7290806
180 35.9843691 50.7286445
533 35.9830907 50.7337353
176 851
851 852
852 853
853 854
854 855
855 831
831 858
858 832
832 833
833 834
834 835
835 836
836 837
837 838
838 533
533 839
839 840
840 841
841 842
842 843
843 844
844 845
845 846
846 847
847 848
848 856
856 849
849 850
849 865
865 867
867 869
869 855
856 857
857 863
863 861
861 859
859 858
859 860
861 862
863 864
865 866
867 868
869 870
850 180
180 178
178 176

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