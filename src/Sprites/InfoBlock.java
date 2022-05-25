// 209407162 Noam Maimon

package Sprites;

import Helpers.Constants;
import Helpers.Counter;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class that indicate the score of the player in the Arkanoid game by implement the Sprites.Sprite interface.
 */
public class InfoBlock implements Sprite {
    private static final int X = 50;
    private static final int Y = (int) Math.floor(1.5 * Constants.FONT_SIZE) + 1;

    private final Counter lives;
    private final Counter scoreCounter;
    private final String levelName;

    /**
     * constructor - initialize score indicator object.
     * @param scoreCounter the player score counter.
     * @param levelName the name of this level.
     * @param lives the player lives remaining.
     */
    public InfoBlock(Counter lives, Counter scoreCounter, String levelName) {
        this.lives = lives;
        this.scoreCounter = scoreCounter;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(X, Y, "Lives: " + this.lives.getValue(), Constants.FONT_SIZE);
        d.drawText(X + 300, Y, "Score: " + this.scoreCounter.getValue(), Constants.FONT_SIZE);
        d.drawText(X + 500, Y, "Level Name: " + this.levelName, Constants.FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
