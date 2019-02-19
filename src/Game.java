import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Represents the environment where the Snake moves a food spawns.
 * <br/>
 * There are some special rules as to how the Snake can move. If the Snake's size
 * is 1, it can move in any direction. If the Snake's size is greater than 1, it
 * cannot move 180 degrees. Example: if the Snake is moving right, it cannot
 * immediately change its direction to left because it would run into itself.
 */
class Game  {
    static Color backgroundColor  = new Color(53, 53, 53);

    private Vector<GameObject> gameObjects = new Vector<>();
    private int score = 0;

    Game () {
        update();
    }

    void update () {
    }


    private void exit () {
        System.out.println("Final Score: " + getScore());
        System.exit(0);
    }

    int getScore () {
        return score;
    }


    void paint (Graphics graphics) {

        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintObjects(g);
    }

    private void paintObjects (Graphics2D g) {
        for (GameObject obj : gameObjects)
        {
            obj.draw(g);
        }

        //g.setColor(foodColor);
        //g.fillRoundRect(x + 1, y + 1, SQUARE_SIZE - 2,
                //SQUARE_SIZE - 2, corner, corner);
    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

}