package Animations;

import Helpers.Constants;
import Helpers.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class to display the Win game screen of the game.
 */
public class WinScreen implements Animation {
    private final Counter score;
    /**
     * constructor - initialize this win game screen object.
     *
     * @param score score of the user
     */
    public WinScreen(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        d.setColor(Color.orange);
        d.drawText(Constants.SCREEN_WIDTH / 4 - 100, Constants.SCREEN_HEIGHT / 2 - 140, "You WIN!", 150);
        for (int i = 100; i > 0; i -= 10) {
            d.setColor(Color.WHITE);
            d.fillCircle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 + 10, i);
            d.setColor(Color.YELLOW);
            d.fillCircle(Constants.SCREEN_WIDTH / 2 + 10, Constants.SCREEN_HEIGHT / 2 + 10, i - 5);
        }
        d.setColor(Color.orange);
        d.drawText(Constants.SCREEN_WIDTH / 4 + 20, Constants.SCREEN_HEIGHT / 2 + 200,
                "Your score is " + this.score.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
