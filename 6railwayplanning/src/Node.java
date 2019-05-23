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

    @Override
    public String toString() {
        return ID.toString();
    }

    public List<Edge> getNeighbourEdges() {
        return neighboursEdges;
    }

    public List<Node<K>> getNeighbours() {

        List<Node<K>> nodes = new ArrayList<>();

        for (Edge edge :
                neighboursEdges) {
            Node from = edge.from;
            Node to = edge.to;

            if (from == this) nodes.add(to);
            else nodes.add(from);
        }

        return nodes;
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
