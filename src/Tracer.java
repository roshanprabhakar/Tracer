import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tracer {

    private BufferedImage image;
    private BufferedImage traced;

    public Tracer(BufferedImage image, int threshold) {
        this.image = image;
        this.image = Utils.thresholdBWImage(Utils.convertToBW(this.image), threshold);
        traced = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void trace() {
        for (int r = 1; r < image.getHeight() - 1; r++) {
            for (int c = 1; c < image.getWidth() - 1; c++) {
                if (image.getRGB(c, r) == Color.BLACK.getRGB()) {
                    trace(r, c);
                }
            }
        }
    }

    public void trace(int r, int c) {
        Point oldPoint = new Point(c, r);
        trace(r, c, oldPoint);
    }

    public void trace(int r, int c, Point oldPoint) {
        Point nextPoint = getNextPoint(r, c, oldPoint);
        if (nextPoint == null || onTheEdge(r, c)) {
            return;
        } else {
            traced.setRGB(nextPoint.getX(), nextPoint.getY(), Color.WHITE.getRGB());
            trace(nextPoint.getY(), nextPoint.getX(), nextPoint);
        }
    }

    public Point getNextPoint(int r, int c, Point except) {
        for (int i = r - 1; i < (r + 1); i++) {
            for (int j = c - 1; j < (c + 1); j++) {
                if (j == c - 1) j++;
                if (image.getRGB(j, i) == Color.BLACK.getRGB() && !new Point(j, i).equals(except)) {
                    return new Point(j, i);
                }
            }
        }
        return null;
    }

    public boolean onTheEdge(int r, int c) {
        try {
            image.getRGB(c - 2, r - 2);
            image.getRGB(c + 2, r + 2);
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    public static void display(BufferedImage image) {
        JFrame frame = new JFrame(image.toString());
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }

    public void displayTraced() {
        display(traced);
    }

    public BufferedImage getTraced() {
        return traced;
    }
}
