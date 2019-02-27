import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;

class Sprite {
    public String name;
    private BufferedImage image;
    private int xOffset, yOffset;

    public Sprite(String file) {
        name = file;
        System.out.println(name);
        File source = new File(name);
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