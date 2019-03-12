import java.util.Vector;

public class Pizza extends GameObject {

    public int inOven = -1;
    public double cookAmount = 0;
    public boolean isSold;
    private String suffix;

    public Pizza(int i, int j) {
        isSold = false;
        suffix = "";
        name = App.PIZZA;
        init(i , j , 130, 75);
    }

    public void addTopping(String name) {
        for(Sprite s : sprites) {
            if(s.name.equals(name)) return; // user trying to add duplicate toppings
        }
        Sprite topping = new Sprite(name);
        sprites.add(topping);
    }

    public void autoGenerate() {
        this.addTopping(App.COOKEDPIZZA);

        int r = App.getRandomInt(1,4);
        if(r == 1) {
            this.addTopping(App.CHEESEPIZZA);
        } else if(r == 2) {
            this.addTopping((App.SAUCEPIZZA));
        } else {
            this.addTopping((App.SAUCEPIZZA));
            this.addTopping((App.CHEESEPIZZA));
        }

        r = App.getRandomInt(1,4);
        if(r == 1) {
            this.addTopping(App.PEPPERPIZZA);
        } else if(r == 2) {
            this.addTopping((App.SHROOMPIZZA));
        } else if (r == 3) {
            this.addTopping(App.PEPPERPIZZA);
            this.addTopping(App.SHROOMPIZZA);
        }
    }

    public void action(Game game) {
        if(sprites.size() > 0) game.activeZa = this;
    }

    public void cook(double amount) {
        cookAmount += amount;
        if(cookAmount > 1) decimatePizza();
        else if(cookAmount > .6) cookSprites(App.BURNTPIZZA);
        else if(cookAmount > .3) cookSprites(App.COOKEDPIZZA);
    }

    private void cookSprites(String level) {
        //TODO
//        if(level.equals(suffix)) return;
//        Vector<Sprite> newSprites = new Vector<>();
//        for(Sprite s: sprites) {
//            newSprites.add(new Sprite(s.name.replace(".png", suffix+".png")));
//        }
//        sprites = newSprites;
//        suffix = level;

        if(sprites.elementAt(0).name.equals(level)) return;
        sprites.removeElementAt(0);
        sprites.add(0, new Sprite(level));
    }

    private void decimatePizza() {
        if(sprites.elementAt(0).name.equals(App.ASH)) return;
        sprites.removeAllElements();
        sprites.add(new Sprite(App.ASH));
    }

    public boolean hasTopping(Sprite desired) {
        for (Sprite received : sprites) {
            if (received.name.equals(desired.name)) return true;
        }
        return false;
    }
}
