import java.util.ArrayList;

public class Answer {
    ArrayList<Edge> edges;
    ArrayList<Double> times;
    double totalTime;

    public Answer(ArrayList<Edge> edges, double totalTime , ArrayList<Double> times) {
        this.edges = edges;
        this.totalTime = totalTime;
        this.times=times;
        for(Edge edge:edges){
            System.out.println(edge.src.id+"->"+edge.dst.id+"  **** len: "+edge.length + "**** weight: "+edge.weight);
        }
        for(Double i:times){
            System.out.print(i+" ");
        }

        System.out.println("********Total time"+totalTime);



    }
    public void trafficConfig(int time){
        int i =edges.size()-1;
        while (i!=0){
            if(time>times.get(i)){
                break;
            }
            edges.get(i).incrementTraffic();
            i--;
        }
        for(Edge edge:edges){
            System.out.println(edge.src.id+"->"+edge.dst.id+"  **** len: "+edge.length + "**** weight: "+edge.weight);
        }


    }
    public void trafficToZero(){
        for (Edge edge:edges) {
            edge.setTrafficToZero();
        }
    }





}
