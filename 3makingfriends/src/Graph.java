import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Graph {

    int[] indata;
    int amountOfPeople;
    List<Edge> edges = new ArrayList<>();
    Map<Integer, Node<Integer>> nodes = new HashMap<>();
    private Node startNode = null;

    public void parse(BufferedReader br) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + " ");
        }

        indata = Arrays.stream(stringBuilder.toString().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        amountOfPeople = indata[0];
        int rows = indata[1];
        //Since we don't want to go out of bounds on the far end.
        rows = rows - 1;

        //Create start node and add it to the tree
        startNode = new Node(indata[2]);
        nodes.put((int)startNode.ID, startNode);

        for (int i = 2; i <= (rows * 3) + 2; i = i + 3) {
            add(indata[i], indata[i + 1], indata[i + 2]);
        }
    }

    public void add(int from, int to, int weight) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null) {
            fromNode = new Node<>(from);
            nodes.put(from, fromNode);
        }

        if (toNode == null) {
            toNode = new Node<>(to);
            nodes.put(to, toNode);
        }

        //GÖR CHECK OM REDAN FINNS

        Edge edge = new Edge(fromNode, toNode, weight, false);

        edges.add(edge);
        nodes.put(from, fromNode);
        nodes.put(to, toNode);

        fromNode.addNeighbour(edge);
        toNode.addNeighbour(edge);
    }

    public Set<Node<Integer>> getAllNodes() {
        Set<Node<Integer>> allNodes = new HashSet<>(nodes.values());
        return allNodes;
    }

    public List<Edge> getAllEdges() {
        return edges;
    }

    public Node getStartNode() {
        return startNode;
    }

}
