// 209407162 Noam Maimon

package GameHandler;

import Collidables.Block;
import Collidables.Collidable;
import Collidables.GameEnvironment;
import Geometry.Point;
import Geometry.Rectangle;
import Helpers.Constants;
import Helpers.Counter;
import Sprites.Ball;
import Sprites.Paddle;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * class of Arkanoid game handler.
 */
public class GameLevel implements Animation {
    private static final int BORDER_THICK = Constants.SCREEN_BORDERS_THICK;

    private final int screenWidth;
    private final int screenHeight;
    private final GameGeometry gameGeometry;

    private final SpriteCollection sprites;
    private final GameEnvironment environment;

    private GUI gui;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final Counter scoreCounter;

    /**
     * <p>constructor - initialize this game object with received screen width and height,
     * and new Sprites.SpriteCollection and GameEnvironment objects.</p>
     *
     * @param screenWidth  the game screen width.
     * @param screenHeight the game screen height.
     */
    public GameLevel(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gameGeometry = new GameGeometry(this.screenWidth, this.screenHeight, this.environment);
        this.gui = new GUI("Arkanoid", this.screenWidth, this.screenHeight);
        this.runner = new AnimationRunner(this.gui, 60);
        this.keyboard = gui.getKeyboardSensor();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.scoreCounter = new Counter();
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
     * add Sprites.Sprite object to the sprites of this game.
     *
     * @param s Sprites.Sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the necessary objects and add them to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);

        // initialize paddle
        Block paddleBlock = this.gameGeometry.getPaddleBlock(100, Constants.PADDLE_HEIGHT, Color.orange);
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), paddleBlock, BORDER_THICK,
                screenWidth - BORDER_THICK, 10);
        paddle.addToGame(this);

        // initialize borders
        for (Block b : this.gameGeometry.getScreenBlockBorders()) {
            b.addToGame(this);
        }

        // initialize death block
        Block deathBlock = this.gameGeometry.getDeathBlock();
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);

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
                blocks[i].addHitListener(scoreTrackingListener);
                this.blocksCounter.increase(1);
                upLeft = upLeft.getPointInDistance(blockWidth, 0);
            }
            lineHeight += blockHeight;
        }

        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        this.sprites.addSprite(scoreIndicator);

        // initialize balls
        ArrayList<Ball> balls = new ArrayList<>();
        Ball b1 = new Ball(250, 50, 5, Color.yellow, this.environment);
        b1.setVelocity(10, 5);
        balls.add(b1);
        Ball b2 = new Ball(400, 600, 5, Color.green, this.environment);
        b2.setVelocity(5, -5);
        balls.add(b2);
        Ball b3 = new Ball(100, 100, 7, Color.CYAN, this.environment);
        b3.setVelocity(4, -4);
        balls.add(b3);

        for (Ball ball : balls) {
            if (!this.environment.isFreePoint(ball.getCenter()) || gameGeometry.isOutOfBorders(ball.getCenter())) {
                ball.setCenter(gameGeometry.getRandomFreePoint());
            }
            ball.addToGame(this);
            this.ballsCounter.increase(1);
        }
    }

    /**
     * run the game - start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Constants.SCREEN_COLOR);
        d.fillRectangle(0, 0, screenWidth, screenHeight);

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        // check for end of level or game
        if (this.blocksCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        } else if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}