package GameHandler;

import Helpers.Constants;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * class for countdown in game.
 */
public class CountdownAnimation implements Animation {
    private final long milliSecondsToSleep;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private final Sleeper sleeper;

    /**
     * constructor.
     * @param numOfSeconds number of seconds to show the countdown.
     * @param countFrom countdown start from here.
     * @param gameScreen game sprite collection to show.
     */
    public CountdownAnimation(int numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        double presentUnit = (double) numOfSeconds / (double) countFrom;
        this.milliSecondsToSleep = (long) (1000L * presentUnit);
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.GRAY);
        if (this.countFrom != 0) {
            d.drawText(Constants.SCREEN_WIDTH / 2 - 40, Constants.SCREEN_HEIGHT / 2 + 75,
                    Integer.toString(this.countFrom), 150);
        }
        sleeper.sleepFor(milliSecondsToSleep);
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom == 0;
    }
}
