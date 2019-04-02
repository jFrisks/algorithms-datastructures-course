import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CoupleParser {
    int N = 0;
    Map<Integer, Man> men = new HashMap<>();
    Map<Integer, Woman> women = new HashMap<>();
    int[] personArray;
    int[][] menArr;
    int[][] womArr;

    public void parse() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + " ");
        }

        //get all strings
        personArray = Arrays.stream(stringBuilder.toString().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        //First int = How many persons per gender
        N = personArray[0];

        divide(matrixize(N));

        putAll();

    }

    private int[][] matrixize(int n) {
        int[][] k = new int[2*n][n+1];

        for (int i = 0; i < (personArray.length-1)/(n+1); i++) {

            for (int j = 0; j < n+1; j++) {
                k[i][j] = personArray[(i*(n+1))+j+1];
            }
        }
        return k;
    }

    private void divide(int[][] people){
        Set<Integer> used = new HashSet<>();

        menArr = new int[people.length/2][people[0].length/2];
        womArr = new int[people.length/2][people[0].length/2];

        int a = 0;
        int b = 0;

        for (int i = 0; i < people.length; i++) {
            int inUse = people[i][0];

            if (!used.contains(inUse)) {
                womArr[a] = people[i];
                a++;
            } else {
                menArr[b] = people[i];
                b++;
            }
            used.add(inUse);
        }
    }

    private void putAll() {

        //create all men
        for (int[] ints : menArr) {
            Man man = new Man(ints[0]);
            men.put(ints[0], man);
        }

        //create all women
        for (int[] ints : womArr) {
            Woman woman = new Woman(ints[0]);
            women.put(ints[0], woman);
        }

        //create pref for men
        for (int i = 0; i < menArr.length; i++) {
            for (int j = 1; j < menArr[i].length; j++) {
                men.get(menArr[i][0]).addToPref(women.get(menArr[i][j]));
            }
        }

        //create pref for women in men
        for (int i = 0; i < womArr.length; i++) {
            for (int j = 1; j < womArr[i].length; j++) {

                Man man = men.get(womArr[i][j]);
                Woman woman = women.get(womArr[i][0]);
                man.addPrefByWoman(woman, j);
            }
        }

    }

    public Set<Man> getMen(){
        return new HashSet<Man>(men.values());
    }

    public Set<Woman> getWoman() {
        return new HashSet<Woman>(women.values());
    }

}
