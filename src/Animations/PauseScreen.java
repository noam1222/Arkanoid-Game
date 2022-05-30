package Animations;

import Helpers.Constants;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class for pause the screen in Arkanoid game.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(100, 120, 225));
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        d.setColor(Color.pink);
        d.drawText(Constants.SCREEN_WIDTH / 4 + 110, Constants.SCREEN_HEIGHT / 2 - 100,
                "paused", 60);
        d.fillCircle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 80);
        d.setColor(Color.white);
        d.fillRectangle(Constants.SCREEN_WIDTH / 2 - 45, Constants.SCREEN_HEIGHT / 2 - 40,
                30, 90);
        d.fillRectangle(Constants.SCREEN_WIDTH / 2 + 15, Constants.SCREEN_HEIGHT / 2 - 40,
                30, 90);
        d.setColor(Color.pink);
        d.drawText(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 2 + 120,
                "       press space to continue", 30);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
