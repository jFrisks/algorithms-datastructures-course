import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.BaseStream;

public class Parser {
    Set<String> words = new HashSet<>();
    Map<String, String> startFinishPairs = new HashMap<>();
    Map<String, List<String>> graph;
    String start, finish;

    public void parse(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();

        String line;
        while((line = br.readLine()) != null){
            sb.append(line + " ");
        }
        String[] inputArray = (String[]) Arrays.stream(sb.toString().split(" ")).filter(s -> !s.equals(" ")).toArray();

        int nWords = Integer.parseInt(inputArray[0]);
        int graphRuns = Integer.parseInt(inputArray[1]);

        for(int i =0; i < nWords; i++){
            String word = inputArray[nWords+2];
            words.add(word);
        }

        for(int j = 0; j < 2*graphRuns; j +=2){
            String start = inputArray[j+nWords+2];
            String finish = inputArray[j+nWords+2+1];
            startFinishPairs.put(start, finish);
        }
    }

    public static Map<String, List<String>> getGraph() {
        return null;
    }

}