public class ValueNode<V, F, S> {

    V value;
    F first;
    S second;

    public ValueNode(V value, F first, S second) {
        this.value = value;
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "[" + value +
                ", " + first.toString() +
                ", " + second.toString() +
                ']';
    }
}
