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
            stringBuilder.append(line + " ");
        }

        //get all strings
        int[] personArray;
        personArray = Arrays.stream(stringBuilder.toString().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

        //First int = How many persons per gender
        N = personArray[0];

        //All other ints - (N+1)*2N
        /* Vi tänker allt som en rekatngel
        * 0
        * 1 2 3 4 5
        * 6 7 8 9 10
        * 11 12 13 14 15
        * .......... */

        for(int i = 1; i <= (N+1)*2*N; i = i+N+1){
            int mainID = personArray[i];

            //När kvinnan ej finns
            if (!women.containsKey(mainID)) {
                Woman theWoman = new Woman(mainID);
                women.put(mainID, theWoman);

                //SKAPA PREFERENSERNA
                for(int j=1; j < N+1; j++){
                    int prefID = personArray[i+j];

                    Man theMan = men.get(prefID);
                    if(theMan == null){
                        theMan = new Man(prefID, N);
                        men.put(prefID, theMan);
                    }
                    theMan.addPrefByWoman(theWoman,j-1);
                }
            } else {
                //Om kvinnan redan parsats
                Man theMan = men.get(mainID);
                if(theMan == null){
                    theMan = new Man(mainID, N);
                    men.put(mainID, theMan);
                }

                //fixar pref
                for(int j=1; j < N+1; j++){
                    int prefID = personArray[i+j];

                    Woman theWoman = women.get(prefID);
                    if(theWoman == null){
                        theWoman = new Woman(prefID);
                        women.put(prefID, theWoman);
                    }
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
