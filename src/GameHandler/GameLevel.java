// 209407162 Noam Maimon

package GameHandler;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Animations.KeyPressStoppableAnimation;
import Animations.PauseScreen;
import Collidables.Block;
import Collidables.Collidable;
import Collidables.GameEnvironment;
import Geometry.Velocity;
import Helpers.Constants;
import Helpers.Counter;
import Levels.LevelInformation;
import Sprites.Ball;
import Sprites.Paddle;
import Sprites.InfoBlock;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * class of Arkanoid game handler.
 */
public class GameLevel implements Animation {
    private static final int BORDER_THICK = Constants.SCREEN_BORDERS_THICK;

    private final BasicGameGeometry basicGameGeometry;

    private final LevelInformation levelInformation;

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private Paddle paddle;

    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;

    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final Counter scoreCounter;
    private static final Counter LIVES = new Counter(7);

    /**
     * <p>constructor - initialize this game object with received screen width and height,
     * and new Sprites.SpriteCollection and GameEnvironment objects.</p>
     *
     * @param levelInformation information about the level to run.
     * @param scoreCounter counter of the score of the whole game.
     * @param animationRunner runner of this game.
     * @param keyboard user keyboard to handle user commands.
     */
    public GameLevel(LevelInformation levelInformation, Counter scoreCounter, AnimationRunner animationRunner,
                     KeyboardSensor keyboard) {
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.basicGameGeometry = new BasicGameGeometry(Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT, this.environment);
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.scoreCounter = scoreCounter;
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

        this.sprites.addSprite(this.levelInformation.getBackground());

        initializePaddle();

        // initialize borders
        for (Block b : this.basicGameGeometry.getScreenBlockBorders()) {
            b.addToGame(this);
        }

        // initialize info bar
        InfoBlock infoBlock = new InfoBlock(LIVES, scoreCounter, this.levelInformation.levelName());
        this.sprites.addSprite(infoBlock);

        // initialize death block
        Block deathBlock = this.basicGameGeometry.getDeathBlock();
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);

        //initialize level blocks
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            this.blocksCounter.increase(1);
        }

        initializeBalls();
    }

    /**
     * initialize the paddle of this game level.
     */
    private void initializePaddle() {
        Block paddleBlock = this.basicGameGeometry.getPaddleBlock(this.levelInformation.paddleWidth(),
                Constants.PADDLE_HEIGHT, Color.orange);
        this.paddle = new Paddle(this.keyboard, paddleBlock, BORDER_THICK,
                Constants.SCREEN_WIDTH - BORDER_THICK, this.levelInformation.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * initialize the balls of this game level.
     */
    private void initializeBalls() {
        for (Velocity v : this.levelInformation.initialBallVelocities()) {
            Ball b = new Ball(Constants.SCREEN_WIDTH / 2.0,
                    Constants.SCREEN_HEIGHT - Constants.PADDLE_HEIGHT - 20, 5, Color.white, this.environment);
            b.setVelocity(v);
            if (!this.environment.isFreePoint(b.getCenter()) || basicGameGeometry.isOutOfBorders(b.getCenter())) {
                b.setCenter(basicGameGeometry.getRandomFreePoint());
            }
            b.addToGame(this);
        }
        this.ballsCounter.increase(this.levelInformation.numberOfBalls());
    }

    /**
     * run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }

        //check for end of level or game
        if (this.blocksCounter.getValue()
                == this.levelInformation.blocks().size() - this.levelInformation.numberOfBlocksToRemove()) {
            this.running = false;
        } else if (this.ballsCounter.getValue() == 0) {
            LIVES.decrease(1);
            if (LIVES.getValue() > 0) {
                initializeBalls();
                this.sprites.removeSprite(this.paddle);
                this.environment.removeCollidable(this.paddle);
                initializePaddle();
                this.runner.run(new CountdownAnimation(2, 3, this.sprites));
            } else {
                this.running = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * check if the ball counter is 0.
     * @return true if the ball counter is 0, false otherwise.
     */
    public boolean looseAllBalls() {
        return this.ballsCounter.getValue() == 0;
    }
}