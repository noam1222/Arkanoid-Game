// 209407162 Noam Maimon

import java.util.Random;
import java.awt.Color;

/**
 * class who represent box in the screen.
 */
public class Box {
    private final int width;
    private final int height;
    private final double rightEdge;
    private final double leftEdge;
    private final double bottomEdge;
    private final double topEdge;

    /**
     * constructor - initialize box object.
     *
     * @param start     the start point of the box on the screen.
     * @param boxWidth  the width of the box.
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
     *
     * @param boxWidth  the width of the box.
     * @param boxHeight the height of the box.
     */
    Box(int boxWidth, int boxHeight) {
        this(new Point(0, 0), boxWidth, boxHeight);
    }

    /**
     * constructor - initialize box object.
     *
     * @param x         x value of the start point of the box on the screen.
     * @param y         y value of the start point of the box on the screen.
     * @param boxWidth  the width of the box.
     * @param boxHeight the height of the box.
     */
    Box(double x, double y, int boxWidth, int boxHeight) {
        this(new Point(x, y), boxWidth, boxHeight);
    }

    /**
     * get the width of the box.
     *
     * @return the width of the box.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * get the height of the box.
     *
     * @return the height of the box.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * get the start point of the box.
     *
     * @return the start point of the box.
     */
    public Point getStartPoint() {
        return new Point(this.rightEdge, this.bottomEdge);
    }

    /**
     * check if ball is out the box width borders.
     *
     * @param ball the ball to check if out of border.
     * @return true if the ball is out the box width borders, false otherwise.
     */
    public boolean isBallWidthOutOfBorder(Ball ball) {
        int radius = ball.getSize();
        int ballLeftEdge = ball.getX() - radius + 1;
        int ballRightEdge = ball.getX() + radius - 1;

        return this.leftEdge >= ballLeftEdge || this.rightEdge <= ballRightEdge;
    }

    /**
     * check if ball is out the box height borders.
     *
     * @param ball the ball to check if out of border.
     * @return true if the ball is out the box height borders, false otherwise.
     */
    public boolean isBallHeightOutOfBorder(Ball ball) {
        int radius = ball.getSize();
        int ballBottomEdge = ball.getY() + radius + 1;
        int ballTopEdge = ball.getY() - radius - 1;

        return this.bottomEdge <= ballBottomEdge || this.topEdge >= ballTopEdge;
    }

    /**
     * squeeze radius to fit the box size.
     *
     * @param radius the radius to squeeze.
     * @return the squeezed radius.
     */
    public int squeezeRadiusToBox(int radius) {
        Random rand = new Random();
        return rand.nextInt(Math.min(this.width, this.height)) / 2;
    }

    /**
     * get random center point in the box, respectively to the box size.
     *
     * @param radius the radius to fit the center point to the box.
     * @return good center point respectively to the radius.
     */
    public Point getRandomCenterPointInBox(int radius) {
        Random rand = new Random();
        double ballLeftEdge = this.leftEdge + (double) radius;
        double ballRightEdge = this.rightEdge - (double) radius;
        double xCenter = ballLeftEdge + (ballRightEdge - ballLeftEdge) * rand.nextDouble();

        double ballTopEdge = this.topEdge + (double) radius;
        double ballBottomEdge = this.bottomEdge - (double) radius;
        double yCenter = ballTopEdge + (ballBottomEdge - ballTopEdge) * rand.nextDouble();
        return new Point(xCenter, yCenter);
    }

    /**
     * generate random ball in the box borders.
     *
     * @param radius the radius of the ball to generate.
     * @return ball in the box with the given radius. if the radius is too big for the box - return null.
     */
    public Ball getRandomBallInBox(int radius) {
        // check if ball is small enough.
        if (2 * radius > this.width || 2 * radius > this.height) {
            radius = squeezeRadiusToBox(radius);
        }

        Point center = getRandomCenterPointInBox(radius);

        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color color = new Color(r, g, b);

        return new Ball(center, radius, color);
    }
}
