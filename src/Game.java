import java.awt.*;
import java.util.Vector;

class Game  {
    static Color backgroundColor  = new Color(53, 53, 53);

    private Vector<GameObject> gameObjects = new Vector<>();
    private Vector<GameObject> gameObjectsBackground = new Vector<>();
    private Vector<GameObject> gameObjectsMidground = new Vector<>();

    private int score = 0;
    private int time = 0;
    private int timeLimit = 60*5;

    private Pizza tray;

    Game () {

        //TODO: Add start game picture

        //addGameObject(new Ingredient(50, 400, App.DOUGH));
        //addGameObject(new Ingredient(50, 400, App.SAUCE));
        addGameObject(new Ingredient(50, 400, App.CHEESE));
        //addGameObject(new Ingredient(50, 400, App.SHROOM));
        //addGameObject(new Ingredient(50, 400, App.PEPPER));

        tray = new Pizza(500, 400);
        addGameObject(tray);




        update();
    }

    public void applyIngredient(Ingredient ingredient) {
        tray.addTopping(ingredient.name.replace(".png", "pizza.png"));
    }

    void update () {
        time++;

    }

    void click (int x, int y) {
        //gameObjects.add(new GameObject(x,y,"C:/Users/Owner/Desktop/CS 338/PizzeriaSimulator2019/images/homer.gif"));
        for (GameObject obj : gameObjects)
        {
            //.active = false;
            if(obj.click(x, y)) obj.action(this);
        }
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
        g.setFont(new Font("sansserif", Font.PLAIN, 50));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Time Until Close: " + getTime(), 1, 60);


        for (GameObject obj : gameObjectsBackground)
            obj.draw(g);
        for (GameObject obj : gameObjectsMidground)
            obj.draw(g);
        for (GameObject obj : gameObjects)
            obj.draw(g);

    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    private String getTime() {
        double seconds = (double)time/15;
        seconds = timeLimit - seconds;
        double minutes = seconds/60;
        int min = (int)Math.floor(minutes);
        int sec = (int)Math.round(minutes*60 - (double)min*60);
        if(sec == 60) {
            sec = 0;
            min++;
        }
        return timeDigit(min)+":"+timeDigit(sec);
    }

    private String timeDigit(int i) {
        if(i<10) return "0"+i;
        else return ""+i;
    }
}