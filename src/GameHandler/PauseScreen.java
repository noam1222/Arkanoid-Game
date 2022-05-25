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
        d.setColor(Color.black);
        d.drawText(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 2 - 100,
                "                    paused", 30);
        d.fillCircle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 50);
        d.setColor(Color.white);
        d.fillRectangle(Constants.SCREEN_WIDTH / 2 - 20, Constants.SCREEN_HEIGHT / 2 - 20,
                10, 45);
        d.fillRectangle(Constants.SCREEN_WIDTH / 2 + 10, Constants.SCREEN_HEIGHT / 2 - 20,
                10, 45);
        d.setColor(Color.black);
        d.drawText(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 2 + 100,
                "       press space to continue", 30);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
