import java.awt.*;

public class Customer extends GameObject {

    private int numCustomers = 5; //TODO: More peeps

    private int spacing = 250;
    private int coordinates[] = {spacing*0, spacing*1, spacing*2, spacing*3};

    private Pizza desiredPizza;
    private Sprite bubble;

    public Customer(int space) {
        int spriteIndex = App.getRandomInt(1, numCustomers);
        init(coordinates[space], 80, App.CUSTOMER.replace(".png", spriteIndex + ".png"));

        bubble = new Sprite(App.BUBBLE);

        desiredPizza = new Pizza(x+110,y-43);
        desiredPizza.autoGenerate();
    }

    private double receivePizza(Pizza pizza) {
        if(pizza.isSold) return 0;

        //TODO:

//        for(Sprite received: pizza.sprites) {
//        }
        pizza.isSold = true;
        return 4.20;
    }

    public void draw(Graphics g) {
        for(Sprite s: sprites) {
            s.draw(x, y, g);
        }

        bubble.draw(x+75,y-60, g);
        desiredPizza.draw(g);
    }

    public void action(Game game) {
        if(game.activeZa != null && game.activeZa.inOven >= 0) {
            game.addScore(receivePizza(game.activeZa));
            game.removePizza(game.activeZa.inOven);
            game.activeZa.hide = true;


        }
    }




}
