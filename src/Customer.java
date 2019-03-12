import java.awt.*;

public class Customer extends GameObject {


    private int spacing = 250;
    private int coordinates[] = {spacing*0, spacing*1, spacing*2, spacing*3};

    private int spaceIndex;

    private Pizza desiredPizza;
    private Sprite bubble;

    public Customer(int space, String randomSprite) {
        spaceIndex = space;
        init(coordinates[space], 0, randomSprite);

        bubble = new Sprite(App.BUBBLE);
        desiredPizza = new Pizza(x+110,y-43+80);
        desiredPizza.autoGenerate();
    }

    private double receivePizza(Pizza pizza) {
        if(pizza.isSold) return 0;
        double total = 0;
        int i = 0;
        int size = pizza.sprites.size();

        if(pizza.cookAmount > 1) return 0;
        for(Sprite desired : desiredPizza.sprites ) {
            if(i < size && pizza.hasTopping(desired)) {
                total += getBaseCost(desired.name);
            }
            i++;
        }
        if(desiredPizza.sprites.size() < pizza.sprites.size()) {
            total -= desiredPizza.sprites.size() - pizza.sprites.size();
        }
        pizza.isSold = true;
        return total;
    }



    private double getBaseCost(String item) {
        if(item.equals(App.COOKEDPIZZA)) {
            return 3.0;
        } else if(item.equals(cooked(App.BURNTPIZZA))) {
            return 1.5;
        } else if(item.equals(cooked(App.CHEESEPIZZA)) || item.equals(cooked(App.SAUCEPIZZA))) {
            return 1.75;
        } else if(item.equals(cooked(App.SHROOMPIZZA)) || item.equals(cooked(App.PEPPERPIZZA))) {
            return 1.0;
        } else if(item.equals(burnt(App.CHEESEPIZZA)) || item.equals(burnt(App.SAUCEPIZZA))) {
            return .35;
        } else if(item.equals(burnt(App.SHROOMPIZZA)) || item.equals(burnt(App.PEPPERPIZZA))) {
            return .15;
        } else {
            return .1;
        }
    }

    public String cooked(String s) {
        return s.replace(".png", "_cooked.png");
    }

    public String burnt(String s) {
        return s.replace(".png", "_burnt.png");
    }

    public void draw(Graphics g) {
        if(!hide) {
            for (Sprite s : sprites) {
                s.draw(x, y, g);
            }
            bubble.draw(x + 75, y +20, g);
            desiredPizza.draw(g);
        }
    }

    public void action(Game game) {
        if(game.activeZa != null && game.activeZa.inOven >= 0 && game.activeZa.sprites.size() > 0) {
            game.addScore(receivePizza(game.activeZa));
            game.removePizza(game.activeZa.inOven);
            game.activeZa.hide = true;
            game.customers[spaceIndex] = null;
            if(!this.hide) game.lineUp.add(name);
            this.hide = true;
        }
    }


}
