public class Edge {
    Node src;
    Node dst;
    double length;
    private int traffic;
    double weight;

    public Edge(Node src, Node dst) {
        this.src = src;
        this.dst = dst;
        traffic= 0;
        length = Math.sqrt(Math.pow(src.x-dst.x,2) + Math.pow(src.y-dst.y,2));
        weight = length *(1+0.3*traffic);
    }
    public void incrementTraffic(){
        traffic++;
        weight = length *(1+0.3*traffic);
    }

    public void decrementTraffic(){
        traffic--;
        weight = length *(1+0.3*traffic);
    }
    public void setTrafficToZero(){
        traffic=0;
        weight = length *(1+0.3*traffic);
    }



}
