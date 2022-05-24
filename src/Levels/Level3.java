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
public class Level3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(3, 3));
        velocities.add(new Velocity(3, 3));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(Constants.SCREEN_RECTANGLE, new Color(0, 100, 0));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int lineHeight = Constants.SCREEN_HEIGHT / 4;
        int blockWidth = 45;
        int blockHeight = 25;
        Color[] colors = {Color.white, Color.blue, Color.yellow, Color.red, Color.gray};
        for (int numOfBlocksInRow = 10; numOfBlocksInRow >= 6; numOfBlocksInRow--) {
            Color color = colors[numOfBlocksInRow - 6];
            Point upLeft = new Point(Constants.SCREEN_WIDTH - Constants.SCREEN_BORDERS_THICK
                    - numOfBlocksInRow * blockWidth, lineHeight);
            for (int i = 0; i < numOfBlocksInRow; i++) {
                Rectangle rect = new Rectangle(upLeft, blockWidth, blockHeight);
                blocks.add(new Block(rect, color));
                upLeft = upLeft.getPointInDistance(blockWidth, 0);
            }
            lineHeight += blockHeight;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 10 + 9 + 8 + 7 + 6;
    }
}
