package GameHandler;

import Collidables.Block;
import Geometry.Velocity;
import Sprites.Sprite;

import java.util.List;

/**
 * Interface that represent game level in the Arkanoid game.
 */
public interface LevelInformation {
    /**
     * get the numbers of balls in this level.
     *
     * @return the numbers of balls in this level.
     */
    int numberOfBalls();

    /**
     * get the initial velocity of each ball.<br/>
     * <p>Note that initialBallVelocities().size() == numberOfBalls()</p>
     *
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * get paddle speed.
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * get paddle width.
     *
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * get level name.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * the background of the level.
     *
     * @return sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * the Blocks that make up this level, each block contains its size, color and location.
     *
     * @return list of the blocks that make up this level
     */
    List<Block> blocks();

    /**
     * get the number of blocks that should be removed before the level is considered to be "cleared".<br/>
     * <p>This number should be <= blocks.size();</p>
     *
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}