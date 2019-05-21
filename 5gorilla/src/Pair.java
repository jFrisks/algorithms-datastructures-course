public class Pair {
    private String X, Y;

    public Pair(String X, String Y){
        this.X = X;
        this.Y = Y;
    }

    public String getX() {
        return X;
    }

    public String getY() {
        return Y;
    }

    public Pair prependX(char a) {
        X = a + X;
        return this;
    }

    public Pair prependY(char a) {
        Y = a + Y;
        return this;
    }
}
