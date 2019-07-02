import java.util.ArrayList;
import java.util.Collections;

public class Cluster {

    private ArrayList<Point> points;
    private Point center;

    public Cluster() {
        points = new ArrayList<>();
    }

    public Cluster(Point center) {
        this.points = new ArrayList<>();
        this.center = center;
    }

    public Cluster(ArrayList<Point> points) {
        this.points = points;
        recalculateCenter();
    }

    public Point getRandomPointExcept(Point exception) {
        Collections.shuffle(points);
        for (Point p : points) {
            if (!p.equals(exception)) {
                return p;
            }
        }
        return null;
    }

    public void recalculateCenter() {
        int rowSum = 0;
        int colSum = 0;

        for (Point p : this.points) {
            rowSum += p.getY();
            colSum += p.getX();
        }

        this.center = new Point(colSum / this.points.size(), rowSum / this.points.size());
    }

    public Point get(int index) {
        return points.get(index);
    }

    public Point getCenter() {
        return this.center;
    }


    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public void clear() {
        points.clear();
    }

    public void add(Point p) {
        this.points.add(p);
    }

    public int size() {
        return this.points.size();
    }
}
