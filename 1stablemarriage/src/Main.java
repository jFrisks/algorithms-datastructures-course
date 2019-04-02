import persons.Man;
import persons.Woman;

import java.io.IOException;
import java.util.Set;

class Main {
	
	public static void main(String[] args) {

	    CoupleParser reader = new CoupleParser();

        try {
            reader.parse(args[0]);
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
