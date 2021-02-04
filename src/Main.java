import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        File file = new File("map.txt");
        int nodes,edges;
        Graph graph ;
        try(Scanner sc = new Scanner(file)) {
            nodes= sc.nextInt();
            edges= sc.nextInt();
            graph = new Graph(nodes+2);
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
                //System.out.println(srcid+" "+dstid);
            }
            ArrayList<Answer> answers = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("**");
                int time = scanner.nextInt();
                int src = scanner.nextInt();
                int dst = scanner.nextInt();
                for (Answer answer:answers) {
                    answer.trafficConfig(time);
                }

                answers.add(graph.dijkstra(time,src,dst));
                for (Answer answer:answers) {
                    answer.trafficToZero();
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}

