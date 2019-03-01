import java.awt.*;

public class Customer extends GameObject {

    private int numCustomers = 5; //TODO: More peeps

    private int spacing = 250;
    private int coordinates[] = {spacing*0, spacing*1, spacing*2, spacing*3};

    private int spaceIndex;

    private Pizza desiredPizza;
    private Sprite bubble;

    public Customer(int space) {
        spaceIndex = space;
        //int spriteIndex = App.getLineUp(); //TODO
        int spriteIndex = App.getRandomInt(1, numCustomers);
        init(coordinates[space], 0, App.CUSTOMER.replace(".png", spriteIndex + ".png"));

        bubble = new Sprite(App.BUBBLE);

        desiredPizza = new Pizza(x+110,y-43+80);
        desiredPizza.autoGenerate();
    }

    private double receivePizza(Pizza pizza) {
        if(pizza.isSold) return 0;

        //TODO: figure out $$$$$$

//        for(Sprite received: pizza.sprites) {
//        }
        pizza.isSold = true;
        return 4.20;
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
        if(game.activeZa != null && game.activeZa.inOven >= 0) {
            game.addScore(receivePizza(game.activeZa));
            game.removePizza(game.activeZa.inOven);
            game.activeZa.hide = true;

            game.customers[spaceIndex] = null;
            this.hide = true;
        }
    }




}
