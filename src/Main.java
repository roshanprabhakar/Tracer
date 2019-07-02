import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	    try {
            BufferedImage image = ImageIO.read(new File("selfie.jpg"));

            //finds straight lines
            Tracer tracer = new Tracer(image, 100);
            tracer.trace();
//            tracer.displayTraced();

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
