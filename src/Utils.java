import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {

    public static BufferedImage convertToBW(BufferedImage image) {
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

    public static BufferedImage thresholdBWImage(BufferedImage image, int threshold) {
        BufferedImage bwImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                if (getBWComponent(c, r, image) > threshold) {
                    bwImage.setRGB(c, r, Color.WHITE.getRGB());
                } else bwImage.setRGB(c, r, Color.BLACK.getRGB());
            }
        }
        return bwImage;
    }

    public static int[][] getBWGrid(BufferedImage image) {
        int[][] out = new int[image.getHeight()][image.getWidth()];
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                out[r][c] = getBWComponent(c, r, image);
            }
        }
        return out;
    }

    public static int[][] getColorGrid(BufferedImage image) {
        int[][] out = new int[image.getHeight()][image.getWidth()];
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                out[r][c] = image.getRGB(c, r);
            }
        }
        return out;
    }

    public static int getBWComponent(int x, int y, BufferedImage image) {
        return new Color(image.getRGB(x, y)).getRed();
    }

    public static int average(int[] list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum / list.length;
    }

    public static BufferedImage constructBWImageFromBWGrid(int[][] bwGrid) {
        BufferedImage image = new BufferedImage(bwGrid[0].length, bwGrid.length, BufferedImage.TYPE_INT_RGB);
        for (int r = 0; r < bwGrid.length; r++) {
            for (int c = 0; c < bwGrid[r].length; c++) {
                image.setRGB(c, r, new Color(bwGrid[r][c], bwGrid[r][c], bwGrid[r][c]).getRGB());
            }
        }
        return image;
    }

    public static void display(BufferedImage image, String title) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }
}
