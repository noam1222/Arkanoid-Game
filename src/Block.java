import biuoop.DrawSurface;

import java.awt.Color;

/**
 * collidable class - represent block.
 */
public class Block implements Collidable {
    private final Rectangle blockSurface;
    private final Color color;

    /**
     * constructor of the block object.
     * @param blockSurface the rectangle shape and location of the block.
     * @param color the color of the block.
     */
    public Block(Rectangle blockSurface, Color color) {
        this.blockSurface = blockSurface;
        this.color = color;
    }

    /**
     * get the rectangle shape of the block.
     * @return the rectangle shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.blockSurface;
    }

    /**
     * draw the block on the surface.
     * @param surface the surface to draw the block on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = (int) this.blockSurface.getUpperLeft().getX();
        int y = (int) this.blockSurface.getUpperLeft().getY();
        int width = (int) this.blockSurface.getWidth();
        int height = (int) this.blockSurface.getHeight();
        surface.fillRectangle(x, y, width, height);
    }

    /**
     * get new velocity of object after collide with this block.
     * @param collisionPoint the collision point in the collidable object.
     * @param currentVelocity velocity of the object we collided with.
     * @return the new velocity after the hit.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
        return newVelocity;
    }
}
