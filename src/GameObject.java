import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;

class GameObject {

    private int x;
    private int y;
    private BufferedImage image;


    public GameObject (int i, int j, String file) {
        System.out.println(file);
        File source = new File(file);
        x = i;
        y = j;
        try {
            image = ImageIO.read(source);
        } catch (IOException e) {
            System.out.println("Image could not be read "+ source);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x+5, y+5, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    //    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = App.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Yikes Couldn't find file: " + path);
//            return null;
//        }
//    }
}