import persons.Man;
import persons.Woman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class CoupleParser {
    int N = 0;
    Map<Integer, Man> men = new HashMap<>();
    Map<Integer, Woman> women = new HashMap<>();

    public void parse(String filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));

        StringBuilder stringBuilder = new StringBuilder();

        String line;

        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }

        //get all strings
        int[] linesplit;
        linesplit = Arrays.stream(stringBuilder.toString().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        //First int = How many persons per gender
        N = linesplit[0];

        //All other ints - (N+1)*2N
        /* Vi tänker allt som en rekatngel
        * 0
        * 1 2 3 4 5
        * 6 7 8 9 10
        * 11 12 13 14 15
        * .......... */

        for(int i = 1; i <= (N+1)*2*N; i = i+N+1){

            if (!women.containsKey(i)) {
                Woman theWoman = women.put(i, new Woman(i));

                //SKAPA PREFERENSERNA
                for(int j=1; j < N+1; j++){

                    Man theMan = men.getOrDefault(i + j, men.put(i + j, new Man(i + j)));
                    theMan.addPrefByWoman(theWoman, j);
                }
            } else {

                Man theMan = men.getOrDefault(i, men.put(i, new Man(i)));

                for(int j=1; j < N+1; j++){

                    Woman theWoman = women.getOrDefault(i + j, women.put(i + j, new Woman(i + j)));
                    theMan.addToPref(theWoman);
                }

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
