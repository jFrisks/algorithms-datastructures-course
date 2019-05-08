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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tuple) {
            if(this.first.equals(((Tuple) obj).first) && this.last.equals(((Tuple) obj).last)) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return first.hashCode() + last.hashCode();
    }
}
