import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * PIZZERIA SIMULATOR 2019
 * COLLIN BARLAGE
 * CS 338 - WINTER 2019
 */

public class App extends JFrame {

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


    private GameLoop loop;
    private JPanel gamePanel;

    private boolean running = false;

    private App() {
        this.setJMenuBar(createMenuBar());
//        JButton b = new JButton("START GAME"); TODO: figure this out
//        b.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                startApp();
//            }
//        });
//        this.add(b);
        startApp();

        setWindowProperties();
    }

    private void startApp() {
        InputManager inputManager = new InputManager();
        gamePanel.addMouseListener(inputManager);
        loop = createGameLoop();
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

        JButton b = new JButton("START GAME");
        this.add(b);

        cp.add(loop);

        return loop;
    }

    private void setWindowProperties () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pizzeria Simulator 2019 - Collin Barlage - CS 338");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);// Center window
    }

    private void startGame (GameLoop loop) {
        Thread th = new Thread(loop);
        th.start();
    }

    private class GameLoop extends JPanel implements Runnable {

        private Game game;
        private boolean running = false;

        private GameLoop(Game game) {
            this.game = game;
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
            if(!running) {
                running = true;
                startGame(loop);
            }
            System.out.println("mousePressed at: "+ event.getX() + ", "+ event.getY());
            loop.click(event.getX(), event.getY());

        }

        public void mouseReleased(MouseEvent event) {
            loop.click(event.getX(), event.getY());
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
        menu.addSeparator();

        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);
        menuItem = new JMenuItem("Load");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);


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

        return menuBar;
    }

}
