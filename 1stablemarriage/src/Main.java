import persons.Man;
import persons.Woman;

import java.util.Set;

class Main {
	
	public static void main(String[] args) {

	    CoupleParser reader = new CoupleParser();

	    reader.parse(args[0]);

        Set<Man> men = reader.getMen();
        Set<Woman> women = reader.getWoman();

        Matcher.GS(women, men);

        reader.printCouples(women);
	}

}
