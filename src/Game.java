import java.awt.*;
import java.util.Random;
import java.util.Vector;

class Game  {
    static Color backgroundColor  = new Color(53, 53, 53);

    private Vector<GameObject> gameObjects = new Vector<>();
    private int score = 0;

    Game () {
        update();
    }

    void update () {
    }

    void click (int x, int y) {
        gameObjects.add(new GameObject(x,y,"C:/Users/Owner/Desktop/CS 338/PizzeriaSimulator2019/images/homer.gif"));
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

    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }
}