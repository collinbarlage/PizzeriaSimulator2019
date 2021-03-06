import java.awt.*;
import java.util.Vector;

abstract class GameObject {

    public int x, y, w, h;
    public boolean active;
    public boolean clickable;
    public boolean hide;
    public String name;

    private String suffex;

    protected Vector<Sprite> sprites;

    public GameObject () {
    }

    public void init(int i, int j, int width, int height) {
        sprites = new Vector<>();
        clickable = true;
        active = false;
        hide = false;
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
        Vector<Sprite> drawSprites = (Vector) sprites.clone();
        for(Sprite s: drawSprites) {
            if(!hide) s.draw(x, y, g);
        }
        if(active && name.equals(App.PIZZA)) {
            g.setColor(Color.CYAN);
            g.drawRoundRect(x,y,w+8,h+10,15,15);
        }
    }

    public boolean click(int i, int j) {
        if (clickable && i < x + w && i > x && j < y + h && j > y) {
            active = true;
            return true;
        }
        return  false;
    }

    public void action(Game game) {
        //abstract

    }
}