// 209407162 Noam Maimon

import java.util.Random;
import java.awt.Color;

/**
 * class who represent box in the screen.
 */
public class Box {
    private int width;
    private int height;
    private double rightEdge;
    private double leftEdge;
    private double bottomEdge;
    private double topEdge;

    /**
     * constructor - initialize box object.
     * @param start the start point of the box on the screen.
     * @param boxWidth the width of the box.
     * @param boxHeight the height of the box.
     */
    Box(Point start, int boxWidth, int boxHeight) {
        this.width = boxWidth;
        this.height = boxHeight;
        this.rightEdge = start.getX() + boxWidth;
        this.leftEdge = start.getX();
        this.bottomEdge = start.getY() + boxHeight;
        this.topEdge = start.getY();
    }

    /**
     * constructor - initialize box object with (0, 0) as start point.
     * @param boxWidth the width of the box.
     * @param boxHeight the height of the box.
     */
    Box(int boxWidth, int boxHeight) {
        this(new Point(0, 0), boxWidth, boxHeight);
    }

    /**
     * constructor - initialize box object.
     * @param x x value of the start point of the box on the screen.
     * @param y y value of the start point of the box on the screen.
     * @param boxWidth the width of the box.
     * @param boxHeight the height of the box.
     */
    Box(double x, double y, int boxWidth, int boxHeight) {
        this(new Point(x, y), boxWidth, boxHeight);
    }

    /**
     * get the width of the box.
     * @return the width of the box.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * get the height of the box.
     * @return the height of the box.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * get the start point of the box.
     * @return the start point of the box.
     */
    public Point getStartPoint() {
        return new Point(this.rightEdge, this.bottomEdge);
    }

    /**
     * check if ball is out the box width borders.
     * @param ball the ball to check if out of border.
     * @return true if the ball is out the box width borders, false otherwise.
     */
    public boolean isBallWidthOutOfBorder(Ball ball) {
        int radius = ball.getSize();
        int ballLeftEdge = ball.getX() - radius;
        int ballRightEdge = ball.getX() + radius;

        return this.leftEdge >= ballLeftEdge || this.rightEdge <= ballRightEdge;
    }

    /**
     * check if ball is out the box height borders.
     * @param ball the ball to check if out of border.
     * @return true if the ball is out the box height borders, false otherwise.
     */
    public boolean isBallHeightOutOfBorder(Ball ball) {
        int radius = ball.getSize();
        int ballBottomEdge = ball.getY() + radius;
        int ballTopEdge = ball.getY() - radius;

        return this.bottomEdge <= ballBottomEdge || this.topEdge >= ballTopEdge;
    }

    /**
     * generate random ball in the box borders.
     * @param radius the radius of the ball to generate.
     * @return ball in the box with the given radius. if the radius is too big for the box - return null.
     */
    public Ball getBallInBox(int radius) {
        // check if ball is small enough.
        if (2 * radius > this.width || 2 * radius > this.height) {
            return null;
        }

        Random rand = new Random();
        double xCenter = rand.nextDouble(this.leftEdge + radius, this.rightEdge - radius);
        double yCenter = rand.nextDouble(this.topEdge + radius, this.bottomEdge - radius);

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color color = new Color(r, g, b);

        return new Ball(xCenter, yCenter, radius, color);
    }
}
