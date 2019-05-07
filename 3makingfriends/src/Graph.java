import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Graph {

    int[] indata;
    int amountOfPeople;
    Map<Integer, Map<Integer, Integer>> graph;
    Set<Integer> nodes = new HashSet<Integer>();

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

        graph = new HashMap<>();

        for (int i = 2; i <= (rows * 3) + 2; i = i + 3) {
            add(indata[i], indata[i + 1], indata[i + 2]);
        }
    }

    public Map add(int from, int to, int weight) {
        nodes.add(from);
        nodes.add(to);

        Map<Integer, Integer> map = graph.get(weight);
        if (map != null) {
            map.put(from, to);
            return graph;
        } else {
            Map newMap = new HashMap<Integer, Integer>();
            newMap.put(from, to);
            return graph.put(weight, newMap);
        }
    }

    public Map getRelation(int weight) {
        return graph.get(weight);
    }

    public Set<Integer> getAllNodes() {
        return nodes;
    }
}
