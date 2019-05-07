import java.util.List;

public class Node<K> {

    K ID;
    List<Edge> neighbours;

    public void setNeighboursReachable() {
        neighbours.forEach(x -> x.setReachable(true));
    }

}
