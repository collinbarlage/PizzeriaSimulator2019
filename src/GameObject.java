import javax.swing.*;

/**
 * Represents a square on the board. Each GameObject has an Entity. An Entity
 * is what is on the GameObject: either there is food, a piece of the snake,
 * or it is empty.
 */
abstract class GameObject {

    private int x;
    private int y;

    public GameObject (int x, int y) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = App.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}