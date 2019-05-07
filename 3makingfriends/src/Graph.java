import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Graph {

    int[] indata;
    int amountOfPeople;
    Map<Integer, Map<Integer, Integer>> weightMap;
    Map<Tuple<Integer, Integer>, Integer> relationMap = new HashMap<>();
    Map<Integer, ArrayList<Integer>> nodes = new HashMap<Integer, ArrayList<Integer>>();

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

        weightMap = new HashMap<>();

        for (int i = 2; i <= (rows * 3) + 2; i = i + 3) {
            add(indata[i], indata[i + 1], indata[i + 2]);
        }
    }

    public void add(int from, int to, int weight) {

        List<Integer> neighbours = nodes.get(from);
        if (neighbours != null) {
            neighbours.add(to);
        } else {
            ArrayList<Integer> newList = new ArrayList<>();
            newList.add(to);
            nodes.put(from, newList);
        }

        Map<Integer, Integer> map = weightMap.get(weight);
        if (map != null) {
            map.put(from, to);
            relationMap.put(new Tuple<Integer, Integer>(from, to), weight);
        } else {
            Map newMap = new HashMap<Integer, Integer>();
            newMap.put(from, to);
            relationMap.put(new Tuple<Integer, Integer>(from, to), weight);
            weightMap.put(weight, newMap);
        }
    }

    public Map getRelation(int weight) {
        return weightMap.get(weight);
    }

    public Set<Integer> getAllNodes() {
        return nodes.keySet();
    }

    public int getWeight(int from, int to) {
        return relationMap.get(new Tuple<Integer, Integer>(from, to));
    }

    public List getAdjacent(int node) {
        return nodes.get(node);
    }
}
