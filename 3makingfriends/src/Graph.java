import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Graph {

    int[] indata;
    int amountOfPeople;
    List<Edge> relationMap = new ArrayList<>();
    List<Node<Integer>> nodes = new ArrayList<>();

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

        for (int i = 2; i <= (rows * 3) + 2; i = i + 3) {
            add(indata[i], indata[i + 1], indata[i + 2]);
        }

        relationMap.forEach(x -> System.out.println(x.toString()));

    }

    public void add(int from, int to, int weight) {

        Node fromNode = new Node(from);
        Node toNode = new Node(to);

        relationMap.add(new Edge(fromNode, toNode, weight, false));
        nodes.add(fromNode);
        nodes.add(toNode);
    }

    public List<Node<Integer>> getAllNodes() {
        return nodes;
    }

    public List<Edge> getAllEdges() {
        return relationMap;
    }

}
