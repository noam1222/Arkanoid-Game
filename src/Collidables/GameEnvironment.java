package Collidables;

import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represent the game environment with all the collidables object in the game.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * constructor - initialize the game environment with no collidables objects.
     */
    public GameEnvironment() {
        collidables = new ArrayList<Collidable>();
    }

    /**
     * constructor - initialize the game environment with received collidables objects.
     *
     * @param blocks received collidables objects.
     */
    public GameEnvironment(ArrayList<Collidable> blocks) {
        this.collidables = blocks;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable from this environment.
     *
     * @param c collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * get the collision info of trajectory in this game environment.
     *
     * @param trajectory the trajectory the object will move without any hit.
     * @return <p>if the object will not collide with any of the collidable in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.</p>
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidablesCopy = new ArrayList<>(this.collidables);
        CollisionInfo closestCollisionInfo = null;
        for (Collidable c : collidablesCopy) {
            ArrayList<Point> currentCollisionPoints = (ArrayList<Point>) c.getCollisionRectangle().
                    intersectionPoints(trajectory);
            for (Point p : currentCollisionPoints) {
                if (closestCollisionInfo == null) {
                    closestCollisionInfo = new CollisionInfo(p, c);
                } else {
                    Point closestPoint = trajectory.start().getClosestPoint(p, closestCollisionInfo.collisionPoint());
                    if (closestPoint != closestCollisionInfo.collisionPoint()) {
                        closestCollisionInfo = new CollisionInfo(p, c);
                    }
                }
            }
        }
        return closestCollisionInfo;
    }

    /**
     * check if received point is not in any collidable object in this environment.
     *
     * @param p point to check.
     * @return true if this point free, false otherwise.
     */
    public boolean isFreePoint(Point p) {
        for (Collidable c : this.collidables) {
            if (c.getCollisionRectangle().isPointIn(p)) {
                return false;
            }
        }
        return true;
    }
}