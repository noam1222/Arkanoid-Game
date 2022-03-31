// 209407162 Noam Maimon

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private final double dx;
    private final double dy;

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
     * convert angle and speed of ball to Velocity object.
     * @param angle the angle of the ball.
     * @param speed the speed of the ball.
     * @return new Velocity object of the receiving angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
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
     * Take a point with position (x,y) and move the point to position (x+dx, y+dy).
     *
     * @param p the point to move.
     * @return return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}