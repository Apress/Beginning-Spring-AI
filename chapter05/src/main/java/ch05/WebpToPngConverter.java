package ch05;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebpToPngConverter {

    public static void convertWebpToPng(BufferedImage webpImage, String pngPath) {
        try {
            ImageIO.write(webpImage, "png", new File(pngPath));

            System.out.println("Conversion complete: " + pngPath);
        } catch (IOException e) {
            System.err.println("Failed to convert WEBP to PNG: " + e.getMessage());
        }
    }

}
