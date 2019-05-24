import com.sun.jdi.Value;
import javafx.scene.chart.ValueAxis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();

        BufferedReader br;

        br = new BufferedReader(new FileReader("./6railwayplanning/data/secret/3large.in"));
        //br = new BufferedReader(new InputStreamReader(System.in));

        Graph graph = parser.parse(br);
        testDeleteRoutes(graph, graph.getRemoveRoutes().size()/2);

        //System.out.println("without: " + flowWithoutRoutes(graph, 15));
        //NetworkFlow nf = new NetworkFlow(graph);
        //System.out.println("flow: " + nf.calculateFlow());
    }

    private static void testDeleteRoutes(Graph graph, int range) {

        ValueNode node = makeBST(0, range);
        System.out.println(node);
    }


    //private static void testDeleteRoutes(Graph graph, int range, int lastFlow) {}

    private static ValueNode makeBST(int start, int end) {
        int median = (end - start)/2;

        System.out.println(end - start);
        if (end - start <= 1) {
            return new ValueNode<Integer, ValueNode, ValueNode>(median, null, null);
        }

        ValueNode node = new ValueNode<Integer, ValueNode, ValueNode>(median, makeBST(start, median), makeBST(median, end));

        return node;
    }

    private static int flowWithoutRoutes(Graph graph, double range) {
        Graph graphCopy = graph.copy();
        List<Edge> removeRoutes = graphCopy.getRemoveRoutes();
        NetworkFlow nf = new NetworkFlow(graphCopy);

        for (int i = 0; i < range; i++) {
            removeRoutes.get(i).remove();
        }

        return  nf.calculateFlow();
    }

}
