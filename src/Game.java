import java.awt.*;
import java.text.DecimalFormat;
import java.util.Vector;

class Game  {
    static Color backgroundColor  = new Color(53, 53, 53);

    private GameObject objectToAdd;
    private Vector<GameObject> gameObjects = new Vector<>();
    private Vector<GameObject> gameObjectsBackground = new Vector<>();
    private Vector<GameObject> gameObjectsMidground = new Vector<>();

    private double score = 0;
    private int time = 0;
    private int timeLimit = 60*5;

    public Customer[] customers = new Customer[4];

    public Pizza activeZa;

    private Pizza tray;
    private Oven oven;

    Game () {

        gameObjectsBackground.add(new GameImage(App.TITLE));

        oven = new Oven();

        update();
    }

    public void start() {
        gameObjects.clear();

        gameObjectsBackground.add(new GameImage(App.BACKGROUND));
        addGameObject(new GameImage(App.FOREGROUND));

        addGameObject(new Ingredient(0, 400, App.DOUGH));
        addGameObject(new Ingredient(136, 400, App.SAUCE));
        addGameObject(new Ingredient(323, 400, App.CHEESE));
        addGameObject(new Ingredient(464, 400, App.PEPPER));
        addGameObject(new Ingredient(464, 518, App.SHROOM));

        newPizzaTray();
    }

    public void applyIngredient(Ingredient ingredient) {
        tray.addTopping(ingredient.name.replace(".png", "pizza.png"));
    }

    public void newPizzaTray() {
        tray = new Pizza(570, 463);
        addGameObject(tray);
    }

    void update () {
        time++;
        oven.update();

        if(time%15 == 0) {
            switch (time) {
                case 3*15: generateCustomer();
                    break;
                case 10*15: generateCustomer();
                    break;
                case 15*15: generateCustomer();
                    break;
                case 20*15: generateCustomer();
                    break;
                case 24*15: generateCustomer();
                    break;
                case 28*15: generateCustomer();
                    break;
                case 33*15: generateCustomer();
                    break;
                case 35*15: generateCustomer();
                    break;
                case 40*15: generateCustomer();
                    break;
                case 45*15: generateCustomer();
                    break;
                case 50*15: generateCustomer();
                    break;
            }
        }
    }

    private void generateCustomer() {
        Vector<Integer> openSpaces = new Vector<>();

        for (int i = 0; i < 4; i++) {
            if(customers[i] == null) openSpaces.add(i);
        }

        if(openSpaces.size() > 0) {
            int space = openSpaces.elementAt(App.getRandomInt(0, openSpaces.size()-1));
            Customer c = new Customer(space);
            customers[space] = c;
            queueGameObject(c);
        }
    }

    public String getLineUp() {
        //TODO: no two customers at the same time

        Vector<String> lineUp = new Vector<>();
        return "blazeit";
    }

    public void removePizza(int index) {
        oven.removePizza(index);
    }


    public void click (int x, int y) {
        if(activeZa != null && oven.click(x, y)) oven.action(this);
        for (GameObject obj : gameObjects) clickObject(obj, x, y);
        for (GameObject obj : gameObjectsMidground) clickObject(obj, x, y);
    }

    private void clickObject(GameObject obj, int x, int y) {
        obj.active = false;
        if(obj.click(x, y)) obj.action(this);
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
        if(objectToAdd != null) {
            gameObjectsMidground.add(objectToAdd);
            objectToAdd = null;
        }

        for (GameObject obj : gameObjectsBackground)
            obj.draw(g);
        for (GameObject obj : gameObjectsMidground)
            obj.draw(g);
        for (GameObject obj : gameObjects)
            obj.draw(g);

        if(oven != null) oven.draw(g);

        g.setFont(new Font("sansserif", Font.PLAIN, 24));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Time Until Close: " + getTime(), 5, 25);
        g.setColor(Color.GREEN);
        DecimalFormat df = new DecimalFormat("#.00");
        g.drawString("$" + String.format("%.02f", score), 1120, 25);
    }

    public void queueGameObject(GameObject obj) {
        objectToAdd = obj;
    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    public void addScore(double d) {
        score += d;
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