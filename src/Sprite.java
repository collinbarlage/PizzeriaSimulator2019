import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;

class Sprite {
    private BufferedImage image;
    private int xOffset, yOffset;

    public Sprite(String file) {
        System.out.println(file);
        File source = new File(file);
        xOffset = yOffset = 0;
        try {
            image = ImageIO.read(source);
        } catch (IOException e) {
            System.out.println("Image could not be read " + source);
        }
    }

    public void draw(int x, int y, Graphics g) {
        g.drawImage(image, x+5+ xOffset, y+5+yOffset, null);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public void setOffset(int x, int y) {
        xOffset = x;
        yOffset = y;
    }
}