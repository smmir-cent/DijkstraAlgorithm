import java.util.ArrayList;

public class Answer {
    ArrayList<Edge> edges;
    ArrayList<Double> times;
    double totalTime;
    String srcID;
    String dstID;
    public Answer(ArrayList<Edge> edges, double totalTime , ArrayList<Double> times,String srcID,String dstID) {
        this.edges = edges;
        this.totalTime = totalTime;
        this.times=times;
        this.srcID=srcID;
        this.dstID=dstID;
        String temp = edges.get(0).src.id.equals(srcID)? edges.get(0).dst.id :edges.get(0).src.id ;
        System.out.print(srcID+" ");
        for(int i =0;i<edges.size();i++){
            System.out.print(temp+" ");
            if(temp.equals(edges.get(i+1).src.id)){
                temp = edges.get(i+1).dst.id;
            }
            else if(temp.equals(edges.get(i+1).dst.id)) {
                temp = edges.get(i+1).src.id;
            }
            if(temp.equals(dstID)){
                System.out.print(temp+" ");
                break;
            }

        }
        System.out.println();
//        for(Double i:times){
//            System.out.print(i+" ");
//        }

        System.out.println("Total time: "+totalTime);
        System.out.println("*******************************************************************");


    }
    public void trafficConfig(double time){
        int i =edges.size()-1;
        while (i!=0){
            int prev =i-1;
            if(prev == -1)
                break;
            if(time>times.get(i)){
                break;
            }
            else if(time<times.get(i) && time>times.get(prev)){
                edges.get(i).incrementTraffic();
                break;
            }
            i--;
        }
    }
    public void trafficToZero(){
        for (Edge edge:edges) {
            edge.setTrafficToZero();
        }
    }





}
