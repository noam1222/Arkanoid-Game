package Levels;

import Collidables.Block;
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
public class Level4 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double alpha = 180.0 / (this.numberOfBalls() + 1);
        for (int i = 1; i <= this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(i * alpha, 5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Block(Constants.SCREEN_RECTANGLE, Color.blue);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.CYAN, Color.pink, Color.white, Color.green, Color.yellow, Color.red, Color.gray};
        int lineHeight = Constants.SCREEN_HEIGHT / 4;
        int blockWidth = (Constants.SCREEN_WIDTH - 2 * Constants.SCREEN_BORDERS_THICK) / 15;
        int blockHeight = 25;
        for (int row = 10; row >= 4; row--) {
            Color color = colors[row - 4];
            Geometry.Point upLeft = new Geometry.Point(Constants.SCREEN_WIDTH - Constants.SCREEN_BORDERS_THICK
                    - 15 * blockWidth, lineHeight);
            for (int i = 0; i < 15; i++) {
                Geometry.Rectangle rect = new Rectangle(upLeft, blockWidth, blockHeight);
                blocks.add(new Block(rect, color));
                upLeft = upLeft.getPointInDistance(blockWidth, 0);
            }
            lineHeight += blockHeight;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15 * 7;
    }
}
