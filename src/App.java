import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * PIZZERIA SIMULATOR 2019
 * COLLIN BARLAGE
 * CS 338 - WINTER 2019
 */
public class App extends JFrame {

    private GameLoop loop;

    private App() {
        loop = createGameLoop();
        setWindowProperties();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new App());

    }

    private GameLoop createGameLoop () {
        
        Container cp = getContentPane();
        Game game = new Game();
        GameLoop loop = new GameLoop(game);

        int canvasWidth = Game.SQUARE_SIZE * Game.BOARD_COLUMNS;
        int canvasHeight = Game.SQUARE_SIZE * Game.BOARD_ROWS;
        loop.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        addKeyListener(new InputManager());

        this.setJMenuBar(createMenuBar());
        cp.add(loop);

        return loop;
    }

    private void setWindowProperties () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pizzeria Simulator 2019 ");
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
            game.addGameObject(new GameObject(0, 0, "/images/homer.gif"));

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

            // Game loop.
            while (true) {

                long now = System.nanoTime();
                elapsedTime += ((now - lastTime) / 1_000_000_000.0) * FPS;
                lastTime = System.nanoTime();

                if (elapsedTime >= 1) {
                    game.update();
                    setTitle("Pizzeria Simulator 2019 " + game.getScore());
                    elapsedTime--;

                }
                sleep();

                repaint();
            }
        }
    }

    /**
     * Sleep for 10 milliseconds.
     */
    private void sleep () {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private class InputManager extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent keyEvent) {

            if (!loop.running && keyEvent.getKeyCode() != KeyEvent.VK_F1 && keyEvent.getKeyCode() != KeyEvent.VK_F2 && keyEvent.getKeyCode() != KeyEvent.VK_F3 && keyEvent.getKeyCode() != KeyEvent.VK_F4 && keyEvent.getKeyCode() != KeyEvent.VK_F5) {
                startGame(loop);
                loop.running = true;
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                //loop.game.directionLeft();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                //loop.game.directionRight();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                //loop.game.directionUp();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                //loop.game.directionDown();
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_F1) {
                repaint();
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_F2) {
                repaint();
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_F3) {
                repaint();
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_F4) {
                repaint();
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_F5) {
                repaint();
            }
        }

    }


    public JMenuBar createMenuBar() {
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
        menuItem = new JMenuItem("Save",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
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

        return menuBar;
    }


}