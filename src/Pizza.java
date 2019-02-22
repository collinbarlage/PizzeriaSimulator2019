
public class Pizza extends GameObject {

    public Pizza(int i, int j) {
        name = App.PIZZA;
        //TODO: update dimensions of pizza
        init(i , j , 420, 420);
    }

    public void addTopping(String name) {
        Sprite topping = new Sprite(name);
        sprites.add(topping);
    }

}
