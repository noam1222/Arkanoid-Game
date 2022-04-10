// 209407162 Noam Maimon

import biuoop.DrawSurface;

/**
 * interface of sprite - object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the surface to draw thw sprite in.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}