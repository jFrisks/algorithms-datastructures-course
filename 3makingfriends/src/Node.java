import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<K> {

    K ID;
    List<Edge> neighboursEdges = new ArrayList<>();

    public Node(K ID) {
        this.ID = ID;
    }

    public void addNeighbour(Edge neighbour) {
        neighboursEdges.add(neighbour);
    }

    public void setNeighboursReachable() {
        neighboursEdges.forEach(x -> x.setReachable(true));
    }

    @Override
    public String toString() {
        return ID.toString();
    }

    public List<Edge> getNeighbourEdges() {
        return neighboursEdges;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {

            boolean a = this.ID == ((Node) o).ID;
            //boolean b = this.neighboursEdges.equals(((Node) o).neighboursEdges);
            if (a) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(ID, neighboursEdges);
        return Objects.hash(ID);
    }
}
