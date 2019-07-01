public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) return true;
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
