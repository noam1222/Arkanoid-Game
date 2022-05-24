package GameHandler;

import Helpers.Constants;
import Helpers.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class to display the end screen of the game.
 */
public class EndScreen implements Animation {
    private static final String DEAD_MESSAGE = "Game Over. Your score is ";
    private static final String WIN_MESSAGE = "You Win! Your score is ";

    private final boolean isWin;
    private final Counter score;
    private final KeyboardSensor keyboardSensor;
    private boolean exit;

    /**
     * constructor - initialize this end game screen object.
     * @param isWin did the user win the game
     * @param score score of the user
     * @param keyboardSensor keyboard sensor of the user.
     */
    public EndScreen(boolean isWin, Counter score, KeyboardSensor keyboardSensor) {
        this.isWin = isWin;
        this.score = score;
        this.keyboardSensor = keyboardSensor;
        this.exit = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        String text = isWin ? WIN_MESSAGE : DEAD_MESSAGE;
        d.drawText(Constants.SCREEN_WIDTH / 4 + 10, Constants.SCREEN_HEIGHT / 2,
                text + this.score.getValue(), 30);
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.exit = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.exit;
    }
}
