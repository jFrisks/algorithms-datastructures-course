import persons.Man;
import persons.Woman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CoupleParser {
    int N = 0;
    Set<Man> men = new HashSet<Man>();
    Set<Woman> women = new HashSet<Woman>();

    public void parse(String filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));

        //How many persons
        N = Integer.parseInt(br.readLine());

        //Loop all persons
        for(int i = 0; i < 2*N; i++){
            Woman woman = null;

            String line = br.readLine();
            String[] linesplit = line.split(" ");

            int first = Integer.parseInt(linesplit[0]);
            if(woman == null) {
                woman = new Woman(first);
            }else {
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
