import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    int[] indata;
    int amountOfPeople;
    List<Tuple<Tuple<Integer, Integer>, Integer>> graph;

    public void parse(BufferedReader br) throws IOException {

        System.out.println("Parsing started");

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

        graph = new ArrayList<>();

        for (int i = 2; i <= (rows * 3) + 2; i = i + 3) {
            add(indata[i], indata[i + 1], indata[i + 2]);
        }

        graph.forEach(x -> System.out.println(x.toString()));
        System.out.println(totalWeigth());
    }

    public boolean add(int from, int to, int weight) {
        return graph.add(new Tuple<Tuple<Integer, Integer>, Integer>(new Tuple<Integer, Integer>(from, to), weight));
    }

    public List<Tuple<Tuple<Integer, Integer>, Integer>> getGraph() {
        return graph;
    }
    
    public int totalWeigth() {
        int sum = 0;

        for (Tuple elem :
                graph) {
            int a = (int) elem.last;
            sum = sum + a;
        }

        return sum;
    }
}
