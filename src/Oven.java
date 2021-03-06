import java.awt.*;

public class Oven extends GameObject {

    private double cookRate = .004;

    private int xCoordinates[] = {833, 988, 912};
    private int yCoordinates[] = {590, 590, 515};

    private Pizza[] slots = new Pizza[3];

    public Oven() {
        name = App.OVEN;
        init(801, 397, 399, 278);
    }

    public void update() { // cook pizzas
        for (int i = 0; i < 3; i++) {
            if(slots[i] != null) slots[i].cook(cookRate);
        }
    }

    public void action(Game game) { // add pizza to oven
        if (getEmptySlot() >= 0 && game.activeZa.inOven < 0) {
            game.activeZa.inOven = getEmptySlot();
            slots[getEmptySlot()] = game.activeZa;

            System.out.println(game.activeZa.sprites.elementAt(0).name);
            if(!game.activeZa.sprites.elementAt(0).name.equals(App.DOUGHPIZZA)) {
                game.activeZa.cookAmount = 1;
            }

            game.activeZa = null;
            game.newPizzaTray();
        }
    }

    private int getEmptySlot() {
        for (int i = 0; i < 3; i++) {
            if (slots[i] == null) return i;
        }
        return -1;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < 3; i++) {
            if(slots[i] != null) {
                slots[i].x = xCoordinates[i];
                slots[i].y = yCoordinates[i];
                slots[i].draw(g);
            }
        }
    }

    public void removePizza(int pizzaIndex) {
        slots[pizzaIndex] = new Pizza(xCoordinates[pizzaIndex], yCoordinates[pizzaIndex]);
        slots[pizzaIndex] = null;
    }
}