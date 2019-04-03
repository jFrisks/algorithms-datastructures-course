import java.util.*;
import java.util.stream.Stream;

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

                p.push(dumped);

            } else {
                p.push(m);
            }
        }

    }

    public static String printCouples(Set<Woman> women) {

        StringBuilder stringBuilder = new StringBuilder();

        women.stream().sorted((w1, w2) -> w2.getID()-w1.getID()).forEach(x -> stringBuilder.append(x.getPartner().getID() + "\n"));

        return stringBuilder.toString();
    }

}
