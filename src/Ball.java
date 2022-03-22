// 209407162 Noam Maimon

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * represent ball.
 */
public class Ball {
    private Point center;
    private int radius;
    private final Color color;
    private Velocity velocity;

    /**
     * <p>constructor - initialize the ball by center point, radius and color.\
     * initially the velocity of the ball is 0.</p>
     *
     * @param center center of the ball.
     * @param r      radius of the ball.
     * @param color  color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        // if not assign velocity - not move
        this.velocity = new Velocity(0, 0);
    }

    /**
     * <p>constructor - initialize the ball by x and y value as tth center point, radius and color.\
     * initially the velocity of the ball is 0.</p>
     *
     * @param x     x value of the center point.
     * @param y     y value of the center point.
     * @param r     radius of the ball.
     * @param color color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * get the x value of the center of the ball.
     *
     * @return the x value of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get the y value of the center of the ball.
     *
     * @return the y value of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * get size of the ball.
     *
     * @return size of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * get color of the ball.
     *
     * @return size of the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * get velocity of the ball.
     *
     * @return velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set the size of the ball.
     * @param radius the new size to set.
     */
    public void setSize(int radius) {
        this.radius = radius;
    }

    /**
     * set the center of the ball.
     * @param center the new center to set.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * set the velocity of the ball with received velocity object.
     *
     * @param v new velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the velocity of the ball with dx, dy numbers.
     *
     * @param dx represent the new velocity on the x-axis.
     * @param dy represent the new velocity on the y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * move the ball one step further by the ball velocity.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface to draw in.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
}