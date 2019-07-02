import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	    try {
            BufferedImage image = ImageIO.read(new File("selfie.jpg"));

            //automatically determines threshold value
            int[][] pixels = Utils.getBWGrid(image);
            Otsu otsu = new Otsu(pixels);

            //finds straight lines
            Tracer tracer = new Tracer(image, otsu.getThreshold());
            tracer.trace();
            tracer.displayTraced();

            //eliminates color repetition to enforce 1px thick lines
            ColorChangeDetector detector = new ColorChangeDetector(tracer.getTraced());
            detector.findTotalCrossover();

            //displays total output
            detector.displayTotal();

        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
}
