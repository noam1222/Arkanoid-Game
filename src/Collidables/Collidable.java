package Collidables;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Ball;

/**
 * interface of collidable objects.
 */
public interface Collidable {
    /**
     * get the collision shape of the collidable object.
     * @return the (rectangle) collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * <p>Notify the object that we collided with it at collisionPoint with a given velocity.
     * and calculate the new excepted velocity.</p>
     *
     * @param hitter the ball that hit this collidable.
     * @param collisionPoint the collision point in the collidable object.
     * @param currentVelocity velocity of the object we collided with.
     * @return the new velocity expected after the hit (based ot the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
