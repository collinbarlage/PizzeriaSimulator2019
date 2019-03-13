import java.awt.*;
import java.text.DecimalFormat;
import java.util.Vector;

class Game  {
    static Color backgroundColor  = new Color(53, 53, 53);
    private boolean selfDestruct = false;

    private GameObject objectToAdd;
    private Vector<GameObject> gameObjects = new Vector<>();
    private Vector<GameObject> gameObjectsBackground = new Vector<>();
    private Vector<GameObject> gameObjectsMidground = new Vector<>();

    private double score;
    private int time;
    private int timeLimit = 2*60;
    public boolean gameOver = false;

    public Customer[] customers = new Customer[4];
    public Vector<String> lineUp = new Vector<>();
    private int numCustomers = 10; //TODO: More peeps

    public Pizza activeZa;

    private Pizza tray;
    private Oven oven;

    Game () {
        shuffleCustomers();
        oven = new Oven();
        update();
    }

    public void start() {
        time = 0;
        score = 0;

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

        if(time%75 == 0) {
            generateCustomer();
        }

        if(time == timeLimit*15) {
            endGame();
        }
    }

    private void shuffleCustomers() {
        Vector<String> availableCustomers = new Vector<>();
        for(int i=0; i<numCustomers; i++) {
            availableCustomers.add(App.CUSTOMER.replace(".png", i+".png"));
        }

        while (availableCustomers.size() > 0) {
            int r = App.getRandomInt(0, availableCustomers.size()-1);
            lineUp.add(availableCustomers.elementAt(r));
            availableCustomers.remove(r);
        }
    }

    private void generateCustomer() {
        Vector<Integer> openSpaces = new Vector<>();
        for (int i = 0; i < 4; i++) {
            if(customers[i] == null) openSpaces.add(i);
        }
        if(openSpaces.size() > 0) {
            int space = openSpaces.elementAt(App.getRandomInt(0, openSpaces.size()-1));
            Customer c = new Customer(space, lineUp.elementAt(0));
            lineUp.remove(0);
            customers[space] = c;
            queueGameObject(c);
        }
    }

    private void endGame() {
        clearGame();
        gameOver = true;
    }

    public void clearGame() {
        selfDestruct = true;
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
        if(selfDestruct) {
            gameObjectsMidground.clear();
            gameObjects.clear();
            gameObjectsBackground.clear();
            return;
        }

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
        g.drawString("$" + getScore(), 1120, 25);
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

    public String getScore() {
        return String.format("%.02f", score);
    }

    private String timeDigit(int i) {
        if(i<10) return "0"+i;
        else return ""+i;
    }
}