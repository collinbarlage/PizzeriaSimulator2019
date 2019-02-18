
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Runs a game of Snake.
 * Uses the arrow keys to move the Snake.
 * Click F1, F2, F3, F4 or F5 to change the color.
 */
public class App extends JFrame {

    private GameLoop loop;

    private App() {
        loop = createGameLoop();
        setWindowProperties();
    }

    private GameLoop createGameLoop () {
        
        Container cp = getContentPane();
        Game game = new Game();
        GameLoop loop = new GameLoop(game);

        int canvasWidth = Game.SQUARE_SIZE * Game.BOARD_COLUMNS;
        int canvasHeight = Game.SQUARE_SIZE * Game.BOARD_ROWS;
        loop.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        addKeyListener(new InputManager());

        cp.add(loop);

        return loop;
    }

    private void setWindowProperties () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake - Score: 0");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);// Center window
    }

    private void startGame (GameLoop loop) {
        Thread th = new Thread(loop);
        th.start();
    }

    /**
     * Contains the game loop.
     */
    private class GameLoop extends JPanel implements Runnable {

        private Game game;
        private boolean running = false;

        private GameLoop(Game game) {
            this.game = game;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            // Ensures that it will run smoothly on Linux.
            if (System.getProperty("os.name").equals("Linux")) {
                Toolkit.getDefaultToolkit().sync();
            }

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
                    setTitle("Snake - Score: " + game.getScore());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}