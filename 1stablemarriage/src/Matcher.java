import persons.Man;
import persons.Woman;

import java.util.*;

public class Matcher {

    public static void GS(Set<Woman>  women, Set<Man> men) {

        Deque<Man> p = new ArrayDeque<Man>();

        while(!p.isEmpty()) {

            Man m = p.pop();

            Woman w = m.getFirstWoman();

            if (w.getPartner() == null) {

                w.setPartner(m);

            } else if ((w.getPartner().getAtIndex(w) - m.getAtIndex(w)) > 0) {

                Man dumped = w.getPartner();

                w.setPartner(m);

                p.addLast(dumped);

            } else {
                p.addLast(m);
            }
        }

    }

    public static void printCouples(Set<Woman> women) {

        StringBuilder stringBuilder = new StringBuilder();
        for (Woman woman : women) {

            stringBuilder.append(woman.getID());
            stringBuilder.append("  ");
            stringBuilder.append(woman.getPartner().getID());
            stringBuilder.append("\n");

        }
    }

}
