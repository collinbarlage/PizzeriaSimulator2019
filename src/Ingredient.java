public class Ingredient extends GameObject {

    public Ingredient(int i, int j, String s) {
        name = s;
        init(i , j , s);
    }

    public void action(Game g) {
        g.applyIngredient(this);
    }
}
