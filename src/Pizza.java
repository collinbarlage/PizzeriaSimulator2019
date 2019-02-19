import java.awt.*;

public class Pizza extends GameObject {
    public Pizza(int i, int j) {
        init(i, j, App.PIZZA);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x+5, y+5, null);
    }
}
