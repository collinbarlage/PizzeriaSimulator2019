
public class Pizza extends GameObject {

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

}
