import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;

class GameObject {

    public boolean clickable;

    private int x;
    private int y;
    private int w;
    private int h;
    private BufferedImage image;


    public GameObject (int i, int j, String file) {
        System.out.println(file);
        File source = new File(file);
        x = i;
        y = j;
        System.out.println(x+", "+y);
        try {
            image = ImageIO.read(source);
        } catch (IOException e) {
            System.out.println("Image could not be read "+ source);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x+5, y+5, null);
    }

    public void click(int i, int j) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}