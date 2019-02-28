import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class Customer extends GameObject {

    private int numCustomers = 5; //TODO: More peeps

    private int coordinates[] = {50, 250, 450, 650, 850};

    public Customer(int x) {
        int spriteIndex = App.getRandomInt(1, numCustomers);
        init(coordinates[x], 50, App.CUSTOMER.replace(".png", spriteIndex + ".png"));
    }

    public void action(Game game) {
    }




}
