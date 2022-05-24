package Levels;

import Collidables.Block;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Helpers.Constants;
import Sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class for level no. 2 of the Arkanoid game.
 */
public class Level2 implements LevelInformation {
    private static final int BLOCKS_Y = Constants.SCREEN_HEIGHT / 2 - 40;
    private static final int BLOCKS_WIDTH = (Constants.SCREEN_WIDTH - 2 * Constants.SCREEN_BORDERS_THICK) / 15;
    private static final int BLOCKS_HEIGHT = 20;
    private static final int NUM_OF_BLOCKS = 15;
    private static final int NUM_OF_BALLS = 10;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            velocities.add(new Velocity(5, -5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(Constants.SCREEN_RECTANGLE, Color.white);
    }

    @Override
    public List<Block> blocks() {
        int x = Constants.SCREEN_BORDERS_THICK;
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.red, Color.ORANGE, Color.yellow, Color.green, Color.blue, Color.pink, Color.magenta};
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            Color color;
            if (i <= 7) {
                color = colors[i / 2];
            } else if (i == 8) {
                color = Color.green;
            } else {
                color = colors[(i + 1) / 2 - 1];
            }
            blocks.add(new Block(new Rectangle(new Point(x, BLOCKS_Y), BLOCKS_WIDTH, BLOCKS_HEIGHT), color));
            x += BLOCKS_WIDTH;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
