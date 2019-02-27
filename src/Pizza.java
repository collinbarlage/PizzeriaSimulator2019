
public class Pizza extends GameObject {

    public boolean isInOven = false;
    public double cookAmount = 0;

    public Pizza(int i, int j) {
        name = App.PIZZA;
        init(i , j , 130, 75);
    }

    public void addTopping(String name) {
        if(sprites.size() == 1 && !sprites.elementAt(0).name.equals(App.DOUGHPIZZA))
            return; // did not start with sauce
        for(Sprite s : sprites) {
            if(s.name.equals(name)) return; // user trying to add duplicate toppings
        }
        System.out.println("adding topping");
        Sprite topping = new Sprite(name);
        sprites.add(topping);
    }

    public void autoGenerate() {
        this.addTopping(App.DOUGHPIZZA);

        //TODO:
        //cheese
        //sauce
        //cheese + sauce

        //pep
        //shroom
    }

    public void action(Game game) {
        game.activeZa = this;
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
