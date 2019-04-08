import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Parser parser = new Parser();

        parser.parse(br);
        Map<String, List<String>> graph = Parser.getGraph();
        Map<String, String> startFinishPars = parser.getStartFinishPairs();

        BFS bfs = new BFS();
        for(String start : startFinishPars.keySet()){
            bfs.run(graph, start, startFinishPars.get(start));
        }
    }
}