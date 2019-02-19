import java.awt.*;
import java.util.Random;

/**
 * Represents the environment where the Snake moves a food spawns.
 * <br/>
 * There are some special rules as to how the Snake can move. If the Snake's size
 * is 1, it can move in any direction. If the Snake's size is greater than 1, it
 * cannot move 180 degrees. Example: if the Snake is moving right, it cannot
 * immediately change its direction to left because it would run into itself.
 */
class Game  {

    // GameBoard.
    static final int BOARD_COLUMNS   = 40;
    static final int BOARD_ROWS      = 20;
    static final int SQUARE_SIZE     = 30;

    // Snake.
    static final int START_X         = BOARD_COLUMNS / 2;
    static final int START_Y         = BOARD_ROWS / 2;

    // Colors.
    static Color backgroundColor    = new Color(53, 53, 53);
    static Color snakeColor         = new Color(0, 255, 255);
    static Color foodColor          = new Color(211, 87, 45);

    // 7/27/2017
    // Add constant color changing background
    static Color green = new Color(36, 165, 107);
    static Color blue = new Color(42, 97, 203);
    static Color violet = new Color(150, 62, 238);
    static Color red = new Color(230, 61, 61);
    static Color orange = new Color(223, 150, 77);
    static Color yellow = new Color(230, 227, 67);

    private GameObject food;
    private int score = 0;


    /**
     * Constructs the board.
     */
    Game () {
        update();
    }

    /**
     * Move the Snake.
     */
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

        paintFood(g);
    }

    private void paintFood (Graphics2D g) {
        int x = 10 * SQUARE_SIZE;
        int y = 10 * SQUARE_SIZE;
        int corner = SQUARE_SIZE / 3;

        g.setColor(foodColor);
        g.fillRoundRect(x + 1, y + 1, SQUARE_SIZE - 2,
                SQUARE_SIZE - 2, corner, corner);
    }

}