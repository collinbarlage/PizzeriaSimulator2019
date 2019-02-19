import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;

abstract class GameObject {

    public boolean active;
    public boolean clickable;

    protected int x, y, w, h;
    protected BufferedImage image;

    public GameObject () {
    }

    protected void init(int i, int j, String file) {
        System.out.println(file);
        File source = new File(file);
        x = i;
        y = j;
        clickable = true;
        active = false;
        try {
            image = ImageIO.read(source);
            w = image.getWidth();
            h = image.getHeight();
        } catch (IOException e) {
            System.out.println("Image could not be read " + source);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x+5, y+5, null);
    }

    public void click(int i, int j, Game game) {
        if (clickable && i < x + w && i > x && j < y + h && j > y) {
            active = true;
            action(game);
        }
    }

    private  void action(Game game) {
        //abstract
    }
}