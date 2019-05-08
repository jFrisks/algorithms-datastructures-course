import java.io.*;
import java.util.Set;

public class Main{

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("./3makingfriends/data/secret/4huge.in"));

        //long startTime = System.currentTimeMillis();
        graph.parse(br);
        MST mst = new MST();
        Node startNode = graph.getStartNode();
        Set<Edge> graf = mst.prim(graph, graph.getStartNode());

        int sum = 0;

        for (Edge edge :
                graf) {
            sum += edge.getWeight();
        }

        System.out.println(sum);
        //long finishTime = System.currentTimeMillis();
       // System.out.println((finishTime-startTime) / 1000);
    }
}