import java.io.*;
import java.util.Set;

class Main {
	
	public static void main(String[] args) throws FileNotFoundException {

	    CoupleParser reader = new CoupleParser();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("/Users/Jonte/Google Drive/UNIVERSITET - LUND/Civilingenjör - Utbildningen/Kurser/AlgoDat/Labbar/algorithms-datastructures-course/1stablemarriage/data/sample/1.in"));

        try {
            reader.parse(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Man> men = reader.getMen();
        Set<Woman> women = reader.getWoman();

        Matcher.GS(women, men);

        String string = Matcher.printCouples(women);
        System.out.println(string);
	}

}
