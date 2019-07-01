import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	    try {
            BufferedImage image = ImageIO.read(new File("sketch.jpg"));
            Tracer tracer = new Tracer(image);
            tracer.trace();
            tracer.displayTraced();
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
}
