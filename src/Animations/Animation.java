package Animations;

import biuoop.DrawSurface;

/**
 * Interface for animation in the Arkanoid game.
 */
public interface Animation {
    /**
     * do one fram in the receiving draw surface.
     * @param d DrawSurface object to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * check if this animation should stop.
     * @return true if this animation should stop, false otherwise.
     */
    boolean shouldStop();
}
