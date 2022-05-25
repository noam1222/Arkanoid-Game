package GameHandler;

import Helpers.Constants;
import Helpers.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class to display the end screen of the game.
 */
public class EndScreen implements Animation {
    private static final String DEAD_MESSAGE = "Game Over. Your score is ";
    private static final String WIN_MESSAGE = "You Win! Your score is ";

    private final boolean isWin;
    private final Counter score;
    private boolean exit;

    /**
     * constructor - initialize this end game screen object.
     * @param isWin did the user win the game
     * @param score score of the user
     */
    public EndScreen(boolean isWin, Counter score) {
        this.isWin = isWin;
        this.score = score;
        this.exit = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        String text;
        if (isWin) {
            text = WIN_MESSAGE;
            d.setColor(Color.yellow);
        } else {
            text = DEAD_MESSAGE;
            d.setColor(Color.red);
        }
        d.drawText(Constants.SCREEN_WIDTH / 4 + 10, Constants.SCREEN_HEIGHT / 2,
                text + this.score.getValue(), 30);
    }

    @Override
    public boolean shouldStop() {
        return this.exit;
    }
}
