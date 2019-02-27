import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;

class Game  {
    static Color backgroundColor  = new Color(53, 53, 53);

    private Vector<GameObject> gameObjects = new Vector<>();
    private Vector<GameObject> gameObjectsBackground = new Vector<>();
    private Vector<GameObject> gameObjectsMidground = new Vector<>();

    private double score = 0;
    private int time = 0;
    private int timeLimit = 60*5;

    private Pizza tray;

    Game () {

        addGameObject(new GameImage(App.TITLE));




        update();
    }

    public void start() {
        gameObjects.clear();

        gameObjectsBackground.add(new GameImage(App.BACKGROUND));
        addGameObject(new GameImage(App.FOREGROUND));

        addGameObject(new Ingredient(0, 400, App.DOUGH));
        addGameObject(new Ingredient(136, 400, App.SAUCE));
        addGameObject(new Ingredient(323, 400, App.CHEESE));
        addGameObject(new Ingredient(464, 400, App.SHROOM));
        addGameObject(new Ingredient(464, 518, App.PEPPER));

        tray = new Pizza(586, 473);
        addGameObject(tray);
    }

    public void applyIngredient(Ingredient ingredient) {
        tray.addTopping(ingredient.name.replace(".png", "pizza.png"));
    }

    void update () {
        time++;

    }

    void click (int x, int y) {

        for (GameObject obj : gameObjects)
        {
            //.active = false;
            if(obj.click(x, y)) obj.action(this);
        }
    }


    private void exit () {
        System.out.println("Final Score: " + score);
        System.exit(0);
    }

    void paint (Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintObjects(g);
    }

    private void paintObjects (Graphics2D g) {
        g.setFont(new Font("sansserif", Font.PLAIN, 24));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Time Until Close: " + getTime(), 5, 25);
        g.setColor(Color.GREEN);
        DecimalFormat df = new DecimalFormat("#.00");
        g.drawString("$" + String.format("%.02f", score), 1120, 25);


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