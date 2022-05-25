package GameHandler;

import Helpers.Constants;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class for pause the screen in Arkanoid game.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.green);
        d.drawText(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 2,
                "paused -- press space to continue", 30);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
