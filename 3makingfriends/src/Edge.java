import java.util.Objects;

public class Edge {

    Node from;
    Node to;
    private int weight;
    private boolean isReachable;

    public Edge(Node from, Node to, int weight, boolean isReachable) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.isReachable = isReachable;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    public boolean isReachable() {
        return isReachable;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "{(" +
                from.toString() +
                " -> " + to.toString() +
                ") weight=" + getWeight() +
                ", isReachable=" + isReachable +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            if (this.from.equals(((Edge) o).from) &&
                    this.to.equals(((Edge) o).to) &&
                    this.weight == ((Edge) o).weight &&
                    this.isReachable == ((Edge) o).isReachable)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight, isReachable);
    }
}
