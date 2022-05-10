// 209407162 Noam Maimon

package Helpers;

import Geometry.Point;

/**
 * Helpers.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor - initialize the change in position on the `x` and the `y` axes.
     *
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * convert angle and speed of ball to Helpers.Velocity object.
     *
     * @param angle the angle of the ball.
     * @param speed the speed of the ball.
     * @return new Helpers.Velocity object of the receiving angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * get the speed of this velocity.
     * @return the speed of this velocity.
     */
    public double getSpeed() {
        // Pythagorean theorem
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * get dx parameter.
     *
     * @return dx parameter.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get dy parameter.
     *
     * @return dy parameter.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * set the dy parameter.
     *
     * @param dx new dx to set.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * set the dy parameter.
     *
     * @param dy new dy to set.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and move the point to position (x+dx, y+dy).
     *
     * @param p the point to move.
     * @return return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}

