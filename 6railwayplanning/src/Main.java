import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();

        BufferedReader br;

        //br = new BufferedReader(new FileReader("./6railwayplanning/data/secret/3large.in"));
        br = new BufferedReader(new InputStreamReader(System.in));

        Graph graph = parser.parse(br);
        testDeleteRoutes(graph, graph.getRemoveRoutes().size()/2);

        //System.out.println("without: " + flowWithoutRoutes(graph, 15));
        //NetworkFlow nf = new NetworkFlow(graph);
        //System.out.println("flow: " + nf.calculateFlow());
    }

    private static void testDeleteRoutes(Graph graph, int range) {

        int finalRange = 1;
        double delta = range;
        int flow = 0;
        boolean isDone = false;

        int[] trail = new int[]{-1,-2,-3};
        int counter = 0;
        while (!isDone) {

            flow = flowWithoutRoutes(graph, range);

            delta = Math.round(delta/2);

            //System.out.println(range + "±" +  delta);

            if (flow < graph.getFlow()) {
                range = (int)Math.round(range - delta);
            } else if (flow > graph.getFlow()) {
                range = (int)Math.round(range + delta);
            }

            trail[counter%3] = range;
            isDone = trail[0] == trail[1] || trail[1] == trail[2] || trail[0] == trail[2];
            //System.out.println("[" + trail[0] + "," + trail[1] + "," +  trail[2] + "]");

            counter++;
        }

        int min = Integer.min(trail[0], Integer.min(trail[1], trail[2]));

        flow = flowWithoutRoutes(graph, min);

        System.out.println(min + " " + flow);
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
