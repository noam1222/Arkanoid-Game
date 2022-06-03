package GameHandler;

import Collidables.Block;
import Collidables.GameEnvironment;
import Geometry.Point;
import Geometry.Rectangle;
import Helpers.Constants;

import java.awt.Color;

/**
 * class to handle the Arkanoid game geometry.
 */
public class BasicGameGeometry {
    private static final int BORDER_THICK = Constants.SCREEN_BORDERS_THICK;

    private final int screenWidth;
    private final int screenHeight;
    private final GameEnvironment environment;

    /**
     * constructor - initialize game blocks object.
     * @param screenWidth screen width of the game.
     * @param screenHeight screen height of the game.
     * @param environment game environment of the game - all the collidables objects in the game.
     */
    public BasicGameGeometry(int screenWidth, int screenHeight, GameEnvironment environment) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.environment = environment;
    }

    /**
     * get the screen blocks that restrict the borders of this game.
     *
     * @return Block array of the screen blocks that restrict the borders of this game.
     */
    public Block[] getScreenBlockBorders() {
        Rectangle s = new Rectangle(new Point(BORDER_THICK, 0), screenWidth - 2 * BORDER_THICK,
                Constants.FONT_SIZE * 2);
        Rectangle u = new Rectangle(new Point(BORDER_THICK, s.getHeight()),
                screenWidth - 2 * BORDER_THICK, BORDER_THICK);
        Rectangle r = new Rectangle(new Point(screenWidth - BORDER_THICK, 0), BORDER_THICK, screenHeight);
        Rectangle l = new Rectangle(new Point(0, 0), BORDER_THICK, screenHeight);
        Color borderColor = Color.gray;
        Color scoreColor = Color.lightGray;
        Block score = new Block(s, scoreColor);
        Block up = new Block(u, borderColor);
        Block right = new Block(r, borderColor);
        Block left = new Block(l, borderColor);
        return new Block[]{score, up, right, left};
    }

    /**
     * get the death bottom zone for the balls.
     * @return block in the start of the death zone.
     */
    public Block getDeathBlock() {
        Rectangle death = new Rectangle(new Point(BORDER_THICK, screenHeight),
                screenWidth - BORDER_THICK, 1);
        return new Block(death, Constants.SCREEN_COLOR);
    }

    /**
     * get the paddle block object of this game.
     * @param paddleWidth the paddle width.
     * @param paddleHeight the paddle height.
     * @param color the paddle color.
     * @return the paddle block object of this game.
     */
    public Block getPaddleBlock(int paddleWidth, int paddleHeight, Color color) {
        Point upperLeft = new Point(screenWidth / 2.0 - paddleWidth / 2.0,
                screenHeight - BORDER_THICK - paddleHeight);
        Rectangle paddleRectangle = new Rectangle(upperLeft, paddleWidth, paddleHeight);
        return new Block(paddleRectangle, color);
    }

    /**
     * check if received point is out of this game borders.
     *
     * @param p point to check.
     * @return true if the point is out of borders, false otherwise.
     */
    public boolean isOutOfBorders(Point p) {
        return p.getX() <= 0 || p.getX() >= screenWidth || p.getY() <= 0 || p.getY() >= screenHeight;
    }

    /**
     * get random point that not in any of the collidable object in this game environment.
     *
     * @return random point that not in any of the collidable object in this game environment.
     */
    public Point getRandomFreePoint() {
        java.util.Random random = new java.util.Random();
        Point p;
        do {
            double x = random.nextDouble() * screenWidth;
            double y = random.nextDouble() * screenHeight;
            p = new Point(x, y);
        } while (!this.environment.isFreePoint(p));
        return p;
    }
}
