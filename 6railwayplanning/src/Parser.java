import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Parser {

    private final int EDGE_PARSE_START = 4;
    private final int ROW_WIDTH = 3;
    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;
    private int numberOfNodes;
    private int numberOfEdges;
    private int numberOfStudents;
    private int numberOfRoutes;

    public Graph parse(BufferedReader br) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        String line;

        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + " ");
        }

        int[] indata = Arrays.stream(stringBuilder.toString().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        Graph graph = new Graph();
        numberOfNodes = indata[0];
        numberOfEdges = indata[1];
        numberOfStudents = indata[2];
        numberOfRoutes = indata[3];

        graph.setFlow(numberOfStudents);

        int routeParseStart = numberOfEdges * ROW_WIDTH + EDGE_PARSE_START;

        LinkedList<Integer> removeEdges = new LinkedList<>();
        for (int i = routeParseStart; i < indata.length; i++) {
            removeEdges.addLast(indata[i]);
        }

        int counter = 0;
        nodes = new HashMap<>();
        edges = new HashMap<>();
        for (int i = EDGE_PARSE_START; i < numberOfEdges * ROW_WIDTH + EDGE_PARSE_START; i = i + ROW_WIDTH) {

            int fromID = indata[i];
            Node from = getNode(fromID);
            if (fromID == 0) graph.setStartNode(from);
            else if (fromID == numberOfNodes - 1) graph.setEndNode(from);

            /*--------------------------------------------*/

            int toID = indata[i + 1];
            Node to = getNode(toID);
            if (toID == 0) graph.setStartNode(to);
            else if (toID == numberOfNodes - 1) graph.setEndNode(to);

            /*--------------------------------------------*/

            Edge edge = new Edge(counter, from, to, indata[i + 2], 0);
            edges.put(counter, edge);
            from.addNeighbour(edge);
            to.addNeighbour(edge);
            counter++;
        }

        removeEdges.forEach(key -> graph.addRemoveRoute(edges.get(key)));

        graph.mapRelations(nodes, edges);

        return graph;
    }

    private Node getNode(int ID) {
        Node to = nodes.get(ID);

        if (to == null) {
            to = new Node(ID);
            nodes.put(ID, to);
        }

        return to;
    }
}
