// 209407162 Noam Maimon

package Sprites;

import Helpers.Constants;
import Helpers.Counter;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class that indicate the score of the player in the Arkanoid game by implement the Sprites.Sprite interface.
 */
public class ScoreIndicator implements Sprite {
    private static final int X = Constants.SCREEN_WIDTH / 2 - Constants.FONT_SIZE / 2;
    private static final int Y = (int) Math.floor(1.5 * Constants.FONT_SIZE) + 1;

    private final Counter scoreCounter;

    /**
     * constructor - initialize score indicator object.
     * @param scoreCounter the player score counter.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String score = "Score: " + this.scoreCounter.getValue();
        d.drawText(X, Y, score, Constants.FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
