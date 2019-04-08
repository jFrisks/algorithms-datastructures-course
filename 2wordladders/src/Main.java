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
        BufferedReader br = new BufferedReader(new FileReader("/Users/Jonte/Google Drive/UNIVERSITET - LUND/Civilingenjör - Utbildningen/Kurser/AlgoDat/Labbar/algorithms-datastructures-course/2wordladders/data/secret/5large1.in"));


        Parser parser = new Parser();

        parser.parse(br);
        Map<String, List<String>> graph = parser.getGraph();
        List<Pairs> startFinishPairs = parser.getStartFinishPairs();

        BFS bfs = new BFS();
        for(Pairs pair : startFinishPairs){
            System.out.println(pair.start + " -> " + pair.finish + " " + bfs.run(graph, pair.start, pair.finish));
        }

    }
}