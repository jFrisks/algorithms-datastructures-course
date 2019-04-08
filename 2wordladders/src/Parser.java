import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

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
                if(hasEdge(word1, word2)){
                    word1Neihbours.add(word2);
                }
            }
            graph.put(word1, word1Neihbours);
        }
    }

    boolean hasEdge(String word1, String word2) {
        if (word1.length() < 2) return false;
        String word = word1.substring(1);
        return isContainedIn(word.toCharArray(), word2.toCharArray());
    }

    boolean isContainedIn(char[] word1, char[] word2) {
        boolean[] removed1 = new boolean[word1.length];
        boolean[] removed2 = new boolean[word2.length];
        int removedAmount = 0;
        for (int i = 0; i < word1.length; i++) {
            for (int j = 0; j < word2.length; j++) {
                if (!removed1[i] && !removed2[j] && word1[i] == word2[j]) {
                    removed1[i] = true;
                    removed2[j] = true;
                    removedAmount++;
                }
            }
        }

        if (removedAmount == word1.length) return true;
        else return false;
    }

    public Map<String, List<String>> getGraph() {
        return graph;
    }

    public Map<String, List<String>> getStartFinishPairs() {
        return startFinishPairs;
    }
}