import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * PIZZERIA SIMULATOR 2019
 * COLLIN BARLAGE
 * CS 338 - WINTER 2019
 */

public class App extends JFrame implements ActionListener {

    public Game game;
    private GameLoop loop;
    private JFrame tutMenu;
    private JPanel mainMenu;
    private JPanel gamePanel;
    private Thread gameThread;

    private boolean running = true;
    private boolean paused = false;
    private boolean stop = false;

    public static String PIZZA = "PIZZA";
    public static String OVEN = "OVEN";

    public static String TITLE = "./images/title.png";
    public static String BACKGROUND = "./images/pizzeria.png";
    public static String FOREGROUND = "./images/pizzeriaforeground.png";

    public static String CUSTOMER = "./images/customer.png";
    public static String BUBBLE = "./images/bubble.png";

    public static String DOUGH = "./images/dough.png";
    public static String SAUCE = "./images/sauce.png";
    public static String CHEESE = "./images/cheese.png";
    public static String SHROOM = "./images/shroom.png";
    public static String PEPPER = "./images/pepper.png";

    public static String DOUGHPIZZA = "./images/doughpizza.png";
    public static String COOKEDPIZZA = "./images/cookedpizza.png";
    public static String BURNTPIZZA = "./images/burntpizza.png";
    public static String ASH = "./images/ash.png";

    public static String SAUCEPIZZA = "./images/saucepizza.png";
    public static String CHEESEPIZZA = "./images/cheesepizza.png";
    public static String SHROOMPIZZA = "./images/shroompizza.png";
    public static String PEPPERPIZZA = "./images/pepperpizza.png";


    private App() {
        this.setJMenuBar(createMenuBar());
        showMainMenu();
        setWindowProperties();
    }

    private void startApp() {
        InputManager inputManager = new InputManager();
        gamePanel.addMouseListener(inputManager);
        loop = createGameLoop();
        pack();

    }

    private void setWindowProperties () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pizzeria Simulator 2019 - Collin Barlage - CS 338");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);// Center window
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }

    private GameLoop createGameLoop () {
        
        Container cp = getContentPane();
        Game game = new Game();
        GameLoop loop = new GameLoop(game);

        int canvasWidth = 1210;
        int canvasHeight = 685;
        loop.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        cp.add(loop);

        return loop;
    }

    private void startGame (GameLoop loop) {
        gameThread = new Thread(loop);
        gameThread.start();
    }

    private class GameLoop extends JPanel implements Runnable {

        private GameLoop(Game g) {
            game = g;
            gamePanel.setOpaque(false);
            this.add(gamePanel);



        }

        private void click(int x, int y) {
            game.click(x,y);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            setBackground(Game.backgroundColor);
            game.paint(graphics);
        }

        public void run () {

            long lastTime = System.nanoTime();
            double elapsedTime = 0.0;
            double FPS = 15.0;

            game.start();

            // Game loop
            while (true) {
                if (!paused) {
                    if (stop) {
                        stop = false;
                        //restart game
                        System.out.println("Repeat!");
                        game.clearGame();
                        gameThread.interrupt();
                        startApp();
                        startGame(loop);
                        break;
                    }
                    long now = System.nanoTime();
                    elapsedTime += ((now - lastTime) / 1_000_000_000.0) * FPS;
                    lastTime = System.nanoTime();
                    if (elapsedTime >= 1) {
                        game.update();
                        elapsedTime--;
                    }
                    sleep();
                    repaint();
                }
            }
        }
    }

    private void sleep () {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // Input Manager
    private class InputManager implements MouseListener {
        public void mousePressed(MouseEvent event) {
            if(!paused) {
                System.out.println("mousePressed at: "+ event.getX() + ", "+ event.getY());
                loop.click(event.getX(), event.getY());
            }

        }

        public void mouseReleased(MouseEvent event) {
            if(!paused) {
                loop.click(event.getX(), event.getY());
            }
        }

        public void mouseClicked(MouseEvent event) {
        }
        public void mouseEntered(MouseEvent event) {
        }
        public void mouseExited(MouseEvent event) {
        }
    }


    // Menu File
    private JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("New Game",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menu.add(menuItem);
        menuItem.addActionListener(this);
        menu.addSeparator();

        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);

        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);
        menuItem.addActionListener(this);


        //a submenu
        menu.addSeparator();
        submenu = new JMenu("Preferences");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("Graphics");
        submenu.add(menuItem);

        menuItem = new JMenuItem("Mods");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        menuItem = new JMenuItem("Tutorial");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);
        menuItem.addActionListener(this);


        return menuBar;
    }

    //main menu event system
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        System.out.println(event);

        if(event.equals("New Game") || event.equals("Start Game")) menuNewGame();
        if(event.equals("Quit") || event.equals("Exit")) System.exit(1);
        if(event.equals("Tutorial") || event.equals("How To Play")) menuTutorial();

    }

    private void menuNewGame() {
        if(game != null) {
            stop = true;
        } else {
            //first run
            startApp();
            startGame(loop);
            this.remove(mainMenu);
        }
    }

    //Main menu
    private void showMainMenu() {
        mainMenu = new JPanel();
        mainMenu.setPreferredSize(new Dimension(1210, 685));

        //buttons
        JPanel buttons = new JPanel();
        buttons.setBorder(new EmptyBorder(10,10,10,10));
        JButton b = new JButton("Start Game");
        b.addActionListener(this);
        buttons.add(b);

        b = new JButton("How To Play");
        b.addActionListener(this);
        buttons.add(b);

        b = new JButton("Quit");
        b.addActionListener(this);
        buttons.add(b);

        mainMenu.add(buttons);

        this.add(mainMenu);
    }

    private void menuTutorial() {
        JOptionPane.showConfirmDialog(this,
                "Eggs are not supposed to be green.",
                "A plain message",
                JOptionPane.PLAIN_MESSAGE);
    }

}
