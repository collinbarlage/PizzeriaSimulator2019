/**
 * Represents a square on the board. Each GameObject has an Entity. An Entity
 * is what is on the GameObject: either there is food, a piece of the snake,
 * or it is empty.
 */
abstract class GameObject {

    private int x;
    private int y;

    public GameObject (int x, int y) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}