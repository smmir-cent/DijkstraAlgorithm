import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        File file = new File("map.txt");
        int nodes,edges;
        Graph graph = new Graph();
        try(Scanner sc = new Scanner(file)) {

            nodes= sc.nextInt();
            edges= sc.nextInt();
            for (int i =0;i<nodes;i++){
                int id = sc.nextInt();
                double y = sc.nextDouble();
                double x = sc.nextDouble();
                graph.addNode(new Node(id,y,x));
                System.out.println(id+" "+y+" "+x);
            }
            for (int i =0;i<edges;i++){
                int srcid = sc.nextInt();
                int dstid = sc.nextInt();
                graph.addEdge(srcid,dstid);
                System.out.println(srcid+" "+dstid);

            }
            System.out.println("*");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
