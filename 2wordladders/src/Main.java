import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Parser parser = new Parser(bufferedReader);

        Map<String, List<String>> graph = Parser.getGraph();

        String start = Parser.getStart();
        String finish = Parser.getFinish();

        BFS bfs = new BFS();
        bfs.run(graph, start, finish);
    }
}