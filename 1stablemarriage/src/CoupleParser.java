import persons.Man;
import persons.Woman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class CoupleParser {
    int N = 0;
    Set<Man> men = new HashSet<Man>();
    Set<Woman> women = new HashSet<Woman>();

    public void parse(String filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));

        //get all strings
        int[] linesplit;
        linesplit = br.lines().mapToInt(str -> Integer.parseInt(str)).toArray();

        //First int = How many persons per gender
        N = linesplit[0];

        //Set with all strings of women we have created. Next should be man.
        Set<Integer> createdWomen = new HashSet<Integer>();

        //All other ints - (N+1)*2N
        /* Vi tänker allt som en rekatngel
        * 0
        * 1 2 3 4 5
        * 6 7 8 9 10
        * 11 12 13 14 15
        * .......... */

        for(int i = 1; i <= (N+1)*2*N; i = i+N+1){

            //SKAPA MAN ELLER KVINNA
            if(!createdWomen.contains(i)){
                //skapa kvinna och markera som skapad.
                Woman newWoman = new Woman(i);
                women.add(newWoman);
                createdWomen.add(i);

                //SKAPA PREFERENSERNA
                for(int j=1; j < N+1; j++){
                    //Hitta mannen (linesplit[i+j]) - kalla han theMan - annars skapa  ny
                    //theMan.setPrefByWoman(newWoman)
                }
            }else{
                //TODO: skapa man, men ta ej hand om den ej finns
                Man theMan = getManOrCreate(i);

                //lägg in pref
                for(int j=1; j < N+1; j++){
                    //theMan.setPref(i+j)
                }
            }



        }




    }

    public Set<Man> getMen() {
    }

    public Set<Woman> getWoman() {
    }

    public void printCouples(Set<Woman> women) {
    }
}
