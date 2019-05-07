public class Tuple <F, L> {

    F first;
    L last;

    public Tuple(F first, L last) {
        this.first = first;
        this.last = last;
    }

    public F getFirst() {
        return first;
    }

    public L getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "(" +
                first.toString() +
                ", "+ last.toString() +
                ')';
    }
}
