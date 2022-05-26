package Animations;

import Helpers.Constants;
import Helpers.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class to display the Game Over screen of the game.
 */
public class GameOverScreen implements Animation {
    private final Counter score;

    /**
     * constructor - initialize this game over screen object.
     *
     * @param score score of the user
     */
    public GameOverScreen(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        d.setColor(Color.red);
        d.drawText(Constants.SCREEN_WIDTH / 4 - 60, Constants.SCREEN_HEIGHT / 2 - 120, "Game Over", 100);
        d.fillCircle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 + 20, 100);
        d.setColor(Color.WHITE);
        d.fillRectangle(Constants.SCREEN_WIDTH / 2 - 80, Constants.SCREEN_HEIGHT / 2, 160, 50);
        d.setColor(Color.red);
        d.drawText(Constants.SCREEN_WIDTH / 4 + 100, Constants.SCREEN_HEIGHT / 2 + 180,
                "Your score is " + this.score.getValue(), 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
