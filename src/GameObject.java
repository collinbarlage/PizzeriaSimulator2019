import java.awt.*;
import java.util.Vector;

abstract class GameObject {

    protected int x, y, w, h;
    public boolean active;
    public boolean clickable;
    String name;

    protected Vector<Sprite> sprites;

    public GameObject () {
    }

    public void init(int i, int j, int width, int height) {
        sprites = new Vector<>();
        clickable = true;
        active = false;
        w = width;
        h = height;
        x = i;
        y = j;
    }

    public void init(int i, int j, String s) {
        name = s;
        Sprite sprite = new Sprite(name);
        this.init(i, j, sprite.getWidth(), sprite.getHeight());
        sprites.add(sprite);
    }

    public void addSprite(String s) {
        Sprite sprite = new Sprite(s);
        sprites.add(sprite);
    }

    public void addSprite(String s, int sX, int sY) {
        Sprite sprite = new Sprite(s);
        sprite.setOffset(sX, sY);
        sprites.add(sprite);
    }

    public void draw(Graphics g) {
        for(Sprite s: sprites) {
            s.draw(x, y, g);
        }
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