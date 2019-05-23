import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StringAlignmentParser {

    private final int SPACE_COST = -4;
    private Map<String, Integer> combination = new HashMap<>();
    private List<Pair> wordPairs = new ArrayList<>();

    void parse(BufferedReader br) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        String line = br.readLine();
        String[] characters = line.split(" ");

        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + " ");
        }

        String[] in = stringBuilder.toString().split(" ");
        for (int i = 0; i < characters.length; i++) {
            for (int j = i; j < characters.length; j++) {
                String key = (characters[i] + characters[j]).toUpperCase();
                int value = Integer.parseInt(in[i + j * characters.length]);

                key = sortKey(key);
                combination.put(key, value);
            }
        }

        combination.put("*", SPACE_COST);

        int queries = characters.length*characters.length;

        for (int i = characters.length*characters.length + 1; i < in.length - 1; i = i + 2) {
            wordPairs.add(new Pair(in[i], in[i + 1]));
        }
    }

    public int getCost(String key) {

        key = key.toUpperCase();
        String keyFlipped = new StringBuilder(key).reverse().toString();

        if (key.equals("*")) {
            return combination.get(key);
        }

        if (key.charAt(0) - key.charAt(1) <= 0) {
            return combination.get(key);
        } else {
            return combination.get(keyFlipped);
        }
    }

    public String sortKey(String key) {
        key = key.toUpperCase();
        String keyFlipped = new StringBuilder(key).reverse().toString();

        if (key.charAt(0) - key.charAt(1) <= 0) {
            return key;
        } else {
            return keyFlipped;
        }
    }

    public List<Pair> getWordPairs() {
        return wordPairs;
    }
}
