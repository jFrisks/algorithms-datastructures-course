import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.BaseStream;

public class Parser {
    Set<String> words = new HashSet<>();
    Map<String, List<String>> startFinishPairs = new HashMap<>();
    Map<String, List<String>> graph = new HashMap<>();

    public void parse(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();

        String line;
        while((line = br.readLine()) != null){
            sb.append(line + " ");
        }
        String[] inputArray = Arrays.stream(sb.toString().split(" ")).filter(s -> !s.equals(" ")).toArray(String[]::new);

        int nWords = Integer.parseInt(inputArray[0]);
        int graphRuns = Integer.parseInt(inputArray[1]);

        for(int i =0; i < nWords; i++){
            String word = inputArray[i+2];
            words.add(word);
        }


        for(int j = 0; j < 2*graphRuns; j +=2){
            String start = inputArray[j+nWords+2];
            String finish = inputArray[j+nWords+2+1];

            List<String> value = startFinishPairs.get(start);
            if (value != null) {
                value.add(finish);
            } else {
                value = new ArrayList<>();
                startFinishPairs.put(start, value);
                value.add(finish);
            }
        }

        createGraph();
    }

    private void createGraph() {
        for(String word1 : words){
            List<String> word1Neihbours = new ArrayList<>();
            for(String word2: words) {
                if(hasedge(word1, word2)){
                    word1Neihbours.add(word2);
                }
            }
            graph.put(word1, word1Neihbours);
        }
    }

    boolean hasedge(String word1, String word2) {
        if (word1.length() < 2) return false;
        for (int i = 1; i < word1.length(); i++) {
            if(!(word2.contains(String.valueOf(word1.charAt(i))))) return false;
        }
        return true;
    }

    public Map<String, List<String>> getGraph() {
        return graph;
    }

    public Map<String, List<String>> getStartFinishPairs() {
        return startFinishPairs;
    }
}