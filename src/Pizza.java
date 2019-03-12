import java.util.Vector;

public class Pizza extends GameObject {

    public int inOven = -1;
    public double cookAmount = 0;
    public boolean isSold;
    public boolean isCooked;
    private String suffix;
    private String oldSuffix;

    public Pizza(int i, int j) {
        isCooked = false;
        isSold = false;
        suffix = oldSuffix = "";
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
        this.addTopping(cookString(App.DOUGHPIZZA));

        int r = App.getRandomInt(1,4);
        if(r == 1) {
            this.addTopping(cookString(App.CHEESEPIZZA));
        } else if(r == 2) {
            this.addTopping((cookString(App.SAUCEPIZZA)));
        } else {
            this.addTopping((cookString(App.SAUCEPIZZA)));
            this.addTopping((cookString(App.CHEESEPIZZA)));
        }

        r = App.getRandomInt(1,4);
        if(r == 1) {
            this.addTopping(cookString(App.PEPPERPIZZA));
        } else if(r == 2) {
            this.addTopping((cookString(App.SHROOMPIZZA)));
        } else if (r == 3) {
            this.addTopping(cookString(App.PEPPERPIZZA));
            this.addTopping(cookString(App.SHROOMPIZZA));
        }

    }

    public String cookString(String s) {
        return s.replace(".png", "_cooked.png");
    }

    public void action(Game game) {
        if(sprites.size() > 0) game.activeZa = this;
    }

    public void cook(double amount) {
        cookAmount += amount;
        if(cookAmount > 1) decimatePizza();
        else if(cookAmount > .6) cookSprites("_burnt");
        else if(cookAmount > .3) cookSprites("_cooked");
    }

    private void cookSprites(String level) {
        if(level.equals(suffix)) return;
        Vector<Sprite> newSprites = new Vector<>();
        suffix = level;
        System.out.println("lvvl:"+suffix);
        for(Sprite s: sprites) {
            newSprites.add(new Sprite(s.name.replace(oldSuffix+".png", suffix+".png")));
        }
        sprites.clear();
        for(Sprite s: newSprites) {
            sprites.add(s);
        }
        oldSuffix = suffix;
    }

    private void decimatePizza() {
        if(sprites.elementAt(0).name.equals(App.ASH)) return;
        sprites.removeAllElements();
        sprites.add(new Sprite(App.ASH));
    }

    public boolean hasTopping(Sprite desired) {
        for (Sprite received : sprites) {
            if (received.name.equals(desired.name)) return true;
            if (received.name.replace("_burnt","_cooked").equals(desired.name)) return true;
        }
        return false;
    }
}
