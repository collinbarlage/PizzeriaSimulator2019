import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;

class Sprite {
    public String name;
    private BufferedImage image;
    private int imageWidth, imageHeight;
    private int xOffset, yOffset;

    public Sprite(String file) {
        name = file;
        File source = new File(name);
        xOffset = yOffset = 0;
        try {
            image = ImageIO.read(source);
            imageWidth = image.getWidth();
            imageHeight = image.getHeight();
        } catch (IOException e) {
            System.out.println("Image could not be read " + source);
        }
    }

    public void draw(int x, int y, Graphics g) {
        g.drawImage(image, x+5+ xOffset, y+5+yOffset, null);
    }

    public int getWidth() { return imageWidth; }

    public int getHeight() {
        return imageHeight;
    }

    public void setOffset(int x, int y) {
        xOffset = x;
        yOffset = y;
    }
}