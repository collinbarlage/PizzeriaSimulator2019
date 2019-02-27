
public class Pizza extends GameObject {

    public Pizza(int i, int j) {
        name = App.PIZZA;
        //TODO: update dimensions of pizza
        init(i , j , 420, 420);
    }

    public void addTopping(String name) {
        if(sprites.size() == 1 && !sprites.elementAt(0).name.equals(App.DOUGH))
            return; // did not start with sauce
        for(Sprite s : sprites) {
            if(s.name.equals(name)) return; // user trying to add duplicate toppings
        }
        System.out.println("adding topping");
        Sprite topping = new Sprite(name);
        sprites.add(topping);
    }

}
