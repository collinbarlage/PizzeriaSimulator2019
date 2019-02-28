import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class Customer extends GameObject {

    private int numCustomers = 5; //TODO: More peeps

    private int spacing = 250;
    private int coordinates[] = {spacing*0, spacing*1, spacing*2, spacing*3};

    public Customer(int x) {
        int spriteIndex = App.getRandomInt(1, numCustomers);
        init(coordinates[x], 80, App.CUSTOMER.replace(".png", spriteIndex + ".png"));
    }

    public void action(Game game) {
    }




}
