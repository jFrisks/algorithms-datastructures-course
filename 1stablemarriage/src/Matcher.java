import persons.Man;
import persons.Woman;

import java.util.*;

public class Matcher {

    public static void GS(Set<Woman>  women, Set<Man> men) {

        Deque<Man> p = new ArrayDeque<Man>();
        p.addAll(men);

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

    public static String printCouples(Set<Woman> women) {

        StringBuilder stringBuilder = new StringBuilder();
        for (Woman woman : women) {

            stringBuilder.append(woman.getID());
            stringBuilder.append(" ");
            Man man = woman.getPartner();
            stringBuilder.append(man.getID());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
