
public class Pizza extends GameObject {

    public boolean isInOven = false;
    public double cookAmount = 0;

    public Pizza(int i, int j) {
        name = App.PIZZA;
        init(i , j , 130, 75);
    }

    public void addTopping(String name) {
        for(Sprite s : sprites) {
            if(s.name.equals(name)) return; // user trying to add duplicate toppings
        }
        System.out.println("adding topping");
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
        else if(cookAmount > .6) changeCrust(App.BURNTPIZZA);
        else if(cookAmount > .3) changeCrust(App.COOKEDPIZZA);
    }

    private void changeCrust(String crust) {
        if(sprites.elementAt(0).name.equals(crust)) return;
        sprites.removeElementAt(0);
        sprites.add(0, new Sprite(crust));
    }

    private void decimatePizza() {
        if(sprites.elementAt(0).name.equals(App.ASH)) return;
        sprites.removeAllElements();
        sprites.add(new Sprite(App.ASH));
    }
}
