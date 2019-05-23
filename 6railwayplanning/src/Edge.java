import java.util.Objects;

public class Edge {

    Node from;
    Node to;
    private int capacity;
    private int ID;
    private int flow;

    public Edge(int ID, Node from, Node to, int capacity, int flow) {
        this.ID = ID;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = flow;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "{(" +
                from.toString() +
                " -> " + to.toString() +
                ") " + getFlow() +
                "/" + getCapacity() +
                "}";
    }

    public int getFlow() {
        return flow;
    }

    public int getRemainingCapacity() {
        return capacity - flow;
    }

    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            if (
                    this.from.equals(((Edge) o).from) &&
                    this.to.equals(((Edge) o).to) &&
                    this.capacity == ((Edge) o).capacity
            )
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, capacity);
    }

    public void addFlow(int addedFlow) {
        flow = flow + addedFlow;
    }

    public void remove() {
        Node from = this.from;
        Node to = this.to;

        from.neighboursEdges.remove(this);
        to.neighboursEdges.remove(this);

    }
}
