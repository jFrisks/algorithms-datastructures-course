import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class EdgeComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {

        if (o1.isReachable() && o2.isReachable()) {
            return o1.getWeight() - o2.getWeight();
        } else if (o1.isReachable()) {
            return -1;
        } else if (o2.isReachable()) {
            return 1;
        } else {
            return 0;
        }
    }
}
