// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.awt.Color;

/**
 * class of Arkanoid game handler.
 */
public class Game {
    private static final int BORDER_THICK = Constants.SCREEN_BORDERS_THICK;

    private final int screenWidth;
    private final int screenHeight;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;

    private GUI gui;

    private final Counter blocksCounter;
    private final Counter ballsCounter;

    /**
     * <p>constructor - initialize this game object with received screen width and height,
     * and new SpriteCollection and GameEnvironment objects.</p>
     *
     * @param screenWidth  the game screen width.
     * @param screenHeight the game screen height.
     */
    public Game(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
    }

    /**
     * add Collidable object to the environment of this game.
     *
     * @param c the Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove the given collidable from this game.
     *
     * @param c collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove given sprite from this game.
     *
     * @param s sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * add Sprite object to the sprites of this game.
     *
     * @param s Sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * get the screen blocks that restrict the borders of this game.
     *
     * @return Block array of the screen blocks that restrict the borders of this game.
     */
    private Block[] getScreenBlockBorders() {
        Rectangle u = new Rectangle(new Point(BORDER_THICK, 0), screenWidth - 2 * BORDER_THICK, BORDER_THICK);
        Rectangle r = new Rectangle(new Point(screenWidth - BORDER_THICK, 0), BORDER_THICK, screenHeight);
        Rectangle d = new Rectangle(new Point(BORDER_THICK, screenHeight - BORDER_THICK),
                screenWidth - 2 * BORDER_THICK, BORDER_THICK);
        Rectangle l = new Rectangle(new Point(0, 0), BORDER_THICK, screenHeight);
        Color borderColor = Color.gray;
        Block up = new Block(u, borderColor);
        Block right = new Block(r, borderColor);
        Block bottom = new Block(d, borderColor);
        Block left = new Block(l, borderColor);
        return new Block[]{up, right, left, bottom};
    }

    /**
     * check if received point is out of this game borders.
     *
     * @param p point to check.
     * @return true if the point is out of borders, false otherwise.
     */
    private boolean isOutOfBorders(Point p) {
        return p.getX() <= 0 || p.getX() >= screenWidth || p.getY() <= 0 || p.getY() >= screenHeight;
    }

    /**
     * get random point that not in any of the collidable object in this game environment.
     *
     * @return random point that not in any of the collidable object in this game environment.
     */
    private Point getRandomFreePoint() {
        java.util.Random random = new java.util.Random();
        Point p;
        do {
            double x = random.nextDouble() * screenWidth;
            double y = random.nextDouble() * screenHeight;
            p = new Point(x, y);
        } while (!this.environment.isFreePoint(p));
        return p;
    }

    /**
     * get the paddle block object of this game.
     * @param paddleWidth the paddle width.
     * @param paddleHeight the paddle height.
     * @param color the paddle color.
     * @return the paddle block object of this game.
     */
    private Block getPaddleBlock(int paddleWidth, int paddleHeight, Color color) {
        Point upperLeft = new Point(screenWidth / 2.0 - paddleWidth / 2.0,
                screenHeight - BORDER_THICK - paddleHeight);
        Rectangle paddleRectangle = new Rectangle(upperLeft, paddleWidth, paddleHeight);
        return new Block(paddleRectangle, color);
    }

    /**
     *  Initialize a new game: create the necessary objects and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", screenWidth, screenHeight);
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);

        // initialize paddle
        Block paddleBlock = this.getPaddleBlock(100, Constants.PADDLE_HEIGHT, Color.orange);
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), paddleBlock, BORDER_THICK,
                screenWidth - BORDER_THICK, 10);
        paddle.addToGame(this);

        // initialize borders
        for (Block b : this.getScreenBlockBorders()) {
            b.addToGame(this);
        }

        // initialize ass 3 collidables blocks
        int lineHeight = screenHeight / 4;
        int blockWidth = 40;
        int blockHeight = 20;
        Color[] colors = {Color.cyan, Color.green, Color.yellow, Color.MAGENTA, Color.RED, Color.PINK, Color.ORANGE};
        for (int numOfBlocksInRow = 12; numOfBlocksInRow >= 7; numOfBlocksInRow--) {
            Block[] blocks = new Block[numOfBlocksInRow];
            Color color = colors[numOfBlocksInRow - 7];
            Point upLeft = new Point(screenWidth - BORDER_THICK - numOfBlocksInRow * blockWidth, lineHeight);
            for (int i = 0; i < blocks.length; i++) {
                Rectangle rect = new Rectangle(upLeft, blockWidth, blockHeight);
                blocks[i] = new Block(rect, color);
                blocks[i].addToGame(this);
                blocks[i].addHitListener(blockRemover);
                this.blocksCounter.increase(1);
                upLeft = upLeft.getPointInDistance(blockWidth, 0);
            }
            lineHeight += blockHeight;
        }

        // initialize balls
        ArrayList<Ball> balls = new ArrayList<>();
        Ball b1 = new Ball(250, 50, 5, Color.yellow, this.environment);
        b1.setVelocity(10, 5);
        balls.add(b1);
        Ball b2 = new Ball(400, 600, 5, Color.green, this.environment);
        b2.setVelocity(5, 5);
        balls.add(b2);
        Ball b3 = new Ball(400, 700, 15, Color.red, this.environment);
        b3.setVelocity(5, -5);
        balls.add(b3);

        for (Ball ball : balls) {
            if (!this.environment.isFreePoint(ball.getCenter()) || isOutOfBorders(ball.getCenter())) {
                ball.setCenter(this.getRandomFreePoint());
            }
            ball.addToGame(this);
            this.ballsCounter.increase(1);
        }
    }

    /**
     * run the game - start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, screenWidth, screenHeight);
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            // check for end of the game
            if (this.blocksCounter.getValue() == 0 || this.ballsCounter.getValue() == 0) {
                this.gui.close();
                return;
            }
        }
    }
}