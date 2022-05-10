// 209407162 Noam Maimon

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * represent ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private final Color color;
    private Velocity velocity;
    private final GameEnvironment gameEnvironment;

    /**
     * <p>constructor - initialize the ball by center point, radius and color.\
     * initially the velocity of the ball is 0.</p>
     *
     * @param center          center of the ball.
     * @param r               radius of the ball.
     * @param color           color of the ball.
     * @param gameEnvironment the game environment of this ball.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        // if not assign  afterwards - the ball not move
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * <p>constructor - initialize the ball by x and y value as tth center point, radius and color.\
     * initially the velocity of the ball is 0.</p>
     *
     * @param x               x value of the center point.
     * @param y               y value of the center point.
     * @param r               radius of the ball.
     * @param color           color of the ball.
     * @param gameEnvironment the game environment of this ball.
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnvironment) {
        this(new Point(x, y), r, color, gameEnvironment);
    }

    /**
     * get the center of this ball.
     *
     * @return the center of this ball.
     */
    public Point getCenter() {
        return this.center;
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
    public Color getColor() {
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
     *
     * @param radius the new size to set.
     */
    public void setSize(int radius) {
        this.radius = radius;
    }

    /**
     * get this ball game environment.
     *
     * @return this ball game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * set the center of the ball.
     *
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
        Point newCenter = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, newCenter);
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        if (collision != null) {
            Point collisionPoint = collision.collisionPoint();
            double moveX = collisionPoint.getX() - this.center.getX();
            moveX -= Math.signum(moveX) * 1;
            double moveY = collisionPoint.getY() - this.center.getY();
            moveY -= Math.signum(moveY) * 1;
            Velocity nearHit = new Velocity(moveX, moveY);
            this.center = nearHit.applyToPoint(this.center);
            this.velocity = collision.collisionObject().hit(this, collisionPoint, this.velocity);
        } else {
            this.center = newCenter;
        }
        // check if paddle go into ball
        int dangerZone = Constants.SCREEN_HEIGHT - Constants.SCREEN_BORDERS_THICK - Constants.PADDLE_HEIGHT;
        if (this.getY() >= dangerZone && !this.gameEnvironment.isFreePoint(this.center)) {
            this.center = this.center.getPointInDistance(0, -Constants.PADDLE_HEIGHT - 1);
            this.velocity.setDy(-Math.abs(this.velocity.getDy()));
        }
    }

    /**
     * notify the ball that time has passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface to draw in.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * add this ball to Game object.
     *
     * @param g the Game object to add to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * remove this ball from receiving game object.
     * @param g game object to remove from.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}