import java.io.*;
import java.util.Set;

public class Main{

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        BufferedReader br = new BufferedReader(new FileReader("./3makingfriends/data/secret/3large.in"));

        graph.parse(br);
        MST mst = new MST();
        Set<Edge> graf = mst.prim(graph);

        int sum = 0;

        for (Edge edge :
                graf) {
            sum += edge.getWeight();
        }

        System.out.println(sum);
    }
}