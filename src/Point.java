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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distanceTo(Point other) {
        double sumX = (x - other.getX()) * (x - other.getX());
        double sumY = (y - other.getY()) * (y - other.getY());
        return Math.sqrt(sumX + sumY);
    }

    public String toString() {
        return "x: " + x + ", y:" + y;
    }
}
