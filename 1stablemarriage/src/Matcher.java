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

            } else if (w.getManPlacement(w.getPartner().getID()) - w.getManPlacement(m.getID()) > 0) {

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

        //women.stream().sorted((w1, w2) -> w2.getID()-w1.getID()).forEach(x -> stringBuilder.append(x.getPartner().getID() + "\n"));

        //women.stream().forEach(x -> stringBuilder.append(x.getID() + "  " + x.getPartner().getID()));
        women.stream().sorted(
                Comparator.comparingInt(w2 -> w2.getPartner().getID())
        ).forEach(w ->
                stringBuilder.append(w.getPartner().getID() + " " + w.getID() + "\n")
        );

        return stringBuilder.toString();
    }

}
