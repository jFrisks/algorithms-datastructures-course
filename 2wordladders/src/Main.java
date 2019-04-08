import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("/Users/eliasvernersson/IdeaProjects/algorithms-datastructures-course/2wordladders/data/secret/1small1.in"));


        Parser parser = new Parser();

        parser.parse(br);
        Map<String, List<String>> graph = parser.getGraph();
        Map<String, List<String>> startFinishPairs = parser.getStartFinishPairs();

        BFS bfs = new BFS();
        for(String start : startFinishPairs.keySet()){
            startFinishPairs.get(start).forEach(finish -> System.out.println(start + " -> " + finish+ " " + bfs.run(graph, start, finish)));
        }

    }
}