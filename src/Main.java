import java.io.File;
import java.math.BigInteger;
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
            sc.nextLine();
            for (int i =0;i<nodes;i++){
                String[] s = sc.nextLine().split(" ");
                graph.addNode(new Node(s[0],Double.parseDouble(s[1]),Double.parseDouble(s[2])));
            }
            for (int i =0;i<edges;i++){
                String[] s = sc.nextLine().split(" ");
                graph.addEdge(s[0],s[1]);
            }
            ArrayList<Answer> answers = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("**");
                String[] s = scanner.nextLine().split(" ");
                for (Answer answer:answers) {
                    answer.trafficConfig(Double.parseDouble(s[0]));
                }

                answers.add(graph.dijkstra(Double.parseDouble(s[0]),s[1],s[2]));
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

