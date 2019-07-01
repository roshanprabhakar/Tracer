import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tracer {

    private BufferedImage image;
    private BufferedImage traced;

    public Tracer(BufferedImage image) {
        this.image = image;
        this.image = thresholdBWImage(convertToBW(this.image), 200);
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
        System.out.println("---------------------");
        System.out.println(r + " " + c);
        System.out.println();
        for (int i = r - 1; i < (r + 1); i++) {
            for (int j = c - 1; j < (c + 1); j++) {
                System.out.println(i + " " + j);
                System.out.println(getBWComponent(j, i, image));
                if (j == c - 1) j++;
                if (image.getRGB(j, i) == Color.BLACK.getRGB() && !new Point(j, i).equals(except)) {
                    return new Point(j, i);
                }
            }
        }
        System.out.println("---------------------");
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

    private BufferedImage convertToBW(BufferedImage image) {
        BufferedImage bwImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                Color pixel = new Color(image.getRGB(c, r));
                int average = average(new int[]{pixel.getRed(), pixel.getGreen(), pixel.getBlue()});
                bwImage.setRGB(c, r, new Color(average, average, average).getRGB());
            }
        }
        return bwImage;
    }

    private int average(int[] list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum / list.length;
    }

    private BufferedImage thresholdBWImage(BufferedImage image, int threshold) {
        BufferedImage bwImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                if (getBWComponent(c, r, image) > threshold) {
                    bwImage.setRGB(c, r, Color.WHITE.getRGB());
                }
                else bwImage.setRGB(c, r, Color.BLACK.getRGB());
            }
        }
        return bwImage;
    }

    private int getBWComponent(int x, int y, BufferedImage image) {
        return new Color(image.getRGB(x, y)).getRed();
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
}
