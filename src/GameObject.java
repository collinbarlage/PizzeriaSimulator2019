import java.awt.*;
import java.io.*;

/**
 * Represents a square on the board. Each GameObject has an Entity. An Entity
 * is what is on the GameObject: either there is food, a piece of the snake,
 * or it is empty.
 */
class GameObject {

    private int x;
    private int y;
    private GameImage image;

    public GameObject (int x, int y, String file) {
        System.out.println(file);
        image = new GameImage(new File("C:/Users/Owner/Desktop/CS 338/PizzeriaSimulator2019/images/homer.gif")); // load file
    }

    public void draw(Graphics g) {
        image.paint(g);
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