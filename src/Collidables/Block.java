// 209407162 Noam Maimon

package Collidables;

import GameHandler.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Helpers.Velocity;
import Sprites.Ball;
import Sprites.Sprite;
import biuoop.DrawSurface;
import notification.HitListener;
import notification.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * collidable class - represent block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle blockSurface;
    private final Color color;
    private final List<HitListener> hitListeners;

    /**
     * constructor of the block object.
     *
     * @param blockSurface the rectangle shape and location of the block.
     * @param color        the color of the block.
     */
    public Block(Rectangle blockSurface, Color color) {
        this.blockSurface = blockSurface;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * get the rectangle shape of the block.
     *
     * @return the rectangle shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.blockSurface;
    }

    /**
     * get this block color.
     *
     * @return this block color.
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        Line[] borders = this.blockSurface.getBorder();
        for (int i = 0; i < borders.length; i++) {
            if (borders[i].isPointInLine(collisionPoint)) {
                if (i % 2 == 0) {
                    // hit in up or bottom border
                    newVelocity.setDy(-currentVelocity.getDy());
                } else {
                    // hit right or left border
                    newVelocity.setDx(-currentVelocity.getDx());
                }
            }
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * draw the block on the surface.
     *
     * @param surface the surface to draw the block on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = (int) this.blockSurface.getUpperLeft().getX();
        int y = (int) this.blockSurface.getUpperLeft().getY();
        int width = (int) this.blockSurface.getWidth();
        int height = (int) this.blockSurface.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);

    }

    /**
     * notify the block that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * add this block to Game.Game object.
     *
     * @param g the Game.Game object to add to.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove this block from receiving game.
     *
     * @param game game object to remove this block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify all this block hit listeners that this block get hit.
     *
     * @param hitter the ball that hit this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
