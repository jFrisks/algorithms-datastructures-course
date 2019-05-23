import javax.swing.plaf.metal.MetalTheme;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();

        BufferedReader br;

        br = new BufferedReader(new FileReader("./6railwayplanning/data/secret/1small.in"));
        //br = new BufferedReader(new FileReader("./6railwayplanning/data/sample/1.in"));

        Graph graph = parser.parse(br);
        testDeleteRoutes(graph, graph.getRemoveRoutes().size()/2, 1, 0);

        //System.out.println("without: " + flowWithoutRoutes(graph, 15));
        //NetworkFlow nf = new NetworkFlow(graph);
        //System.out.println("flow: " + nf.calculateFlow());
    }

    private static void testDeleteRoutes(Graph graph, int range, int depth, int lastFlow) {

        int flow = flowWithoutRoutes(graph, range);
        int maxDepth = (int)(Math.ceil(Math.log(graph.getRemoveRoutes().size())/Math.log(2)));
        int counter = 0;
        int finalRange = 1;

        while (range > 0) {
            counter++;
            int low = (range/2) - 1;
            int high = (int)(Math.ceil(3*(range/2)) - 1);

            if (flow < graph.getFlow()) {
                flow = flowWithoutRoutes(graph, low);
                finalRange = low;
            } else if (flow > graph.getFlow()) {
                flow = flowWithoutRoutes(graph, high);
                finalRange = high;
            }
        }

        System.out.println(finalRange + " " + flow);
    }

    private static int flowWithoutRoutes(Graph graph, int range) {
        Graph graphCopy = graph.copy();
        List<Edge> removeRoutes = graphCopy.getRemoveRoutes();
        NetworkFlow nf = new NetworkFlow(graphCopy);

        for (int i = 0; i < range; i++) {
            removeRoutes.get(i).remove();
        }

        return  nf.calculateFlow();
    }

}
