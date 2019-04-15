import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("/Users/Jonte/Google Drive/UNIVERSITET - LUND/Civilingenjör - Utbildningen/Kurser/AlgoDat/Labbar/algorithms-datastructures-course/2wordladders/data/secret/6large2.in"));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);

        Parser parser = new Parser();

        parser.parse(br);
        Map<String, List<String>> graph = parser.getGraph();
        List<Pairs> startFinishPairs = parser.getStartFinishPairs();

        BFS bfs = new BFS();
        for(Pairs pair : startFinishPairs){
            //out.write(pair.start + " -> " + pair.finish + " " + bfs.run(graph, pair.start, pair.finish));
            out.write(bfs.run(graph, pair.start, pair.finish));
            out.newLine();
            //System.out.println(pair.start + " -> " + pair.finish + " " + bfs.run(graph, pair.start, pair.finish));
        }
        out.flush();
    }
}