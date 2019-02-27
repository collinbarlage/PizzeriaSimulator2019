import java.awt.*;

public class Oven extends GameObject {

    private double cookRate = .025;

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
        if (getEmptySlot() >= 0 && !game.activeZa.isInOven) {
            game.activeZa.isInOven = true;
            slots[getEmptySlot()] = game.activeZa;
            game.activeZa = null;
            game.newPizza();
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
}