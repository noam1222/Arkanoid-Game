import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class that indicate the score of the player in the Arkanoid game by implement the Sprite interface.
 */
public class ScoreIndicator implements Sprite {
    private static final int FONT_SIZE = 50;
    private static final int X = Constants.SCREEN_WIDTH / 2 - FONT_SIZE / 2;
    private static final int Y = Constants.SCREEN_BORDERS_THICK + FONT_SIZE;

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
        d.setColor(Color.WHITE);
        String score = String.valueOf(this.scoreCounter.getValue());
        d.drawText(X, Y, score, FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
