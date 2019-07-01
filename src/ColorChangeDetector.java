import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//takes a thresholded BufferedImage, outputs only color changes
public class ColorChangeDetector {

    //only contains black and white pixels
    private BufferedImage image;
    private BufferedImage totalBounded;
    private BufferedImage horizontalBounded;
    private BufferedImage verticalBounded;

    public ColorChangeDetector(BufferedImage image) {
        this.image = image;
        this.totalBounded = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.verticalBounded = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.horizontalBounded = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void findTotalCrossover() {
        findHorizontalCrossovers();
        findVerticalCrossovers();
        combine();
    }

    public void findVerticalCrossovers() {
        for (int r = 0; r < image.getHeight() - 1; r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                if (image.getRGB(c, r) != image.getRGB(c, r + 1))
                    verticalBounded.setRGB(c, r, Color.WHITE.getRGB());
            }
        }
    }

    public void findHorizontalCrossovers() {
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth() - 1; c++) {
                if (image.getRGB(c, r) != image.getRGB(c + 1, r))
                    horizontalBounded.setRGB(c, r, Color.WHITE.getRGB());
            }
        }
    }

    private void combine() {
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                if (verticalBounded.getRGB(c, r) == Color.WHITE.getRGB() ||
                    horizontalBounded.getRGB(c, r) == Color.WHITE.getRGB()) {
                        totalBounded.setRGB(c, r, Color.WHITE.getRGB());
                }
            }
        }
    }

    public void display(BufferedImage image) {
        JFrame frame = new JFrame(image.toString());
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }

    public void displayTotal() {
        display(totalBounded);
    }

    public void displayVertical() {
        display(verticalBounded);
    }

    public void displayHorizontal() {
        display(horizontalBounded);
    }
}
