import java.util.List;

public class Node<K> {

    K ID;
    List<Edge> neighbours;

    public Node(K ID) {
        this.ID = ID;
    }

    public void setNeighboursReachable() {
        neighbours.forEach(x -> x.setReachable(true));
    }

    @Override
    public String toString() {
        return ID.toString();
    }

    public List<Edge> getNeighbourEdges() {
        return neighbours;
    }
}
