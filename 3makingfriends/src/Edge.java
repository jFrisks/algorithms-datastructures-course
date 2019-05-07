public class Edge {

    Node from;
    Node to;
    int weight;
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
                ") weight=" + weight +
                ", isReachable=" + isReachable +
                "}";
    }
}
