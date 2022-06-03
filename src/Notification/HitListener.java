package Notification;

import Collidables.Block;
import Sprites.Ball;

/**
 * interface that indicate that objects that implement it get notification when they are get hit.
 */
public interface HitListener {
    /**
     * indicate the hit event of ball in block has occurred.
     * @param beingHit the block that being hit.
     * @param hitter the Sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}