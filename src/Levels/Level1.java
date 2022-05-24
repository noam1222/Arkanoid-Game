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
 * class for level no. 1 of the Arkanoid game.
 */
public class Level1 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(
                new Rectangle(new Point(0, 0), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT),
                Color.BLACK
        );
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(
                new Rectangle(
                        new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 4), 30, 30
                ),
                Color.red
        ));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
